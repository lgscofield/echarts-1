package org.eastway.echarts.client.activity;

import java.util.List;

import org.eastway.echarts.client.request.AssignmentProxy;
import org.eastway.echarts.client.request.AssignmentRequest;
import org.eastway.echarts.client.request.EHRProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.EhrRequest;
import org.eastway.echarts.client.ui.CurrentEhrView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.ServerFailure;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class CurrentEhrActivity extends AbstractActivity {

	class EHRFetcher {
		EHRProxy fetchedEHR;
		List<AssignmentProxy> fetchedAssignments;

		void Run(final EhrRequest ehrRequest, final AssignmentRequest assignmentRequest, final String caseNumber, final Receiver<EHRFetcher> callback) {
			ehrRequest.findEHRByCaseNumber(caseNumber)
				.with("patient")
				.with("patient.caseStatus")
				.with("demographics")
				.with("demographics.gender")
				.fire(
					new Receiver<EHRProxy>() {
						@Override
						public void onSuccess(EHRProxy response) {
							if (response != null) {
								fetchedEHR = response;
								assignmentRequest.findAssignmentsByCaseNumber(caseNumber).fire(
										new Receiver<List<AssignmentProxy>>() {
											@Override
											public void onSuccess(List<AssignmentProxy> response) {
												fetchedAssignments = response;
												if (fetchedAssignments != null)
													callback.onSuccess(EHRFetcher.this);
											}
										});
							}
						}

						@Override
						public void onFailure(ServerFailure failure) {
							view.setRowData(null);
						}
					});
								
		}
	}
	private CurrentEhrView view;
	private EchartsRequestFactory requestFactory;
	private String caseNumber;
	private AcceptsOneWidget panel;

	public CurrentEhrActivity(String caseNumber, CurrentEhrView view, EchartsRequestFactory requestFactory) {
		this.view = view;
		this.requestFactory = requestFactory;
		this.caseNumber = caseNumber;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		if (caseNumber == null) {
			view.setRowData(null);
			return;
		} else if (view.isEhrLoaded(caseNumber)) {
			panel.setWidget(view);
			return;
		}
		this.panel = panel;
		fetchData();
	}

	private void fetchData() {
		final EhrRequest ehrRequest = requestFactory.ehrRequest();
		AssignmentRequest assignmentRequest = requestFactory.assignmentRequest();
		new EHRFetcher().Run(ehrRequest, assignmentRequest, caseNumber, new Receiver<EHRFetcher>() {
			@Override
			public void onSuccess(EHRFetcher response) {
				if (response != null) {
					EHRProxy ehr = requestFactory.ehrRequest().edit(response.fetchedEHR);
					ehr.setAssignments(response.fetchedAssignments);
					view.setRowData(ehr);
					panel.setWidget(view);
				} else {
					handleFailure(null);
				}
			}

			@Override
			public void onFailure(ServerFailure failure) {
				handleFailure(failure.getMessage());
			}
		});
	}

	private void handleFailure(String message) {
		view.setRowData(null);
		view.setError(message);
		panel.setWidget(view);
	}
}
