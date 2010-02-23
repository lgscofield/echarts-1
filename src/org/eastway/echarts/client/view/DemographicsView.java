package org.eastway.echarts.client.view;

import java.sql.Date;

import org.eastway.echarts.client.presenter.DemographicsPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DemographicsView extends Composite implements DemographicsPresenter.Display {
	private static DemographicsViewUiBinder uiBinder = GWT.create(DemographicsViewUiBinder.class);

	interface DemographicsViewUiBinder extends UiBinder<Widget, DemographicsView> {}

	@UiField SpanElement preferredLanguage, insuranceType, gender, race,
				ethnicity, dob;

	public DemographicsView () {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage.setInnerHTML(preferredLanguage);
	}

	@Override
	public void setInsuranceType(String insuranceType) {
		this.insuranceType.setInnerHTML(insuranceType);
	}

	@Override
	public void setGender(String gender) {
		this.gender.setInnerHTML(gender);
	}

	@Override
	public void setRace(String race) {
		this.race.setInnerHTML(race);
	}

	@Override
	public void setEthnicity(String ethnicity) {
		this.ethnicity.setInnerHTML(ethnicity);
	}

	@Override
	public void setDob(Date date) {
		this.dob.setInnerHTML(date.toString());
	}
}
