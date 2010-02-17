package org.eastway.echarts.client.view;

import org.eastway.echarts.client.presenter.PersonalPresenter;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class PersonalView extends Widget implements PersonalPresenter.Display {
	private static PersonalViewUiBinder uiBinder = GWT.create(PersonalViewUiBinder.class);

	interface PersonalViewUiBinder extends UiBinder<Element, PersonalView> {}

	@UiField SpanElement PATID, Name, DOB, SSN;

	public PersonalView() {
		setElement(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(Patient data) {
		Name.setInnerText(data.getName());
		DOB.setInnerText(data.getDob().toString());
		SSN.setInnerText(data.getSsn());
		PATID.setInnerText(data.getPatientId());
	}
}
