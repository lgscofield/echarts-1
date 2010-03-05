package org.eastway.echarts.client.presenter;

import java.util.ArrayList;

import org.eastway.echarts.client.events.SavedPatientMessageEvent;
import org.eastway.echarts.client.EchartsRpc;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class AddMessagePresenter extends EchartsPresenter<AddMessagePresenter.Display> {

	public interface Display extends EchartsDisplay {
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

	public AddMessagePresenter(Display display, HandlerManager eventBus,
			Patient patient) {
		super(display, eventBus);
		this.patient = patient;
		display.setText(patient.getPatientId());
		bind();
		loadMessageType();
		display.show();
	}

	private void bind() {
		display.getSaveButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				save(display.getMessage(), display.getMessageType());
			}
		});
		display.getCloseButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				display.close();
			}
		});
	}

	private void save(String message, String messageType) {
		Message m = new Message();
		m.add(patient.getPatientId(), messageType, message);

		AsyncCallback<Void> callback = new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Void result) {
				display.saved();
				eventBus.fireEvent(new SavedPatientMessageEvent());
			}
		};
		EchartsRpc.getRpc().addMessage(m, UserImpl.getSessionId(),
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
		EchartsRpc.getRpc().getMessageTypes(UserImpl.getSessionId(),
				callback);
	}
}
