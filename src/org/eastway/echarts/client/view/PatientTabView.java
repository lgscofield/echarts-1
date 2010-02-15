package org.eastway.echarts.client.view;

import org.eastway.echarts.client.presenter.PatientTabPresenter;
import org.eastway.echarts.client.view.ServiceHistoryView;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class PatientTabView extends Composite implements PatientTabPresenter.Display {
	private static PatientTabViewUiBinder uiBinder = GWT.create(PatientTabViewUiBinder.class);

	interface PatientTabViewUiBinder extends
			UiBinder<Widget, PatientTabView> {}

	private String patientId = null;
	@UiField Tree menu;
	@UiField FlowPanel displayArea;

	private TreeItem personal, demographics, progressNote, messages,
			addMessage, treatmentPlan, serviceHistory;

	public PatientTabView() {
		initWidget(uiBinder.createAndBindUi(this));
		buildMenu();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasSelectionHandlers<TreeItem> getMenu() {
		return menu;
	}

	@Override
	public void setDisplay(Widget menuItemUserObject) {
		displayArea.clear();
		displayArea.add(menuItemUserObject);
	}

	private void buildMenu() {
		personal = menu.addItem("Personal");

		demographics = menu.addItem("Demographics");

		progressNote = menu.addItem("Progress Note");

		messages = menu.addItem("Messages");

		addMessage = messages.addItem("Add");

		treatmentPlan = menu.addItem("Treatment Plan");

		serviceHistory = menu.addItem("Service History");
		serviceHistory.setUserObject(new ServiceHistoryView());
	}
}
