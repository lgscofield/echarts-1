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

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
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
	private Messages messages;
	private ArrayList<String[]> data = new ArrayList<String[]>();
	private String patientid;

	public MessagesPresenter(final Display display, HandlerManager eventBus,
			RpcServicesAsync rpcServices, String patientid) {
		super(display, eventBus);
		this.patientid = patientid;
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
				Message m = new Message();
				m.setPatId(patientid);
				m.setMessageType(display.getMessageType());
				m.setMessage(display.getMessage());
				m.setCreationDate(new Date().getTime());
				m.setLastModifiedBy(UserImpl.getStaffName());
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

	public void save(final Message m) {
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Void result) {
				display.saved();
				messages.add(m);
				setData(messages);
				display.setData(getData());
			}
		};
		rpcServices.addMessage(m, UserImpl.getSessionId(),
				callback);
	}

	private void loadMessageType() {
		AsyncCallback<ArrayList<String>> callback = new AsyncCallback<ArrayList<String>>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(ArrayList<String> types) {
				display.setMessageTypes(types);
			}
		};
		rpcServices.getMessageTypes(UserImpl.getSessionId(),
				callback);
	}

	private void fetchData() {
		AsyncCallback<Messages> callback = new AsyncCallback<Messages>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Messages data) {
				setData(data);
				display.setData(getData());
			}
		};
		rpcServices.getMessages(patientid, UserImpl.getSessionId(),
				callback);
	}

	public ArrayList<String[]> getData() {
		return this.data;
	}

	public void setData(Messages msgs) {
		this.messages = msgs;
		ArrayList<String[]> d = new ArrayList<String[]>();
		Message m;

		if (msgs.get(0) == null)
			d = null;
		else
			for (int i = 0; (m = messages.get(i)) != null; i++) {
				String[] msgstr = {
					new Long(m.getCreationDate()).toString(),
					m.getMessageType(),
					m.getLastModifiedBy(),
					m.getMessage()
				};
				d.add(msgstr);
			}
		this.data = d;
	}

	public Message getMessage(int i) {
		return messages.get(i);
	}

	private void showAddMessage() {
		loadMessageType();
		display.setText(patientid);
		display.show();
	}
}
