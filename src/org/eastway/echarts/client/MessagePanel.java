package org.eastway.echarts.client;

import java.util.ArrayList;

import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.eastway.echarts.client.HandleRpcException;

public class MessagePanel extends VerticalPanel implements ClickHandler {
	private final PatientServicesAsync patientSvc;
	private FlexTable msgTable = new FlexTable();
	private Button addLbl = new Button("Add");
	private String patientId;
	private ListBox messageType = new ListBox();

	public MessagePanel(String patientId, PatientServicesAsync patientSvc) {
		addLbl.addClickHandler(this);
		this.patientSvc = patientSvc;
		this.patientId = patientId;
		get(this);
		loadMessageType();
	}

	private void get(final MessagePanel messagePanel) {
		AsyncCallback<Messages> callback = new AsyncCallback<Messages>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Messages result) {
				Message m;
				String dateOut;
				if (result.get(0) == null) {
					msgTable.setText(0, 0, "No messages");
				} else {
					msgTable.setWidget(0, 0, addLbl);
					for (int i = 0; (m = result.get(i)) != null; i++) {
						dateOut = DateTimeFormat.getShortDateTimeFormat()
								.format(m.MessageDate);
						msgTable.setHTML(i, 0, "<strong>" + dateOut
								+ "</strong> &mdash; " + m.MessageType
								+ " &mdash; " + m.LastEditBy
								+ "<div class='home-PatientMessage'>"
								+ m.Message + "</div>");
					}
				}
				messagePanel.add(addLbl);
				messagePanel.add(msgTable);
			}
		};
		patientSvc.getMessages(patientId, Cookies.getCookie("sessionId"), callback);
		return;
	}

	public void add(final String patientId, String Message, String MessageType,
			final DialogBox db) {
		Message msg = new Message();
		msg.add(patientId, MessageType, Message, "5597");
		final MessagePanel messagePanel = this;
		AsyncCallback<Void> callback = new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Message saved");
				db.hide();
				get(messagePanel);
			}
		};
		patientSvc.addMessage(msg, Cookies.getCookie("sessionId"), callback);
	}

	@Override
	public void onClick(ClickEvent event) {
		Object sender = event.getSource();
		if (sender == addLbl) {
			showAddMessage();
		}
	}

	public void showAddMessage() {
		VerticalPanel vPanel = new VerticalPanel();
		HorizontalPanel buttonPanel = new HorizontalPanel();
		final TextArea ta = new TextArea();
		final DialogBox db = new DialogBox();

		Button saveButton = new Button("Save", new ClickHandler() {
			public void onClick(ClickEvent event) {
				int messageTypeIndex;
				if ((messageTypeIndex = messageType.getSelectedIndex()) < 0)
					Window.alert("Please select a message type");
				else
					// messageTypeIndex + 1 because
					// MessageType starts at 1
					// in the VMessageType
					// and ListBox indexes from 0
					add(patientId, ta.getText(), messageType
							.getItemText(messageTypeIndex), db);
				return;
			}
		});

		Button closeButton = new Button("Close", new ClickHandler() {
			public void onClick(ClickEvent event) {
				db.hide();
			}
		});

		db.setText("Message " + patientId);
		db.setModal(false);
		vPanel.add(ta);
		buttonPanel.add(messageType);
		buttonPanel.add(saveButton);
		buttonPanel.add(closeButton);
		vPanel.add(buttonPanel);
		vPanel.setCellHorizontalAlignment(buttonPanel,
				HasHorizontalAlignment.ALIGN_RIGHT);
		ta.setSize("400px", "400px");
		db.add(vPanel);
		// The order matters to IE7: first show, then center
		db.show();
		db.center();
	}

	private void loadMessageType() {
		AsyncCallback<ArrayList<String>> callback = new AsyncCallback<ArrayList<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(ArrayList<String> result) {
				if (result == null)
					return;
				int i = 0;
				while (i < result.size())
					messageType.addItem(result.get(i++));
			}
		};
		patientSvc.getMessageTypes(Cookies.getCookie("sessionId"), callback);
	}
}
