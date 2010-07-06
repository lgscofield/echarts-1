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

	enum Column {
		ADDRESS_CASE_NUMBER,
		ADDRESS_CITY,
		ADDRESS_COUNTY,
		ADDRESS_DESCRIPTOR,
		ADDRESS_LAST_EDIT,
		ADDRESS_LAST_EDIT_BY,
		ADDRESS_PHONE1,
		ADDRESS_PHONE1_DESC,
		ADDRESS_PHONE2,
		ADDRESS_PHONE2_DESC,
		ADDRESS_STATE,
		ADDRESS_STREET1,
		ADDRESS_STREET2,
		ADDRESS_TITLE,
		ADDRESS_ZIP,
	}

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

	// This should be done in the presenter...
	@Override
	public void setAddresses(List<Address> addresses) {
		for (Address address : addresses) {
			this.contacts.setText(record, Column.ADDRESS_CASE_NUMBER.ordinal(), address.getCaseNumber());
			this.contacts.setText(record, Column.ADDRESS_CITY.ordinal(), address.getCity());
			this.contacts.setText(record, Column.ADDRESS_COUNTY.ordinal(), address.getCounty());
			this.contacts.setText(record, Column.ADDRESS_DESCRIPTOR.ordinal(), address.getDescriptor());
			this.contacts.setText(record, Column.ADDRESS_LAST_EDIT.ordinal(), address.getLastEdit().toString());
			this.contacts.setText(record, Column.ADDRESS_LAST_EDIT_BY.ordinal(), address.getLastEditBy());
			this.contacts.setText(record, Column.ADDRESS_PHONE1.ordinal(),address.getPhone1());
			this.contacts.setText(record, Column.ADDRESS_PHONE1_DESC.ordinal(), address.getPhone1Desc());
			this.contacts.setText(record, Column.ADDRESS_PHONE2.ordinal(), address.getPhone2());
			this.contacts.setText(record, Column.ADDRESS_PHONE2_DESC.ordinal(), address.getPhone2Desc());
			this.contacts.setText(record, Column.ADDRESS_STATE.ordinal(), address.getState());
			this.contacts.setText(record, Column.ADDRESS_STREET1.ordinal(), address.getStreet1());
			this.contacts.setText(record, Column.ADDRESS_STREET2.ordinal(), address.getStreet2());
			this.contacts.setText(record, Column.ADDRESS_TITLE.ordinal(), address.getTitle());
			this.contacts.setText(record, Column.ADDRESS_ZIP.ordinal(), address.getZip());
			nextRecord();
		}
	}

	@Override
	public String getCaseNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		// TODO Auto-generated method stub
		
	}
}
