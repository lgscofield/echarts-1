package org.eastway.echarts.client.view;

import java.util.ArrayList;

import org.eastway.echarts.client.presenter.AddMessagePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class AddMessageView extends Composite implements AddMessagePresenter.Display {
	private static AddMessageViewUiBinder uiBinder = GWT.create(AddMessageViewUiBinder.class);

	interface AddMessageViewUiBinder extends
			UiBinder<Widget, AddMessageView> {}

	@UiField DialogBox db;
	@UiField Button saveButton, closeButton;
	@UiField ListBox messageTypes;
	@UiField TextArea message;

	public AddMessageView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setText(String patientId) {
		db.setText("Message for " + patientId);
	}

	@Override
	public void saved() {
		com.google.gwt.user.client.Window.alert("Message saved");
		db.hide();
	}

	@Override
	public HasClickHandlers getSaveButton() {
		return saveButton;
	}

	@Override
	public HasClickHandlers getCloseButton() {
		return closeButton;
	}

	@Override
	public void setMessageTypes(ArrayList<String> types) {
		if (types == null)
			return;
		for (int i = 0; i < types.size(); i++)
			messageTypes.addItem(types.get(i));
	}

	@Override
	public String getMessageType() {
		int idx = messageTypes.getSelectedIndex();
		return messageTypes.getItemText(idx);
	}

	@Override
	public String getMessage() {
		return message.getText();
	}

	@Override
	public void close() {
		db.clear();
		db.hide();
		db = null;
	}

	@Override
	public void show() {
		db.show();
		db.center();
	}
}
