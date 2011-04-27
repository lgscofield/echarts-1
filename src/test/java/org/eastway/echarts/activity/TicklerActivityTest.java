package org.eastway.echarts.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.activity.TicklerActivity;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.AssignmentRequest;
import org.eastway.echarts.client.request.DemographicsProxy;
import org.eastway.echarts.client.request.DemographicsRequest;
import org.eastway.echarts.client.request.PatientProxy;
import org.eastway.echarts.client.request.PatientRequest;
import org.eastway.echarts.client.request.UserProxy;
import org.eastway.echarts.client.request.UserRequest;
import org.eastway.echarts.client.ui.TicklerView;
import org.eastway.echarts.shared.Tickler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.requestfactory.shared.Violation;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class TicklerActivityTest extends ActivityTestBase {

	private static class MyDisplay implements AcceptsOneWidget {
		@Override
		public void setWidget(IsWidget widget) {
		}
	}

	private static class MyTicklerView implements TicklerView<Tickler> {

		private List<Tickler> rowData;

		@Override
		public Widget asWidget() {
			return null;
		}

		@Override
		public void setPresenter(Presenter<Tickler> presenter) {
		}

		@Override
		public void setRowData(List<Tickler> ticklers) {
			this.rowData = ticklers;
		}

		public List<Tickler> getRowData() {
			return rowData;
		}

		@Override
		public void setColumnDefinitions(List<ColumnDefinition<Tickler>> columnDefinitions) {
		}

		@Override
		public void setHeader(String headerText) {
		}

		@Override
		public void setNoteTimeliness(int noDataCount, int noDataPercentage,
				int overdueCount, int overduePercentage, int upToDateCount,
				int upToDatePercentage) {
		}

		@Override
		public String formatTicklerDate(Date date) {
			return date.toString();
		}
	};

	TicklerPlace place;
	List<ColumnDefinition<Tickler>> columnDefinitions = new ArrayList<ColumnDefinition<Tickler>>();
	MyTicklerView view;
	TicklerActivity activity;
	MyDisplay display;
	PatientProxy patient;
	DemographicsProxy demographics;
	AssignmentProxy assignment;

	String caseNumber;
	String disposition;
	String service;
	String staffName;
	String program;
	String name;

	@Before
	public void setData() {
		caseNumber = "10";
		disposition = "Open";
		service = "S CS";
		staffName = "DOE, JOHN";
		program = "021";
		name = "Test, Harry";

		display = new MyDisplay();
		place = new TicklerPlace(EchartsUser.userName);
		view = new MyTicklerView();
		activity = new TicklerActivity(place, requestFactory, columnDefinitions, placeController, view);

		PatientRequest patientContext = requestFactory.patientRequest();
		patient = patientContext.create(PatientProxy.class);
		patient.setCaseNumber(caseNumber);
		patient.setLastEdit(new Date());
		patient.setFirstName("Harry");
		patient.setLastName("Test");
		patientContext.persist().using(patient).fire();

		DemographicsRequest demographicsContext = requestFactory.demographicsRequest();
		demographics = demographicsContext.create(DemographicsProxy.class);
		demographics.setCaseNumber(caseNumber);
		demographics.setLastEdit(new Date());
		demographicsContext.persist().using(demographics).fire();

		AssignmentRequest assignmentContext = requestFactory.assignmentRequest();
		assignment = assignmentContext.create(AssignmentProxy.class);

		assignment.setAssignmentDate(new Date());
		assignment.setCaseNumber(caseNumber);
		assignment.setDemographics(demographics);
		assignment.setDisposition(disposition);
		assignment.setId(1L);
		assignment.setLastEdit(new Date());
		assignment.setLastEditBy(EchartsUser.staffId);
		assignment.setOrderDate(new Date());
		assignment.setPatient(patient);
		assignment.setProgram(program);
		assignment.setService(service);
		assignment.setStaff(EchartsUser.staffId);
		assignment.setStaffName(staffName);
		assignment.setTermDate(new Date());

		UserRequest userContext = requestFactory.userRequest();
		UserProxy user = userContext.create(UserProxy.class);
		user.setId(EchartsUser.userName);
		user.setStaffId(EchartsUser.staffId);
		userContext.persist().using(user).fire();

		assignmentContext.persist().using(assignment).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
			}

			@Override
			public void onFailure(ServerFailure failure) {
				assertNotNull("failure message null", failure);
				assertNotNull("failure message null" , failure.getMessage());
				fail("server failure: " + failure.getMessage());
			}

			@Override
			public void onViolation(Set<Violation> violations) {
				String v = null;
				for (Violation violation : violations)
					v += violation.getMessage() + ", ";
				fail(v);
			}
		});
	}

	@After
	public void resetData() {
		requestFactory.assignmentRequest().remove().using(assignment).fire();
		requestFactory.patientRequest().remove().using(patient).fire();
		requestFactory.demographicsRequest().remove().using(demographics).fire();
	}

	@Test
	public void testStart() {
		activity.start(display, eventBus);
	}

	@Test
	public void testFetchData() {
		activity.start(display, eventBus);
		List<Tickler> rowData = activity.getDisplay().getRowData();
		assertNotNull("tickler data is null", rowData);
		assertFalse("tickler data is size zero", rowData.size() == 0);
		assertEquals(caseNumber, rowData.get(0).getCaseNumber());
		assertEquals(staffName, rowData.get(0).getStaffName());
		assertEquals(service, rowData.get(0).getService());
		assertEquals(name, rowData.get(0).getName());
	}
}
