package org.eastway.echarts.client.presenter;

import java.util.Vector;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.events.OpenPatientEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class PatientListPresenter extends EchartsPresenter<PatientListPresenter.Display> {
	private Vector<String> data;

	public interface Display extends EchartsDisplay {
		void setData(Vector<String> data);

		HasClickHandlers getTable();

		int getClickedRow(ClickEvent event);
	}

	private PatientServicesAsync patientSvc;

	public PatientListPresenter(Display display, HandlerManager eventBus, PatientServicesAsync patientSvc) {
		super(display, eventBus);
		this.patientSvc = patientSvc;
		fetchPatientList();
		bind();
	}

	private void bind() {
		display.getTable().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				int selected = display.getClickedRow(event);

				if (selected >= 0) {
					String patientId = getData().get(selected).toString().replaceAll("(.*) - .*", "$1");
					eventBus.fireEvent(new OpenPatientEvent(patientId));
				}
			}
		});
	}

	public void fetchPatientList() {
		AsyncCallback<Vector<String>> callback = new AsyncCallback<Vector<String>>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Vector<String> data) {
				display.setData(data);
				setData(data);
			}
		};
		patientSvc.getPatientList(UserImpl.getSessionId(), callback);
	}

	private void setData(Vector<String> data) {
		this.data = data;
	}

	private Vector<String> getData() {
		return this.data;
	}
}
