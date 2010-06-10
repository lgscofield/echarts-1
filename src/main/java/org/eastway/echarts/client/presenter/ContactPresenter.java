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
package org.eastway.echarts.client.presenter;

import org.eastway.echarts.shared.Contact;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class ContactPresenter extends Presenter<ContactPresenter.Display> {

	public interface Display extends EchartsDisplay, Contact {
		public void nextRecord();
	}

	private EHR ehr;

	public ContactPresenter(Display display, HandlerManager eventBus, EHR ehr) {
		super(display, eventBus);
		this.ehr = ehr;
	}


	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		setData();
	}

	public void setData() {
		for (Contact contact : ehr.getContacts()) {
			display.setFirstName(contact.getFirstName());
			display.setRelationship(contact.getRelationship());
			display.setType(contact.getType());
			display.setAddresses(contact.getAddresses());
			display.nextRecord();
		}
	}
}
