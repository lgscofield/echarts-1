package org.eastway.echarts.tab.client;

import org.eastway.echarts.client.Rpc;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.presenter.PatientPresenter;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.PatientView;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class PatientTabBody extends Composite implements SelectionHandler<TreeItem> {
	private FlowPanel body = new FlowPanel();
	private FlowPanel messagesPanel = new FlowPanel();
	private FlowPanel patientPanel = new FlowPanel();
	private FlowPanel displayarea = new FlowPanel();
	private MessagesPresenter mp;
	private PatientPresenter pp;
	private Tree menu = new Tree();

	public PatientTabBody(HandlerManager eventBus, String patientId) {
		initWidget(body);
		body.add(menu);
		body.add(displayarea);
		TreeItem messageMenuItem = menu.addItem("Messages");
		messageMenuItem.setUserObject(messagesPanel);
		TreeItem patientMenuItem = menu.addItem("Patient");
		patientMenuItem.setUserObject(patientPanel);
		mp = new MessagesPresenter(new MessagesView(), eventBus, Rpc.singleton(), patientId);
		mp.go(messagesPanel);
		pp = new PatientPresenter(new PatientView(), eventBus, Rpc.singleton(), patientId);
		pp.go(patientPanel);
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
