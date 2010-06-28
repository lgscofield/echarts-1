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

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.shared.CodeDTO;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.MessageDTO;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class MessagesPresenter extends Presenter<MessagesPresenter.Display> {

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

	private RpcServicesAsync rpcServices;
	private List<MessageDTO> messages;
	private ArrayList<String[]> data = new ArrayList<String[]>();
	private EHR ehr;
	private List<CodeDTO> types;

	public MessagesPresenter(final Display display, HandlerManager eventBus,
			RpcServicesAsync rpcServices, EHR ehr) {
		super(display, eventBus);
		this.ehr = ehr;
		this.rpcServices = rpcServices;
	}

	@Override
	public void go(final HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchData();
		bind();
	}

	private void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				showAddMessage();
			}
		});
		display.getSaveButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				MessageDTO m = new MessageDTO();
				m.setEhrId(ehr.getId());
				CodeDTO mtDto = findMessageType(display.getMessageType());
				m.setMessageType(mtDto);
				m.setMessage(display.getMessage());
				m.setCreationTimestamp(new Date());
				m.setLastEdit(new Date());
				m.setLastEditBy(Cookies.getCookie("echarts_user"));
				save(m);
			}
		});
		display.getCloseButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				display.close();
			}
		});
	}

	public CodeDTO findMessageType(String messageType) {
		List<CodeDTO> types = getTypes();
		for (CodeDTO type : types)
			if (type.getDescriptor().matches(messageType))
				return type;
		return null;
	}

	public void save(MessageDTO m) {
		AsyncCallback<MessageDTO> callback = new AsyncCallback<MessageDTO>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(MessageDTO result) {
				display.saved();
				messages.add(result);
				setData(messages);
				display.setData(getData());
			}
		};
		rpcServices.addMessage(m, Cookies.getCookie("sessionId"),
				callback);
	}

	private void loadMessageType() {
		AsyncCallback<List<CodeDTO>> callback = new AsyncCallback<List<CodeDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(List<CodeDTO> types) {
				setTypes(types);
				ArrayList<String> data = new ArrayList<String>();
				for (CodeDTO type : types)
					data.add(type.getDescriptor());
				display.setMessageTypes(data);
			}
		};
		rpcServices.getMessageTypes(Cookies.getCookie("sessionId"),
				callback);
	}

	private void fetchData() {
		AsyncCallback<List<MessageDTO>> callback = new AsyncCallback<List<MessageDTO>>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(List<MessageDTO> data) {
				setData(data);
				display.setData(getData());
			}
		};
		rpcServices.getMessages(ehr.getId(), Cookies.getCookie("sessionId"),
				callback);
	}

	public ArrayList<String[]> getData() {
		return this.data;
	}

	public void setData(List<MessageDTO> msgs) {
		this.messages = msgs;
		ArrayList<String[]> d = new ArrayList<String[]>();

		for (MessageDTO m : msgs) {
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

	public MessageDTO getMessage(int i) {
		return messages.get(i);
	}

	private void showAddMessage() {
		loadMessageType();
		display.setText(ehr.getSubject().getCaseNumber());
		display.show();
	}

	public void setTypes(List<CodeDTO> types) {
		this.types = types;
	}

	public List<CodeDTO> getTypes() {
		return types;
	}
}
