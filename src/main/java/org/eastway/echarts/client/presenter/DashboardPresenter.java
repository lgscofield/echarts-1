package org.eastway.echarts.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.CachingDispatchAsyncImpl;
import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.events.ChangeCurrentEhrEvent;
import org.eastway.echarts.client.events.ChangeCurrentEhrEventHandler;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.shared.Assignment;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.GetAssignments;
import org.eastway.echarts.shared.GetAssignmentsResult;

import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class DashboardPresenter implements Presenter, DashboardView.Presenter<LinkedHashMap<String, Long>> {

	private DashboardView<LinkedHashMap<String, Long>> view;
	private EventBus eventBus;
	private GetAssignmentsResult data;
	private CachingDispatchAsyncImpl dispatch;
	private GetAssignments action = new GetAssignments(EchartsUser.sessionId, EchartsUser.staffId);
	private String caseNumber;

	@Inject
	public DashboardPresenter(DashboardView<LinkedHashMap<String, Long>> view,
			EventBus eventBus, final CachingDispatchAsyncImpl dispatch) {
		this.view = view;
		this.view.setPresenter(this);
		this.eventBus = eventBus;
		this.dispatch = dispatch;
	}

	private void bind() {
		eventBus.addHandler(ChangeCurrentEhrEvent.TYPE, new ChangeCurrentEhrEventHandler() {
			@Override
			public void onChangeCurrentEhr(ChangeCurrentEhrEvent event) {
				setCurrentEhrData(event.getEhr());
			}
		});
	}

	public DashboardView<LinkedHashMap<String, Long>> getDisplay() {
		return view;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(view.asWidget());
		bind();
		fetchData();
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
		data.add(new String[] {"Provider", getProvider() });
		data.add(new String[] {"SSN",patient.getSsn()});
		view.setCurrentEhrData(data);
	}

	private String getProvider() {
		List<Assignment> assignments = data.getAssignments();
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
		eventBus.fireEvent(new RequestEvent(State.SENT));
		dispatch.executeWithCache(action, new AsyncCallback<GetAssignmentsResult>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(GetAssignmentsResult data) {
				Map<String, Long> pl = new LinkedHashMap<String, Long>();
				for (Assignment assignment : data.getAssignments())
					if (assignment != null)
						pl.put(new StringBuilder()
									.append(assignment.getPatient().getCaseNumber())
									.append(" - ")
									.append( assignment.getPatient().getLastName())
									.append((assignment.getPatient().getSuffix() == null ? ", " : " " + assignment.getPatient().getSuffix() + ", "))
									.append(assignment.getPatient().getFirstName())
									.append((assignment.getPatient().getMiddleInitial() == null ? "" : ", " + assignment.getPatient().getMiddleInitial())).toString(),
									new Long(assignment.getId()));
				eventBus.fireEvent(new RequestEvent(State.RECEIVED));
				view.reset();
				for (String str : pl.keySet())
					view.addPatientSearchData(str);
				setData(data);
			}
		});
	}

	private void setData(GetAssignmentsResult data) {
		this.data = data;
	}

	@Override
	public void onItemSelected(String row) {
		if (row != null) {
			eventBus.fireEvent(new OpenEhrEvent(caseNumber));
		}
	}

//	private Map<String, Long> getData() {
//		return data.getAssignments();
//	}

	@Override
	public void changeCurrentEhr(EHR ehr) {
			eventBus.fireEvent(new ChangeCurrentEhrEvent(ehr));
	}

	@Override
	public void openEhr(String text) {
		eventBus.fireEvent(new OpenEhrEvent(text.replaceAll("(.*) - .*", "$1")));
	}

	@Override
	public void patientListOpen() {
		fetchData();
	}
}
