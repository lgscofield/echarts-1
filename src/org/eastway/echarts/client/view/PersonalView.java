package org.eastway.echarts.client.view;

import java.util.HashMap;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class PersonalView extends Widget {
	private final PatientServicesAsync patientSvc;
	private static PersonalViewUiBinder uiBinder = GWT.create(PersonalViewUiBinder.class);
	private String firstName;

	interface PersonalViewUiBinder extends UiBinder<Element, PersonalView> {}

	@UiField
	SpanElement PATID, Name, DOB, SSN;

	public PersonalView(String firstName, PatientServicesAsync patientSvc) {
		this.patientSvc = patientSvc;
		this.firstName = firstName;
		setElement(uiBinder.createAndBindUi(this));
		doCallback();
	}

	private void doCallback() {
		AsyncCallback<HashMap<String, String>> callback = new AsyncCallback<HashMap<String, String>>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(HashMap<String, String> result) {
				Name.setInnerText(result.get("Name"));
				DOB.setInnerText(result.get("DOB"));
				SSN.setInnerText(result.get("SSN"));
			};
		};
		PATID.setInnerText(firstName);
		patientSvc.getPatientDemo(firstName, Cookies.getCookie("sessionId"), callback);
	}
}
