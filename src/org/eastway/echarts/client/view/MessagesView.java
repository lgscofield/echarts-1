package org.eastway.echarts.client.view;

import java.util.ArrayList;

import org.eastway.echarts.client.presenter.MessagesPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class MessagesView extends Composite implements MessagesPresenter.Display {
	private static MessagesViewUiBinder uiBinder = GWT.create(MessagesViewUiBinder.class);

	interface MessagesViewUiBinder extends
			UiBinder<Widget, MessagesView> {}

	@UiField FlowPanel messages;
	@UiField Button add;
	@UiField DialogBox db;
	@UiField Button saveButton, closeButton;
	@UiField ListBox messageTypes;
	@UiField TextArea message;

	public MessagesView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(ArrayList<String> data) {
		messages.clear();
		for (String s : data) {
			messages.add(new HTML(s));
		}
	}

	@Override
	public HasClickHandlers getAddButton() {
		return add;
	}

	@Override
	public void close() {
		db.hide();
	}

	@Override
	public HasClickHandlers getCloseButton() {
		return closeButton;
	}

	@Override
	public String getMessage() {
		return message.getText();
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
		db.hide();
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
		db.show();
		db.center();
	}
}
