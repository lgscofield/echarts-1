package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.events.SavedPatientMessageEvent;
import org.eastway.echarts.client.events.SavedPatientMessageEventHandler;
import org.eastway.echarts.client.EchartsRpc;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.presenter.AddMessagePresenter;
import org.eastway.echarts.client.view.AddMessageView;
import org.eastway.echarts.shared.Messages;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MessagesPresenter extends EchartsPresenter<MessagesPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(Messages data);

		HasClickHandlers getAddButton();
	}

	private Patient patient;

	public MessagesPresenter(Display display, HandlerManager eventBus,
			Patient patient) {
		super(display, eventBus);
		this.patient = patient;
		bind();
		setData();
	}

	private void bind() {
		display.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				showAddMessage();
			}
		});

		eventBus.addHandler(SavedPatientMessageEvent.TYPE,
				new SavedPatientMessageEventHandler() {
			public void onSavedPatientMessage(SavedPatientMessageEvent event) {
				refresh();
			}
		});
	}

	private void setData() {
		AsyncCallback<Messages> callback = new AsyncCallback<Messages>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Messages data) {
				display.setData(data);
			}
		};
		EchartsRpc.getRpc().getMessages(patient.getPatientId(), UserImpl.getSessionId(),
				callback);
	}

	private void showAddMessage() {
		new AddMessagePresenter(new AddMessageView(),
				eventBus, patient);
	}

	private void refresh() {
		setData();
	}
}
