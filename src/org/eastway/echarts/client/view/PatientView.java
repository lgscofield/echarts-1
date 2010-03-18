package org.eastway.echarts.client.view;

import java.util.LinkedHashSet;

import org.eastway.echarts.client.presenter.PatientPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

public class PatientView extends Composite implements PatientPresenter.Display {
	private static PatientViewUiBinder uiBinder = GWT.create(PatientViewUiBinder.class);

	interface PatientViewUiBinder extends UiBinder<Widget, PatientView> {}

	@UiField FlexTable ft1;
	@UiField FlexTable ft2;

	interface PatientViewStyle extends CssResource {
	}

	@UiField PatientViewStyle style;

	public PatientView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(LinkedHashSet<String[]> data, int ncols) {
		int i = 0;
		for (String[] s : data) {
			for (int j = 0; j < ncols; j++)
				ft1.setHTML(i, j, s[j]);
			i++;
		}
	}
}
