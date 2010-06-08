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
package org.eastway.echarts.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import org.eastway.echarts.client.presenter.ContactPresenter;
import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.ContactDTO;

public class ContactView extends Composite implements ContactPresenter.Display {

	private static ContactViewUiBinder uiBinder = GWT
			.create(ContactViewUiBinder.class);

	interface ContactViewUiBinder extends UiBinder<Widget, ContactView> { }

	@UiField FlexTable contacts;
	private int record = 0;

	public ContactView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void addAddress(Address address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Address> getAddresses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRelationship() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFirstName(String firstName) {
		this.contacts.setText(record, 0, firstName);
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRelationship(String relationship) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ContactDTO toDto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nextRecord() {
		record++;
	}
}
