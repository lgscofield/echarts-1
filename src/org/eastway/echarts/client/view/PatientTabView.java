package org.eastway.echarts.client.view;

import org.eastway.echarts.client.presenter.PatientTabPresenter;

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

import org.eastway.echarts.client.MessagePanel;

public class PatientTabView extends Composite implements PatientTabPresenter.Display {
	private static PatientTabViewUiBinder uiBinder = GWT.create(PatientTabViewUiBinder.class);

	interface PatientTabViewUiBinder extends
			UiBinder<Widget, PatientTabView> {}

	private String patientId = null;
	@UiField Tree patientMenu;
	@UiField FlowPanel displayArea;


	private TreeItem personal, demographics, progressNote, messages,
			addMessage, treatmentPlan, serviceHistory;

	private IspPanelView ispPanel;
	private MessagePanel messagePanel;

	public PatientTabView() {
		initWidget(uiBinder.createAndBindUi(this));
		this.personal = patientMenu.addItem("Personal");
		this.demographics = patientMenu.addItem("Demographics");
		this.progressNote = patientMenu.addItem("Progress Note");
		this.messages = patientMenu.addItem("Messages");
		this.addMessage = messages.addItem("Add");
		this.treatmentPlan = patientMenu.addItem("Treatment Plan");
		this.serviceHistory = patientMenu.addItem("Service History");
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	@Override
	public String getPatientId() {
		return patientId;
	}

	@Override
	public HasSelectionHandlers<TreeItem> getPatientMenu() {
		return patientMenu;
	}

	@Override
	public void setDisplay(HasText selectedItem) {
		if (selectedItem == personal) {
		} else if (selectedItem == demographics) {
		} else if (selectedItem == progressNote) {
		} else if (selectedItem == messages) {
			displayArea.clear();
			displayArea.add(messagePanel);
		} else if (selectedItem == addMessage) {
		} else if (selectedItem == treatmentPlan) {
			displayArea.clear();
			displayArea.add(ispPanel);
		} else if (selectedItem == serviceHistory) {
		} else {
			displayArea.clear();
			displayArea.add(new HTML("This menu item not yet available"));
		}
	}

	@Override
	public void initDisplayArea() {
		this.ispPanel = new IspPanelView(patientId);
	}

	@Override
	public void setMessages(MessagePanel messagePanel) {
		this.messagePanel = messagePanel;
	}
}
