package org.eastway.echarts.client.presenter;

import java.util.ArrayList;
import java.util.Date;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class MessagesPresenter extends Presenter<MessagesPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(ArrayList<String> data);

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

	private Patient patient;
	private RpcServicesAsync rpcServices;
	private Messages messages;

	public MessagesPresenter(final Display display, HandlerManager eventBus,
			RpcServicesAsync rpcServices, Patient patient) {
		super(display, eventBus);
		this.patient = patient;
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
				m.setPatId(patient.getPatientId());
				m.setMessageType(display.getMessageType());
				m.setMessage(display.getMessage());
				m.setCreationDate(DateTimeFormat.getShortDateTimeFormat().format(new Date()));
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
				display.setData(setData(messages));
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
				display.setData(setData(data));
			}
		};
		rpcServices.getMessages(patient.getPatientId(),	UserImpl.getSessionId(),
				callback);
	}

	public ArrayList<String> setData(Messages msgs) {
		this.messages = msgs;
		ArrayList<String> data = new ArrayList<String>();
		Message m;
		if (msgs.get(0) == null)
			data.add("No Messages");
		else
			for (int i = 0; (m = messages.get(i)) != null; i++)
				data.add(formatMessage(m));
		return data;
	}

	private String formatMessage(Message m) {
		return "<strong>" + m.getCreationDate()
			+ "</strong>&mdash;"
			+ m.getMessageType() + "&mdash;"
			+ m.getLastModifiedBy()
			+ "<div class='home-PatientMessage'>"
			+ m.getMessage() + "</div>";
	}

	public Message getMessage(int i) {
		return messages.get(i);
	}

	private void showAddMessage() {
		loadMessageType();
		display.show();
	}
}
