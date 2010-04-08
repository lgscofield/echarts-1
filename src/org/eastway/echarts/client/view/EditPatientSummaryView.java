package org.eastway.echarts.client.view;

import org.eastway.echarts.client.presenter.EditPatientSummaryPresenter;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class EditPatientSummaryView extends Composite implements EditPatientSummaryPresenter.Display {
	@Override
	public Widget asWidget() {
		return this;
	}
}
