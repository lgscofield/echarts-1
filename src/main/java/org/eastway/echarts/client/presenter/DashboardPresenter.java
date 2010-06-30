package org.eastway.echarts.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.ChangeCurrentEhrEventHandler;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.shared.Assignment;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
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
		bind();
	}

	private void bind() {
		eventBus.addHandler(ChangeCurrentEhrEvent.TYPE, new ChangeCurrentEhrEventHandler() {
			@Override
			public void onChangeCurrentEhr(ChangeCurrentEhrEvent event) {
				setCurrentEhrData(event.getEhr());
			}
		});
	}

	private void setCurrentEhrData(EHR ehr) {
		if (ehr == null) {
			view.setCurrentEhrData(null);
			return;
		}
		ArrayList<String[]> data = new ArrayList<String[]>();
		Patient patient = ehr.getSubject();
		Demographics demographics = ehr.getDemographics();
		data.add(new String[] {"Name",patient.getName()});
		data.add(new String[] {"DOB",new Long(demographics.getDob().getTime()).toString()});

		Long age = (new Date().getTime() - demographics.getDob().getTime()) / (3600*24*365) / 1000;

		data.add(new String[] {"Age",  age.toString() });
		data.add(new String[] {"Case Status",patient.getCaseStatus().getDescriptor() });
		data.add(new String[] {"Provider", getProvider(ehr.getAssignments()) });
		data.add(new String[] {"SSN",patient.getSsn()});
		view.setCurrentEhrData(data);
	}

	private String getProvider(List<Assignment> assignments) {
		for (Assignment assignment : assignments) {
			if (!assignment.getService().matches("S CS"))
				continue;
			else
				return assignment.getStaffName();
		}

		for (Assignment assignment : assignments)
			return assignment.getStaffName();
		return null;
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

	@Override
	public void onItemSelected(String row) {
		if (row != null) {
			long ehrId = getData().get(row);
			eventBus.fireEvent(new OpenEhrEvent(ehrId));
		}
	}

	private LinkedHashMap<String, Long> getData() {
		return this.data;
	}

	@Override
	public void changeCurrentEhr(EHR ehr) {
		if (ehr != null) {
			eventBus.fireEvent(new ChangeCurrentEhrEvent(ehr));
			History.newItem("list", false);
		} else {
			eventBus.fireEvent(new ChangeCurrentEhrEvent(ehr));
		}
	}

	@Override
	public void openEhr(String text) {
		eventBus.fireEvent(new OpenEhrEvent(data.get(text)));
	}
}
