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
package org.eastway.echarts.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.MessageRequest;
import org.eastway.echarts.shared.CodeProxy;
import org.eastway.echarts.shared.MessageProxy;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.user.client.ui.HasWidgets;

public class MessagesPresenter implements Presenter {

	public interface Display extends EchartsDisplay {
		void setData(ArrayList<String[]> data);

		HasClickHandlers getAddButton();

		void setText(String patientId);

		HasClickHandlers getSaveButton();

		HasClickHandlers getCloseButton();

		String getMessage();

		String getMessageType();

		void saved();

		void close();

		void show();

		void setMessageTypes(ArrayList<String> types);
	}

	private List<MessageProxy> messages = new ArrayList<MessageProxy>();
	private ArrayList<String[]> data = new ArrayList<String[]>();
	private List<CodeProxy> types;
	private Display view;
	private EchartsRequestFactory requestFactory;
	private String caseNumber;

	public MessagesPresenter(Display view, EventBus eventBus,
			EchartsRequestFactory requestFactory, String caseNumber) {
		this.view = view;
		this.requestFactory = requestFactory;
		this.caseNumber = caseNumber;
	}

	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
		bind();
	}

	private void bind() {
		view.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				showAddMessage();
			}
		});
		view.getSaveButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MessageRequest request = requestFactory.messageRequest();
				MessageProxy m = request.create(MessageProxy.class);
				m.setCaseNumber(caseNumber);
				CodeProxy mtDto = findMessageType(view.getMessageType());
				m.setMessageType(mtDto);
				m.setMessage(view.getMessage());
				m.setCreationTimestamp(new Date());
				m.setLastEdit(new Date());
				m.setLastEditBy(EchartsUser.userName);
				save(m, request);
			}
		});
		view.getCloseButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				view.close();
			}
		});
	}

	public CodeProxy findMessageType(String messageType) {
		List<CodeProxy> types = getTypes();
		for (CodeProxy type : types)
			if (type.getDescriptor().matches(messageType))
				return type;
		return null;
	}

	public void save(MessageProxy newMessage, MessageRequest request) {
//		requestFactory.execute(new SaveMessage(EchartsUser.sessionId, m), new EchartsCallback<SaveMessageResult>(eventBus) {
//			@Override
//			protected void handleFailure(Throwable caught) {
//			}
//
//			@Override
//			protected void handleSuccess(SaveMessageResult result) {
//				view.saved();
//				messages.add(0, result.getMessage());
//				setData(messages);
//				view.setData(getData());
//			}
//		});
		Request<Void> createReq = request.persist().using(newMessage);
		createReq.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				view.saved();
			}
		});
	}

	private void fetchData() {
		Request<List<CodeProxy>> codeRequest = requestFactory.codeRequest().findCodeByName("MessageType");
		codeRequest.fire(new Receiver<List<CodeProxy>>() {
			@Override
			public void onSuccess(List<CodeProxy> response) {
				setTypes(response);
				ArrayList<String> typesData = new ArrayList<String>();
				for (CodeProxy type : types)
					typesData.add(type.getDescriptor());
				view.setMessageTypes(typesData);
			}
		});
		MessageRequest request = requestFactory.messageRequest();
		Request<List<MessageProxy>> messageRequest = request.findMessageByCaseNumber(caseNumber).with("messageType");
		messageRequest.fire(new Receiver<List<MessageProxy>>() {
			@Override
			public void onSuccess(List<MessageProxy> response) {
				setData(response);
				view.setData(getData());
			}
		});
	}

	public ArrayList<String[]> getData() {
		return this.data;
	}

	public void setData(List<MessageProxy> msgs) {
		this.messages = msgs;
		ArrayList<String[]> d = new ArrayList<String[]>();

		for (MessageProxy m : msgs) {
			String[] msgstr = {
					new Long(m.getCreationTimestamp().getTime()).toString(),
					m.getMessageType().getDescriptor(),
					m.getLastEditBy(),
					m.getMessage()
				};
				d.add(msgstr);
		}
		this.data = d;
	}

	public MessageProxy getMessage(int i) {
		return messages.get(i);
	}

	private void showAddMessage() {
		view.setText(caseNumber);
		view.show();
	}

	public void setTypes(List<CodeProxy> types) {
		this.types = types;
	}

	public List<CodeProxy> getTypes() {
		return types;
	}
}
