package org.eastway.echarts.client.ui;

import java.util.Iterator;

import org.eastway.echarts.client.place.ARInfoPlace;
import org.eastway.echarts.client.place.AddressPlace;
import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.place.DemographicsPlace;
import org.eastway.echarts.client.place.DiagnosisPlace;
import org.eastway.echarts.client.place.LabPlace;
import org.eastway.echarts.client.place.LinkPlace;
import org.eastway.echarts.client.place.MedicationPlace;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.ReferralPlace;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.place.TreatmentPlanPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class EhrSideBarViewImpl extends Composite implements EhrSideBarView {
	@SuppressWarnings("unchecked")
	@UiTemplate("EhrSideBarView.ui.xml")
	interface EhrSideBarViewUiBinder extends UiBinder<Widget, EhrSideBarViewImpl> { }

	private static EhrSideBarViewUiBinder BINDER = GWT.create(EhrSideBarViewUiBinder.class);
	private EhrSideBarView.Presenter presenter;
	private String id;

	@UiField Tree menu;

	@UiField Label patientSummary;
	@UiField Label messages;
	@UiField Label demographics;
	@UiField Label aRInfo;
	@UiField Label referral;
	@UiField Label addresses;
	@UiField Label diagnoses;
	@UiField Label appointments;
	@UiField Label medications;
	@UiField Label labs;
	@UiField Label links;
	@UiField Label serviceHistory;
	@UiField Label treatmentPlan;

	public EhrSideBarViewImpl() {
		initWidget(BINDER.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@UiHandler("patientSummary")
	void onPatientSummarySelected(ClickEvent event) {
		presenter.goTo(new PatientSummaryPlace(id));
	}

	@UiHandler("messages")
	void onMessagesSelected(ClickEvent event) {
		presenter.goTo(new MessagePlace(id));
	}

	@UiHandler("demographics")
	void onDemographicsSelected(ClickEvent event) {
		presenter.goTo(new DemographicsPlace(id));
	}

	@UiHandler("referral")
	void onReferralSelected(ClickEvent event) {
		presenter.goTo(new ReferralPlace(id));
	}

	@UiHandler("appointments")
	void onAppointmentsSelected(ClickEvent event) {
		presenter.goTo(new AppointmentPlace(id));
	}

	@UiHandler("diagnoses")
	void onDiagnosesSelected(ClickEvent event) {
		presenter.goTo(new DiagnosisPlace(id));
	}

	@UiHandler("links")
	void onLinksSelected(ClickEvent event) {
		presenter.goTo(new LinkPlace(id));
	}

	@UiHandler("addresses")
	void onAddressesSelected(ClickEvent event) {
		presenter.goTo(new AddressPlace(id));
	}

	@UiHandler("medications")
	void onMedicationsSelected(ClickEvent event) {
		presenter.goTo(new MedicationPlace(id));
	}

	@UiHandler("labs")
	void onLabsSelected(ClickEvent event) {
		presenter.goTo(new LabPlace(id));
	}

	@UiHandler("treatmentPlan")
	void onIspSelected(ClickEvent event) {
		presenter.goTo(new TreatmentPlanPlace(id));
	}

	@UiHandler("serviceHistory")
	void onServiceHistorySelected(ClickEvent event) {
		presenter.goTo(new ServiceHistoryPlace(id));
	}

	@UiHandler("aRInfo")
	void onARInfoSelected(ClickEvent event) {
		presenter.goTo(new ARInfoPlace(id));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	private TreeItem getTreeItem(Label label) {
		Iterator<TreeItem> iterator = menu.treeItemIterator();

		while (iterator.hasNext()) {
			TreeItem item = iterator.next();
			if (item.getWidget().equals(label))
					return item;
		}
		return null;
	}

	@Override
	public void selectPatientSummaryPlace() {
		menu.setSelectedItem(getTreeItem(patientSummary));
	}

	@Override
	public void selectMessagePlace() {
		menu.setSelectedItem(getTreeItem(messages));
	}

	@Override
	public void selectDemographicsPlace() {
		menu.setSelectedItem(getTreeItem(demographics));
	}

	@Override
	public void selectReferralPlace() {
		menu.setSelectedItem(getTreeItem(referral));
	}

	@Override
	public void selectAppointmentPlace() {
		menu.setSelectedItem(getTreeItem(appointments));
	}

	@Override
	public void selectDiagnosisPlace() {
		menu.setSelectedItem(getTreeItem(diagnoses));
	}

	@Override
	public void selectLinkPlace() {
		menu.setSelectedItem(getTreeItem(links));
	}

	@Override
	public void selectAddressPlace() {
		menu.setSelectedItem(getTreeItem(addresses));
	}

	@Override
	public void selectMedicationPlace() {
		menu.setSelectedItem(getTreeItem(medications));
	}

	@Override
	public void selectTreatmentPlanPlace() {
		menu.setSelectedItem(getTreeItem(treatmentPlan));
	}

	@Override
	public void selectServiceHistoryPlace() {
		menu.setSelectedItem(getTreeItem(serviceHistory));
	}

	@Override
	public void selectARInfoPlace() {
		menu.setSelectedItem(getTreeItem(aRInfo));
	}
}
