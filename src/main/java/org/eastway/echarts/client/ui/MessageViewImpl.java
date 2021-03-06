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
package org.eastway.echarts.client.ui;

import java.util.ArrayList;
import java.util.Date;

import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;

public class MessageViewImpl<T> extends Composite implements MessageView<T> {
	private static MessagesViewUiBinder uiBinder = GWT.create(MessagesViewUiBinder.class);

	@SuppressWarnings("unchecked")
	@UiTemplate("MessageView.ui.xml")
	interface MessagesViewUiBinder extends
			UiBinder<Widget, MessageViewImpl> {}

	@UiField FlowPanel messages;
	@UiField Button add;
	@UiField DialogBox db;
	@UiField Button saveButton, closeButton;
	@UiField ListBox messageTypes;
	@UiField RichTextArea message;
	@UiField Style style;
	@UiField SpanElement error;

	private Presenter<T> presenter;

	public MessageViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(ArrayList<String[]> data) {
		if (data == null || data.isEmpty()) {
			messages.setVisible(false);
			return;
		} else {
			messages.setVisible(true);
			error.setInnerHTML("");
		}
		messages.clear();
		for (String[] s : data)
			messages.add(formatMessage(s));
	}

	public HTML formatMessage(String[] m) {
		HTML message = new HTML(m[3]);
		return new HTML("<strong>"
				+ GlobalResources.getDateTimeFormat()
					.format(new Date(new Long(m[0])))
				+ "</strong>&mdash;"
				+ m[1] + "&mdash;"
				+ m[2] + "<div class='home-PatientMessage'>"
				+ message + "</div>");
	}

	@UiHandler("saveButton")
	void handleSaveClicked(ClickEvent event) {
		presenter.save(getMessageType(), getMessage());
	}

	@UiHandler("add")
	void handleAddClicked(ClickEvent event) {
		setText(presenter.getId());
		message.setText("");
		show();
	}

	@UiHandler("closeButton")
	void handleCloseClicked(ClickEvent event) {
		close();
	}

	@Override
	public void close() {
		db.setVisible(false);
	}

	@Override
	public HasClickHandlers getCloseButton() {
		return closeButton;
	}

	@Override
	public String getMessage() {
		return message.getHTML();
	}

	@Override
	public String getMessageType() {
		int idx = messageTypes.getSelectedIndex();
		return messageTypes.getItemText(idx);
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	@Override
	public void saved() {
		com.google.gwt.user.client.Window.alert("Message saved");
		close();
	}

	@Override
	public void setMessageTypes(ArrayList<String> types) {
		messageTypes.clear();
		if (types == null)
			return;
		for (String s : types)
			messageTypes.addItem(s);
	}

	@Override
	public void setText(String patientId) {
		db.setText("Message for " + patientId);
	}

	@Override
	public void show() {
		db.setVisible(true);
		db.addStyleName(style.show());
		db.center();
		message.setFocus(true);
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setError(String message) {
		error.setInnerText(message);
	}

	@Override
	public void reset() {
		close();
		message.setText("");
	}

	@Override
	public boolean isDisplayVisible() {
		return db.isVisible();
	}
}
