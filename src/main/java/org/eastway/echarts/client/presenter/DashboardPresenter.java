package org.eastway.echarts.client.presenter;

import java.util.LinkedHashMap;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.view.DashboardView;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class DashboardPresenter implements Presenter, DashboardView.Presenter<LinkedHashMap<String, Long>> {

	private DashboardView<LinkedHashMap<String, Long>> view;
	private HandlerManager eventBus;
	private PatientServicesAsync patientServices;
	private LinkedHashMap<String, Long> data;

	public DashboardPresenter(DashboardView<LinkedHashMap<String, Long>> view,
			HandlerManager eventBus, PatientServicesAsync patientServices) {
		this.view = view;
		this.view.setPresenter(this);
		this.eventBus = eventBus;
		this.patientServices = patientServices;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		fetchData();
	}

	private void fetchData() {
		AsyncCallback<LinkedHashMap<String, Long>> callback = new AsyncCallback<LinkedHashMap<String, Long>>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(LinkedHashMap<String, Long> data) {
				for (String str : data.keySet())
					view.addPatientSearchData(str);
				setData(data);
			}
		};
		patientServices.getPatientList(Cookies.getCookie("sessionId"), "5434", callback);
	}

	private void setData(LinkedHashMap<String, Long> data) {
		this.data = data;
	}
}
