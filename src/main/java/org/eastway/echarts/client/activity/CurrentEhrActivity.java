package org.eastway.echarts.client.activity;

import java.util.List;

import org.eastway.echarts.client.request.AssignmentProxy;
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
		EhrRequest context = requestFactory.ehrRequest();
		context.findEHRByCaseNumber(caseNumber)
			.with("patient")
			.with("patient.caseStatus")
			.with("demographics")
			.with("demographics.gender").to(new Receiver<EHRProxy>() {
				@Override
				public void onSuccess(EHRProxy response) {
					if (response != null)
						view.setRowData(response);
					else
						handleFailure(null);
				}
			});
		context.findAssignmentsByCaseNumber(caseNumber).to(new Receiver<List<AssignmentProxy>>() {
			@Override
			public void onSuccess(List<AssignmentProxy> response) {
				if (response != null && response.size() != 0)
					view.setProvider(response);
				else
					handleFailure(null);
			}

			@Override
			public void onFailure(ServerFailure failure) {
				handleFailure(failure.getMessage());
			}
		});
		context.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				panel.setWidget(view);
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
