package org.eastway.echarts.client.forms.view;

import java.util.HashMap;
import java.util.LinkedHashSet;

import org.eastway.echarts.client.forms.presenter.CPSTNotePresenter;
import org.eastway.echarts.client.forms.presenter.IPNNotePresenter;
import org.eastway.echarts.client.forms.presenter.NursingNotePresenter;
import org.eastway.echarts.client.forms.presenter.ProgressNotePresenter;
import org.eastway.echarts.client.presenter.EchartsPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProgressNoteView extends Composite implements ProgressNotePresenter.Display {
	private static ProgressNoteViewUiBinder uiBinder = GWT
			.create(ProgressNoteViewUiBinder.class);

	interface ProgressNoteViewUiBinder extends
				UiBinder<Widget, ProgressNoteView> {}

	@UiField SpanElement patientId, noteName;

	@UiField ListBox serviceCodes, location1, location2, program, modifier1, modifier2;

	@UiField FlowPanel progressNoteBody;

	@UiField Button next, previous, finish;

	@UiField TabLayoutPanel tabPanel;

	public ProgressNoteView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasChangeHandlers getServiceCodesList() {
		return serviceCodes;
	}

	@Override
	public void setData(HashMap<String, LinkedHashSet<HashMap<String, ?>>> data) {
		LinkedHashSet<HashMap<String, ?>> d;

		serviceCodes.addItem("");
		d = data.get("ServiceCodes");
		for (HashMap<String, ?> hm : d)
			serviceCodes.addItem(hm.get("code") + " - " + hm.get("description"), (String) hm.get("templateId"));

		location1.addItem("");
		d = data.get("EastwayLocation");
		for (HashMap<String, ?> hm : d)
			location1.addItem(hm.get("code") + " - " + hm.get("description"), (String) hm.get("code"));

		location2.addItem("");
		d = data.get("MACBMLocation");
		for (HashMap<String, ?> hm : d)
			location2.addItem(hm.get("code") + " - " + hm.get("description"), (String) hm.get("code"));

		program.addItem("");
		d = data.get("EastwayPrograms");
		for (HashMap<String, ?> hm : d)
			program.addItem(hm.get("code") + " - " + hm.get("description"), (String) hm.get("code"));

		modifier1.addItem("");
		d = data.get("MACSISModifier1");
		for (HashMap<String, ?> hm : d)
			modifier1.addItem(hm.get("modifier") + " - " + hm.get("description"), (String) hm.get("modifier"));

		modifier2.addItem("");
		d = data.get("MACSISModifier2");
		for (HashMap<String, ?> hm : d)
			modifier2.addItem(hm.get("modifier") + " - " + hm.get("description"), (String) hm.get("modifier"));
	}

	@Override
	public String getSelectedServiceCode(ChangeEvent event) {
		ListBox selectedItem = (ListBox)event.getSource();
		int i = selectedItem.getSelectedIndex();
		return selectedItem.getValue(i);
	}

	@Override
	public void setNoteBody(EchartsPresenter<?> presenter) {
		if (presenter instanceof CPSTNotePresenter) {
			progressNoteBody.clear();
			progressNoteBody.add((CPSTNoteView)presenter.getDisplay().asWidget());
			noteName.setInnerHTML("CPST Note");
		} else if (presenter instanceof IPNNotePresenter) {
			progressNoteBody.clear();
			progressNoteBody.add((IPNNoteView)presenter.getDisplay().asWidget());
			noteName.setInnerHTML("IPN Note");
		} else if (presenter instanceof NursingNotePresenter) {
			progressNoteBody.clear();
			progressNoteBody.add((NursingNoteView)presenter.getDisplay().asWidget());
			noteName.setInnerHTML("Nursing Note");
		} else {
			com.google.gwt.user.client.Window.alert("");
		}
	}

	@Override
	public void setPatientId(String patientId) {
		this.patientId.setInnerText(patientId);
	}

	@Override
	public HasClickHandlers getNext() {
		return next;
	}

	@Override
	public HasClickHandlers getPrevious() {
		return previous;
	}

	@Override
	public HasClickHandlers getFinish() {
		return finish;
	}

	@Override
	public void doFinish() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doNext() {
		tabPanel.selectTab(tabPanel.getSelectedIndex() + 1);
	}

	@Override
	public void doPrevious() {
		tabPanel.selectTab(tabPanel.getSelectedIndex() - 1);
	}
}
