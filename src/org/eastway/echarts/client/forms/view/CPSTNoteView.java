package org.eastway.echarts.client.forms.view;

import java.util.HashMap;
import java.util.LinkedHashSet;

import org.eastway.echarts.client.forms.presenter.CPSTNotePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CPSTNoteView extends Composite implements CPSTNotePresenter.Display {

	private static CPSTNoteUiBinder uiBinder = GWT
			.create(CPSTNoteUiBinder.class);

	interface CPSTNoteUiBinder extends UiBinder<Widget, CPSTNoteView> { }

	@UiField ListBox nonBillable;
	@UiField ListBox othersPresent;
	@UiField ListBox stageTreatMentalIll;
	@UiField ListBox stageTreatAOD;
	@UiField VerticalPanel activitiesOffered;
	@UiField VerticalPanel therapeuticInterventionsProvided;

	public CPSTNoteView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setNonBillable() {
		nonBillable.addItem("");
		nonBillable.addItem("Cancelled appointment due to scheduling conflict");
		nonBillable.addItem("Attempted to contact client");
		nonBillable.addItem("Prompted client to attend scheduled appointment");
		nonBillable.addItem("Returned client's call and left message");
		nonBillable.addItem("Explained/obtained release/s of information");
	}

	@Override
	public void setData(HashMap<String, LinkedHashSet<HashMap<String, String>>> data) {
		LinkedHashSet<HashMap<String, String>> d;

		d = data.get("ActivitiesOffered");
		for (HashMap<String, String> hm : d) {
			CheckBox c = new CheckBox();
			c.setText(hm.get("description"));
			activitiesOffered.add(c);
		}

		d = data.get("OthersPresent");
		for (HashMap<String, String> hm : d) {
			othersPresent.addItem(hm.get("description"), hm.get("description"));
		}

		d = data.get("StageOfTreatmentMI");
		for (HashMap<String, String> hm : d) {
			stageTreatMentalIll.addItem(hm.get("description"), hm.get("description"));
		}

		d = data.get("StageOfTreatmentAOD");
		for (HashMap<String, String> hm : d) {
			stageTreatAOD.addItem(hm.get("description"), hm.get("description"));
		}

		d = data.get("TherapeuticInterventionsProvided");
		for (HashMap<String, String> hm : d) {
			CheckBox c = new CheckBox();
			c.setText(hm.get("description"));
			therapeuticInterventionsProvided.add(c);
		}
	}
}
