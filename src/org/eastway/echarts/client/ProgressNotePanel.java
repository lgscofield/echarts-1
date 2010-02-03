package org.eastway.echarts.client;

import org.eastway.echarts.shared.ServiceCode;
import org.eastway.echarts.shared.ServiceCodes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class ProgressNotePanel extends Composite {
	private final PatientServicesAsync patientSvc;
	private static ProgressNotePanelUiBinder uiBinder = GWT.create(ProgressNotePanelUiBinder.class);

	interface ProgressNotePanelUiBinder extends UiBinder<Widget, ProgressNotePanel> {}

	@UiField
	SpanElement nameSpan;

	@UiField
	ListBox serviceCodesListBox;

	@UiField
	HTML progressNoteBody;

	public ProgressNotePanel(String firstName, PatientServicesAsync patientSvc) {
		this.patientSvc = patientSvc;
		initWidget(uiBinder.createAndBindUi(this));
		nameSpan.setInnerText(firstName);
		AsyncCallback<ServiceCodes> serviceCodesCallback = new AsyncCallback<ServiceCodes>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(ServiceCodes result) {
				// Make the first item empty so the change event
				// works
				// for the rest of the service codes
				serviceCodesListBox.addItem("");
				int i = 0;
				while (result.get(i) != null) {
					ServiceCode serviceCode = result.get(i++);
					serviceCodesListBox.addItem(serviceCode.getService()
							.toString()
							+ " " + serviceCode.getDescription(), serviceCode
							.getTemplateId());
				}
			}
		};
		patientSvc.getServiceCodes(Cookies.getCookie("sessionId"), serviceCodesCallback);
	}

	private void getNote(String service) {
		AsyncCallback<String> callback = new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(String result) {
				progressNoteBody.setHTML(result);
			}
		};
		patientSvc.getProgressNoteBody(service, Cookies.getCookie("sessionId"), callback);
		return;
	}

	@UiHandler("serviceCodesListBox")
	void handleChange(ChangeEvent event) {
		String service = null;
		ListBox sender = (ListBox) event.getSource();
		int i = sender.getSelectedIndex();
		service = sender.getValue(i);
		// IE interprets a null string as a string equal to "null"
		if (service.isEmpty() || service.matches("null")) {
			Window.alert("There is no template associated with this service.");
		} else
			getNote(service);
	}
}
