package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.presenter.AddMessagePresenter;
import org.eastway.echarts.client.view.AddMessageView;
import org.eastway.echarts.shared.Messages;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class MessagesPresenter extends EchartsPresenter<MessagesPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(Messages data);

		HasClickHandlers getAddButton();
	}

	private String patientId;
	private PatientServicesAsync patientSvc;

	public MessagesPresenter(Display display, HandlerManager eventBus,
			PatientServicesAsync patientSvc, String patientId) {
		super(display, eventBus);
		this.patientId = patientId;
		this.patientSvc = patientSvc;
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
		patientSvc.getMessages(patientId, Cookies.getCookie("sessionId"),
				callback);
	}

	private void showAddMessage() {
		AddMessagePresenter addMessage =
			new AddMessagePresenter(new AddMessageView(),
				eventBus, patientSvc, patientId);
	}
}
