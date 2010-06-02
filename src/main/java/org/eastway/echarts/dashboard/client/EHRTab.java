/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.dashboard.client;

import org.eastway.echarts.client.EHRServices;
import org.eastway.echarts.client.EHRServicesAsync;
import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.presenter.EditPatientSummaryPresenter;
import org.eastway.echarts.client.presenter.LinkPresenter;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.presenter.PatientSummaryPresenter;
import org.eastway.echarts.client.view.EditPatientSummaryView;
import org.eastway.echarts.client.view.LinkView;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.PatientSummaryView;
import org.eastway.echarts.shared.AssignmentDTO;
import org.eastway.echarts.shared.EHRDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class EHRTab extends Composite implements SelectionHandler<TreeItem> {
	private SplitLayoutPanel body = new SplitLayoutPanel();
	private FlowPanel messagesPanel = new FlowPanel();
	private FlowPanel patientPanel = new FlowPanel();
	private FlowPanel editPatientPanel = new FlowPanel();
	private FlowPanel formsPanel = new FlowPanel();
	private ScrollPanel displayarea = new  ScrollPanel();
	private MessagesPresenter mp;
	private PatientSummaryPresenter pp;
	private EditPatientSummaryPresenter ep;
	private LinkPresenter fp;
	private Tree menu = new Tree();
	private EHRDTO ehr;

	public EHRTab(HandlerManager eventBus, RpcServicesAsync rpcServices, EHRDTO ehr) {
		this.setEhr(ehr);
		initWidget(body);
		body.addWest(menu, 150);
		body.add(displayarea);

		TreeItem patientMenuItem = menu.addItem("Patient Summary");
		patientMenuItem.setUserObject(patientPanel);
		pp = new PatientSummaryPresenter(new PatientSummaryView(), eventBus, GWT.<EHRServicesAsync>create(EHRServices.class), ehr.getSubject());
		pp.go(patientPanel);

		//TreeItem editPatientMenuItem = patientMenuItem.addItem("Edit");
		//editPatientMenuItem.setUserObject(editPatientPanel);
		//ep = new EditPatientSummaryPresenter(new EditPatientSummaryView(), eventBus, GWT.<EHRServicesAsync>create(EHRServices.class), ehr.getSubject());
		//ep.go(editPatientPanel);

		TreeItem messageMenuItem = menu.addItem("Messages");
		messageMenuItem.setUserObject(messagesPanel);
		mp = new MessagesPresenter(new MessagesView(), eventBus, rpcServices, ehr);
		mp.go(messagesPanel);

		TreeItem formsMenuItem = menu.addItem("Forms");
		formsMenuItem.setUserObject(formsPanel);
		fp = new LinkPresenter(new LinkView(), eventBus, rpcServices, ehr.getSubject().getCaseNumber());
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

	public void setEhr(EHRDTO ehr) {
		this.ehr = ehr;
	}

	public EHRDTO getEhr() {
		return ehr;
	}
}
