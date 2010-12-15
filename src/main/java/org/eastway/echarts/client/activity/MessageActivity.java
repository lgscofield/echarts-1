/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.client.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.request.CodeProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.MessageProxy;
import org.eastway.echarts.client.request.MessageRequest;
import org.eastway.echarts.client.ui.CurrentEhrWidget;
import org.eastway.echarts.client.ui.MessageView;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class MessageActivity implements Activity, MessageView.Presenter<MessageProxy> {

	private List<MessageProxy> messages = new ArrayList<MessageProxy>();
	private ArrayList<String[]> data = new ArrayList<String[]>();
	private List<CodeProxy> types = new ArrayList<CodeProxy>();
	private MessageView<MessageProxy> view;
	private String caseNumber;
	private EchartsRequestFactory requestFactory;

	public MessageActivity(MessagePlace place,
						   EchartsRequestFactory requestFactory,
						   MessageView<MessageProxy> view) {
		this.caseNumber = place.getCaseNumber();
		this.requestFactory = requestFactory;
		this.view = view;
	}

	public CodeProxy findMessageType(String messageType) {
		List<CodeProxy> types = getTypes();
		for (CodeProxy type : types)
			if (type.getCodeDescriptor().matches(messageType))
				return type;
		return null;
	}

	public void save(String messageType, String message) {
		CodeProxy codeProxy = findMessageType(messageType);
		Date now = new Date();
		MessageRequest messageRequest = requestFactory.messageRequest();
		final MessageProxy newMessage = messageRequest.create(MessageProxy.class);

		newMessage.setCaseNumber(caseNumber);
		newMessage.setCreationTimestamp(now);
		newMessage.setLastEdit(now);
		newMessage.setLastEditBy(EchartsUser.userName);
		newMessage.setMessage(message);
		newMessage.setMessageType(codeProxy);

		messageRequest.persist().using(newMessage).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				view.saved();
				messages.add(0, newMessage);
				setMessages(messages);
				setData(messages);
				view.setData(getData());
			}
		});
	}

	private void fetchData() {
		Request<List<CodeProxy>> codeRequest = requestFactory.codeRequest().findAllCodes();
		codeRequest.fire(new Receiver<List<CodeProxy>>() {
			@Override
			public void onSuccess(List<CodeProxy> response) {
				if (response != null) {
					setTypes(response);
					ArrayList<String> typesData = new ArrayList<String>();
					for (CodeProxy type : types)
						typesData.add(type.getCodeDescriptor());
					view.setMessageTypes(typesData);
				}
			}
		});
		MessageRequest request = requestFactory.messageRequest();
		Request<List<MessageProxy>> messageRequest = request.findMessageByCaseNumber(caseNumber).with("messageType");
		messageRequest.fire(new Receiver<List<MessageProxy>>() {
			@Override
			public void onSuccess(List<MessageProxy> response) {
				if (response != null && !response.isEmpty()) {
					setMessages(response);
					setData(response);
					view.setData(getData());
					CurrentEhrWidget.instance().setEhr(caseNumber, requestFactory);
				} else {
					handleFailure("No messages found for case number: " + caseNumber);
				}
			}

			@Override
			public void onFailure(ServerFailure failure) {
				handleFailure(failure.getMessage());
			}
		});
	}

	public ArrayList<String[]> getData() {
		return this.data;
	}

	public void setMessages(List<MessageProxy> messages) {
		this.messages = messages;
	}

	public void setData(List<MessageProxy> messages) {
		ArrayList<String[]> d = new ArrayList<String[]>();

		for (MessageProxy message : messages) {
			String[] msgstr = {
					new Long(message.getCreationTimestamp().getTime()).toString(),
					message.getMessageType().getCodeDescriptor(),
					message.getLastEditBy(),
					message.getMessage()
				};
				d.add(msgstr);
		}
		this.data = d;
	}

	public MessageProxy getMessage(int i) {
		return messages.get(i);
	}

	public void setTypes(List<CodeProxy> types) {
		for (CodeProxy c : types)
			if(c.getColumnName().equals("MessageType"))
					this.types.add(c);
	}

	public List<CodeProxy> getTypes() {
		return types;
	}

	@Override
	public String mayStop() {
		return null;
	}

	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setPresenter(this);
		panel.setWidget(view.asWidget());
		fetchData();
		
	}

	@Override
	public String getId() {
		return caseNumber;
	}

	private void handleFailure(String message) {
		view.setData(null);
		CurrentEhrWidget.instance().setEhr(null);
		view.setError(message);
	}
}
