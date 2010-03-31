package org.eastway.echarts.dashboard.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

public class GMHIntakeWizard extends Composite implements SelectionHandler<TreeItem> {

	private static GMHIntakeWizardUiBinder uiBinder = GWT
			.create(GMHIntakeWizardUiBinder.class);

	interface GMHIntakeWizardUiBinder extends
			UiBinder<Widget, GMHIntakeWizard> {
	}

	@UiField DockLayoutPanel panel;
	@UiField Tree steps;
	@UiField TabLayoutPanel wizard;
	@UiField HTMLPanel returningPatient;
	@UiField TextBox previousProvider;
	@UiField DateBox previousVisit;

	@UiField DatePicker firstAppointmentOfferedDatePicker;
	@UiField Label firstAppointmentOffered;

	@UiField DatePicker firstAppointmentTakenDatePicker;
	@UiField Label firstAppointmentTaken;

	@UiField Button next, previous, finish;

	private TreeItem stepOne;
	private TreeItem stepTwo;
	private TreeItem stepThree;
	private TreeItem stepFour;
	private TreeItem stepFive;
	private TreeItem stepSix;
	private TreeItem stepSeven;

	private TreeItem[] stepList = new TreeItem[] {
			stepOne,
			stepTwo,
			stepThree,
			stepFour,
			stepFive,
			stepSix,
			stepSeven
	};

	public GMHIntakeWizard() {
		initWidget(uiBinder.createAndBindUi(this));

		stepList[0] = steps.addItem("Step 1");
		stepList[1] = steps.addItem("Step 2");
		stepList[2] = steps.addItem("Step 3");
		stepList[3] = steps.addItem("Step 4");
		stepList[4] = steps.addItem("Step 5");
		stepList[5] = steps.addItem("Step 6");
		stepList[6] = steps.addItem("Step 7");
		steps.setSelectedItem(stepList[0]);

		steps.addSelectionHandler(this);

		DateTimeFormat dateFormat = DateTimeFormat.getMediumDateFormat();
		previousVisit.setFormat(new DateBox.DefaultFormat(dateFormat));
	}

	@Override
	public void onSelection(SelectionEvent<TreeItem> event) {
		TreeItem item = event.getSelectedItem();
		if (item.equals(stepList[0])) {
			previous.setEnabled(false);
			next.setEnabled(true);
			wizard.selectTab(0);
		} else if (item.equals(stepList[1])) {
			previous.setEnabled(true);
			next.setEnabled(true);
			wizard.selectTab(1);
		} else if (item.equals(stepList[2])) {
			previous.setEnabled(true);
			next.setEnabled(true);
			wizard.selectTab(2);
		} else if (item.equals(stepList[3])) {
			previous.setEnabled(true);
			next.setEnabled(true);
			wizard.selectTab(3);
		} else if (item.equals(stepList[4])) {
			previous.setEnabled(true);
			next.setEnabled(true);
			wizard.selectTab(4);
		} else if (item.equals(stepList[5])) {
			next.setEnabled(true);
			previous.setEnabled(true);
			wizard.selectTab(5);
		} else if (item.equals(stepList[6])) {
			next.setEnabled(false);
			previous.setEnabled(true);
			finish.setEnabled(true);
			wizard.selectTab(6);
		}
	}


	@UiHandler(value = { "next"})
	void handleNext(ClickEvent event) {
		for (int i = 0; i < stepList.length; i++) {
			if (stepList[i].equals(steps.getSelectedItem()))
				steps.setSelectedItem(stepList[++i]);
		}
	}

	@UiHandler(value = { "previous"})
	void handlePrevious(ClickEvent event) {
		for (int i = stepList.length - 1; i > 0; i--) {
			if (stepList[i].equals(steps.getSelectedItem()))
				steps.setSelectedItem(stepList[--i]);
		}
	}

	@UiHandler(value = { "finish"})
	void handleFinish(ClickEvent event) {
		// TODO : do something really cool with the data
	}

	@UiHandler(value = { "firstAppointmentOfferedDatePicker"})
	void handleFirstAppointmentOffered(ValueChangeEvent<Date> event) {
		Date date = event.getValue();
		String dateString = DateTimeFormat.getMediumDateFormat().format(date);
		firstAppointmentOffered.setText(dateString);
	}

	@UiHandler(value = { "firstAppointmentTakenDatePicker"})
	void handleFirstAppointmentTaken(ValueChangeEvent<Date> event) {
		Date date = event.getValue();
		String dateString = DateTimeFormat.getMediumDateFormat().format(date);
		firstAppointmentTaken.setText(dateString);
	}
}
