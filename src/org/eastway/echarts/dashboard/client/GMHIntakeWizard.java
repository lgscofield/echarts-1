package org.eastway.echarts.dashboard.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class GMHIntakeWizard extends Composite implements SelectionHandler<TreeItem> {

	private static GMHIntakeWizardUiBinder uiBinder = GWT
			.create(GMHIntakeWizardUiBinder.class);

	interface GMHIntakeWizardUiBinder extends
			UiBinder<Widget, GMHIntakeWizard> {
	}

	@UiField DockLayoutPanel panel;
	@UiField Tree steps;
	@UiField TabLayoutPanel wizard;
	@UiField CheckBox isPreviousPatient;
	@UiField HTMLPanel returningPatient;
	@UiField TextBox previousProvider;
	@UiField DateBox previousVisit;

	private TreeItem stepOne;
	private TreeItem stepTwo;

	public GMHIntakeWizard() {
		initWidget(uiBinder.createAndBindUi(this));
		stepOne = steps.addItem("Step 1");
		stepTwo = steps.addItem("Step 2");
		steps.addSelectionHandler(this);
		DateTimeFormat dateFormat = DateTimeFormat.getShortDateFormat();
		previousVisit.setFormat(new DateBox.DefaultFormat(dateFormat));
	}

	@Override
	public void onSelection(SelectionEvent<TreeItem> event) {
		TreeItem item = event.getSelectedItem();
		if (item.equals(stepOne))
			wizard.selectTab(0);
		else if (item.equals(stepTwo))
			wizard.selectTab(1);
	}

	@UiHandler(value = { "isPreviousPatient" })
	void handleClick(ClickEvent event) {
		if (isPreviousPatient.getValue()) {
			returningPatient.setVisible(true);
		} else {
			returningPatient.setVisible(false);
			previousVisit.setValue(null);
			previousProvider.setValue(null);
		}
	}
}