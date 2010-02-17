package org.eastway.echarts.client.view;

import org.eastway.echarts.client.presenter.ProgressNotePresenter;
import org.eastway.echarts.shared.ServiceCode;
import org.eastway.echarts.shared.ServiceCodes;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class ProgressNoteView extends Composite implements ProgressNotePresenter.Display {
	private static ProgressNoteViewUiBinder uiBinder = GWT
			.create(ProgressNoteViewUiBinder.class);

	interface ProgressNoteViewUiBinder extends
				UiBinder<Widget, ProgressNoteView> {}

	@UiField SpanElement patientId;

	@UiField ListBox serviceCodesListBox;

	@UiField HTML progressNoteBody;

	public ProgressNoteView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasChangeHandlers getServiceCodesList() {
		return serviceCodesListBox;
	}

	@Override
	public void setData(ServiceCodes data) {
		// make the first item empty so the change event works for the
		// reset of the service codes
		serviceCodesListBox.addItem("");
		for (int i = 0; data.get(i) != null; i++) {
			ServiceCode serviceCode = data.get(i);
			serviceCodesListBox.addItem(serviceCode.getService()
					.toString()
					+ " " + serviceCode.getDescription(),
					serviceCode.getTemplateId());
		}
	}

	@Override
	public String getSelectedServiceCode(ChangeEvent event) {
		ListBox selectedItem = (ListBox)event.getSource();
		int i = selectedItem.getSelectedIndex();
		return selectedItem.getValue(i);
	}

	@Override
	public void setNoteBody(String body) {
		progressNoteBody.setHTML(body);
	}

	@Override
	public void setPatientId(String patientId) {
		this.patientId.setInnerText(patientId);
	}
}
