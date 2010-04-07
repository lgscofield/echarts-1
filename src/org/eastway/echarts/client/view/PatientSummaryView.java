package org.eastway.echarts.client.view;

import java.util.LinkedHashSet;

import org.eastway.echarts.client.presenter.PatientSummaryPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class PatientSummaryView extends Composite implements PatientSummaryPresenter.Display {
	private static Binder uiBinder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, PatientSummaryView> {}

	@UiField FlexTable ft1;
	@UiField FlexTable ft2;

	interface Style extends CssResource {
	}

	@UiField Style style;

	public PatientSummaryView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(LinkedHashSet<String[]> data) {
		int i = 0;
		for (String[] s : data) {
			for (int j = 0; j < s.length; j++) {
				ft1.setHTML(i, j, s[j]);
			}
			i++;
		}
	}
}
