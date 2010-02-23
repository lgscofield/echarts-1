package org.eastway.echarts.client.presenter;

import java.util.Vector;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.events.LogoutEvent;
import org.eastway.echarts.client.events.OpenPatientEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;

public class TopPanelPresenter extends EchartsPresenter<TopPanelPresenter.Display> {
	public interface Display extends EchartsDisplay {
		void setData(Vector<String> data);

		HasClickHandlers getSearchButton();

		HasText getSuggestBox();

		HasClickHandlers getLogoutButton();
	}

	private PatientServicesAsync patientSvc;

	public TopPanelPresenter(Display display, HandlerManager eventBus, PatientServicesAsync patientSvc) {
		super(display, eventBus);
		this.patientSvc = patientSvc;
		fetchData();
		bind();
	}

	private void bind() {
		display.getSearchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new OpenPatientEvent(display.getSuggestBox().getText().replaceAll("(.*) - .*", "$1")));
				display.getSuggestBox().setText("");
			}
		});
		display.getLogoutButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogoutEvent());
			}
		});
	}

	public void fetchData() {
		AsyncCallback<Vector<String>> callback = new AsyncCallback<Vector<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Vector<String> data) {
				display.setData(data);
			}
		};
		patientSvc.getPatientList(UserImpl.getSessionId(), callback);
	}
}
