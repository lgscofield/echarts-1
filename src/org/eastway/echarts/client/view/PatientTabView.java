package org.eastway.echarts.client.view;

import org.eastway.echarts.client.presenter.PatientTabPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;

public class PatientTabView extends Composite implements PatientTabPresenter.Display {
	private static PatientTabViewUiBinder uiBinder = GWT.create(PatientTabViewUiBinder.class);

	interface PatientTabViewUiBinder extends
			UiBinder<Widget, PatientTabView> {}

	private String patientId = null;
	@UiField Tree patientMenu;

	public PatientTabView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	@Override
	public String getPatientId() {
		return patientId;
	}
}
