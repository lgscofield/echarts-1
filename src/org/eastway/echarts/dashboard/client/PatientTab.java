package org.eastway.echarts.dashboard.client;

import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.presenter.EditPatientSummaryPresenter;
import org.eastway.echarts.client.presenter.FormsPresenter;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.presenter.PatientSummaryPresenter;
import org.eastway.echarts.client.view.EditPatientSummaryView;
import org.eastway.echarts.client.view.FormsView;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.PatientSummaryView;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class PatientTab extends Composite implements SelectionHandler<TreeItem> {
	private SplitLayoutPanel body = new SplitLayoutPanel();
	private FlowPanel messagesPanel = new FlowPanel();
	private FlowPanel patientPanel = new FlowPanel();
	private FlowPanel editPatientPanel = new FlowPanel();
	private FlowPanel formsPanel = new FlowPanel();
	private FlowPanel displayarea = new FlowPanel();
	private MessagesPresenter mp;
	private PatientSummaryPresenter pp;
	private EditPatientSummaryPresenter ep;
	private FormsPresenter fp;
	private Tree menu = new Tree();

	public PatientTab(HandlerManager eventBus, RpcServicesAsync rpcServices, Patient patient) {
		initWidget(body);
		body.addWest(menu, 150);
		body.add(displayarea);

		TreeItem patientMenuItem = menu.addItem("Patient Summary");
		patientMenuItem.setUserObject(patientPanel);
		pp = new PatientSummaryPresenter(new PatientSummaryView(), eventBus, rpcServices, patient);
		pp.go(patientPanel);

		TreeItem editPatientMenuItem = patientMenuItem.addItem("Edit");
		editPatientMenuItem.setUserObject(editPatientPanel);
		ep = new EditPatientSummaryPresenter(new EditPatientSummaryView(), eventBus, rpcServices, patient);
		ep.go(editPatientPanel);

		TreeItem messageMenuItem = menu.addItem("Messages");
		messageMenuItem.setUserObject(messagesPanel);
		mp = new MessagesPresenter(new MessagesView(), eventBus, rpcServices, patient.getCaseNumber());
		mp.go(messagesPanel);

		TreeItem formsMenuItem = menu.addItem("Forms");
		formsMenuItem.setUserObject(formsPanel);
		fp = new FormsPresenter(new FormsView(), eventBus, rpcServices, patient.getCaseNumber());
		fp.go(formsPanel);

		menu.addSelectionHandler(this);
		setTreeItemWidth();
	}

	@Override
	public void onSelection(SelectionEvent<TreeItem> event) {
		TreeItem ti = event.getSelectedItem();
		displayarea.clear();
		displayarea.add((FlowPanel) ti.getUserObject());
	}

	void setTreeItemWidth() {
		int items = menu.getItemCount();
		for (int i = 0; i < items; i++)
			menu.getItem(i).setWidth("0px");
	}
}