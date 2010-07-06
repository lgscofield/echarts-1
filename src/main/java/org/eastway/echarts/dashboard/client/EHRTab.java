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

import org.eastway.echarts.client.AddressServices;
import org.eastway.echarts.client.AddressServicesAsync;
import org.eastway.echarts.client.EHRServices;
import org.eastway.echarts.client.EHRServicesAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.ReferralServices;
import org.eastway.echarts.client.ReferralServicesAsync;
import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.presenter.AddressPresenter;
import org.eastway.echarts.client.presenter.AppointmentPresenter;
import org.eastway.echarts.client.presenter.ContactPresenter;
import org.eastway.echarts.client.presenter.DemographicsPresenter;
import org.eastway.echarts.client.presenter.DiagnosisPresenter;
import org.eastway.echarts.client.presenter.LinkPresenter;
import org.eastway.echarts.client.presenter.MedicationPresenter;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.presenter.PatientSummaryPresenter;
import org.eastway.echarts.client.presenter.ReferralPresenter;
import org.eastway.echarts.client.view.AddressView;
import org.eastway.echarts.client.view.AppointmentView;
import org.eastway.echarts.client.view.ContactView;
import org.eastway.echarts.client.view.DemographicsView;
import org.eastway.echarts.client.view.DiagnosisView;
import org.eastway.echarts.client.view.LinkView;
import org.eastway.echarts.client.view.MedicationView;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.PatientSummaryView;
import org.eastway.echarts.client.view.ReferralView;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	private FlowPanel demographicsPanel = new FlowPanel();
	private FlowPanel referralPanel = new FlowPanel();
	private FlowPanel addressPanel = new FlowPanel();
	private FlowPanel contactPanel = new FlowPanel();
	private FlowPanel diagnosisPanel = new FlowPanel();
	private FlowPanel appointmentPanel = new FlowPanel();
	private FlowPanel medicationPanel = new FlowPanel();
	private FlowPanel formsPanel = new FlowPanel();
	private ScrollPanel displayarea = new  ScrollPanel();
	private MessagesPresenter mp;
	private PatientSummaryPresenter pp;
	private DemographicsPresenter dp;
	private ReferralPresenter rp;
	private AddressPresenter ap;
	private ContactPresenter cp;
	private DiagnosisPresenter diagp;
	private AppointmentPresenter appointp;
	private MedicationPresenter medp;
	private LinkPresenter fp;
	private Tree menu = new Tree();
	private EHR ehr;
	private EHRServicesAsync ehrServices = GWT.<EHRServicesAsync>create(EHRServices.class);

	public EHRTab(HandlerManager eventBus, RpcServicesAsync rpcServices, long ehrId) {
		fetchEhr(ehrId);
		initWidget(body);
		body.addWest(menu, 150);
		body.add(displayarea);

//		TreeItem patientMenuItem = menu.addItem("Patient Summary");
//		patientMenuItem.setUserObject(patientPanel);
//		pp = new PatientSummaryPresenter(new PatientSummaryView(), eventBus, ehrServices, ehr.toDto());
//		pp.go(patientPanel);

		//TreeItem editPatientMenuItem = patientMenuItem.addItem("Edit");
		//editPatientMenuItem.setUserObject(editPatientPanel);
		//ep = new EditPatientSummaryPresenter(new EditPatientSummaryView(), eventBus, GWT.<EHRServicesAsync>create(EHRServices.class), ehr.getSubject());
		//ep.go(editPatientPanel);

//		TreeItem demographicsMenuItem = menu.addItem("Demographics");
//		demographicsMenuItem.setUserObject(demographicsPanel);
//		dp = new DemographicsPresenter(new DemographicsView(), eventBus, ehrServices, ehr);
//		dp.go(demographicsPanel);

//		TreeItem referralMenuItem = menu.addItem("Referral");
//		referralMenuItem.setUserObject(referralPanel);
//		rp = new ReferralPresenter(new ReferralView(), eventBus, GWT.<ReferralServicesAsync>create(ReferralServices.class), ehr);
//		rp.go(referralPanel);

//		TreeItem addressMenuItem = menu.addItem("Addresses");
//		addressMenuItem.setUserObject(addressPanel);
//		ap = new AddressPresenter(new AddressView(), eventBus, GWT.<AddressServicesAsync>create(AddressServices.class), ehr);
//		ap.go(addressPanel);

//		TreeItem contactMenuItem = menu.addItem("Contacts");
//		contactMenuItem.setUserObject(contactPanel);
//		cp = new ContactPresenter(new ContactView(), eventBus, ehr);
//		cp.go(contactPanel);

//		TreeItem diagnosisMenuItem = menu.addItem("Diagnoses");
//		diagnosisMenuItem.setUserObject(diagnosisPanel);
//		diagp = new DiagnosisPresenter(new DiagnosisView(), eventBus, ehr);
//		diagp.go(diagnosisPanel);

//		TreeItem appointmentMenuItem = menu.addItem("Appointments");
//		appointmentMenuItem.setUserObject(appointmentPanel);
//		appointp = new AppointmentPresenter(new AppointmentView(), eventBus, ehr);
//		appointp.go(appointmentPanel);

//		TreeItem medicationMenuItem = menu.addItem("Medications");
//		medicationMenuItem.setUserObject(medicationPanel);
//		medp = new MedicationPresenter(new MedicationView(), eventBus, ehr);
//		medp.go(medicationPanel);

		TreeItem messageMenuItem = menu.addItem("Messages");
		messageMenuItem.setUserObject(messagesPanel);
		//mp = new MessagesPresenter(new MessagesView(), eventBus, rpcServices, ehr);
		mp.go(messagesPanel);

//		TreeItem formsMenuItem = menu.addItem("Forms");
//		formsMenuItem.setUserObject(formsPanel);
//		fp = new LinkPresenter(new LinkView(), eventBus, rpcServices, ehr.getSubject().getCaseNumber());
//		fp.go(formsPanel);

		menu.addSelectionHandler(this);
		setTreeItemWidth();
	}

	private void fetchEhr(long ehrId) {
		AsyncCallback<EHR> callback = new AsyncCallback<EHR>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(EHR ehr) {
				setEhr(ehr);
			}
		};
		ehrServices.getEhr(ehrId, Cookies.getCookie("sessionId"), callback);
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

	public void setEhr(EHR ehr) {
		this.ehr = ehr;
	}

	public EHR getEhr() {
		return ehr;
	}
}
