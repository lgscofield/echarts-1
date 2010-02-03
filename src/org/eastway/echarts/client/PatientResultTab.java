package org.eastway.echarts.client;

import java.util.HashMap;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class PatientResultTab implements SelectionHandler<TreeItem> {
	private final PatientServicesAsync patientSvc;
	private FlowPanel displayArea = new FlowPanel();
	private Tree tree = new Tree();
	private HorizontalPanel tabTxt = new HorizontalPanel();
	private DockLayoutPanel patientDataPanel = new DockLayoutPanel(Unit.EM);
	private String patientId;
	private TabLayoutPanel tp;
	private FlexTable demoData = new FlexTable();
	private Label removeTab = new Label();
	private TreeItem personalDataTreeItem = new TreeItem();
	private TreeItem demographicsTreeItem = new TreeItem();
	private TreeItem progressNotesTreeItem = new TreeItem();
	private TreeItem messageTreeItem = new TreeItem();
	private TreeItem addMessageTreeItem = new TreeItem();
	private TreeItem treatmentPlanTreeItem = new TreeItem();
	private TreeItem serviceHistoryTreeItem = new TreeItem();
	private PersonalDataPanel personalDataPanel;
	private ProgressNotePanel progressNote;
	private MessagePanel messagePanel;
	private ServiceHistory serviceHistoryPanel;
	private IspPanel ispPanel;

	public PatientResultTab(String patientId, TabLayoutPanel tp, PatientServicesAsync patientSvc) {
		this.patientId = patientId;
		this.tp = tp;
		this.patientSvc = patientSvc;
		getPatientResultTab();
		loadData();
	}

	private void loadData() {
		personalDataPanel = new PersonalDataPanel(patientId, patientSvc);
		progressNote = new ProgressNotePanel(patientId, patientSvc);
		messagePanel = new MessagePanel(patientId, patientSvc);
		serviceHistoryPanel = new ServiceHistory(patientId);
		ispPanel = new IspPanel(patientId);
	}

	private void getPatientResultTab() {
		displayArea.setHeight("100%");
		AsyncCallback<HashMap<String, String>> callback = new AsyncCallback<HashMap<String, String>>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(HashMap<String, String> result) {
				removeTab.addStyleName("close-Tab");
				removeTab.setTitle("Close");
				removeTab.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						Integer idx = tp.getWidgetIndex(patientDataPanel);
						if (tp.remove(idx))
							tp.selectTab(idx - 1);
						else
							Window.alert("Could not find a tab to close!");
					}
				});
				demoData.setText(4, 0, "MaritalStatus :");
				demoData.setText(4, 1, result.get("MaritalStatus"));
				demoData.setText(5, 0, "Education Level :");
				demoData.setText(5, 1, result.get("EducationLevel"));
				demoData.setText(6, 0, "Education Type :");
				demoData.setText(6, 1, result.get("EducationType"));
				demoData.setText(7, 0, "Current Living Arrangement :");
				demoData.setText(7, 1, result.get("LivingArrangement"));
				demoData.setText(8, 0, "Employment :");
				demoData.setText(8, 1, result.get("Employment"));
				demoData.setText(9, 0, "Income Source :");
				demoData.setText(9, 1, result.get("IncomeSource1"));
				demoData.setText(9, 2, result.get("IncomeSource2"));
				demoData.setText(9, 3, result.get("IncomeSource3"));
				demoData.setText(10, 0, "Religion :");
				demoData.setText(10, 1, result.get("Religion"));
				demoData.setCellPadding(2);
				personalDataTreeItem = tree.addItem("Patient Data");
				demographicsTreeItem = tree.addItem("Demographics");
				progressNotesTreeItem = tree.addItem("Progress Notes");
				tree.addItem("Assessment");
				messageTreeItem = tree.addItem("Messages");
				addMessageTreeItem = messageTreeItem.addItem("Add message");
				tree.addItem("Narrative Summaries");
				treatmentPlanTreeItem = tree.addItem("Treatment Plan");
				tree.addItem("Financial");
				tree.addItem("Authorizations");
				tree.addItem("Ticket to work");
				tree.addItem("Employment");
				tree.addItem("Legal");
				tree.addItem("Physician Orders");
				serviceHistoryTreeItem = tree.addItem("Service History");
				tree.addItem("Lab");
				tree.addSelectionHandler(PatientResultTab.this);
				patientDataPanel.addWest(tree, 15);
				tree.setSelectedItem(personalDataTreeItem);
				patientDataPanel.add(displayArea);
				tp.add(patientDataPanel, tabTxt);
				tp.selectTab(tp.getWidgetIndex(patientDataPanel));
				tabTxt.add(new HTML(patientId));
				tabTxt.add(removeTab);
			}
		};
		patientSvc.getPatientDemo(patientId, Cookies.getCookie("sessionId"), callback);
	}

	@Override
	public void onSelection(SelectionEvent<TreeItem> event) {
		Object sender = event.getSource();
		if (sender == tree) {
			displayArea.clear();
			TreeItem t = tree.getSelectedItem();
			if (t == personalDataTreeItem) {
				displayArea.add(personalDataPanel);
			} else if (t == demographicsTreeItem) {
				displayArea.add(demoData);
			} else if (t == progressNotesTreeItem) {
				displayArea.add(progressNote);
			} else if (t == messageTreeItem) {
				displayArea.add(messagePanel);
			} else if (t == addMessageTreeItem) {
				displayArea.add(messagePanel);
				messagePanel.showAddMessage();
			} else if (t == serviceHistoryTreeItem) {
				displayArea.add(serviceHistoryPanel);
			} else if (t == treatmentPlanTreeItem) {
				displayArea.add(ispPanel);
			} else {
				displayArea.add(new HTML(t.getText() + " coming soon ..."));
			}
		}
	}
}
