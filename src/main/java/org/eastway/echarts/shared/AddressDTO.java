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
package org.eastway.echarts.shared;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class AddressDTO implements Serializable, Address {

	private String caseNumber;
	private String city;
	private String county;
	private String descriptor;
	private long id;
	private Date lastEdit;
	private String lastEditBy;
	private String phone1;
	private String phone1Desc;
	private String phone2;
	private String phone2Desc;
	private String state;
	private String street1;
	private String street2;
	private String title;
	private String zip;

	public AddressDTO() { }

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public String getCounty() {
		return county;
	}

	@Override
	public String getDescriptor() {
		return descriptor;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public Date getLastEdit() {
		return lastEdit;
	}

	@Override
	public String getLastEditBy() {
		return lastEditBy;
	}

	@Override
	public String getPhone1() {
		return phone1;
	}

	@Override
	public String getPhone1Desc() {
		return phone1Desc;
	}

	@Override
	public String getPhone2() {
		return phone2;
	}

	@Override
	public String getPhone2Desc() {
		return phone2Desc;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public String getStreet1() {
		return street1;
	}

	@Override
	public String getStreet2() {
		return street2;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getZip() {
		return zip;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public void setCounty(String county) {
		this.county = county;
	}

	@Override
	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	@Override
	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	@Override
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	@Override
	public void setPhone1Desc(String phone1Desc) {
		this.phone1Desc = phone1Desc;
	}

	@Override
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	@Override
	public void setPhone2Desc(String phone2Desc) {
		this.phone2Desc = phone2Desc;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	@Override
	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public AddressDTO toDto() {
		return this;
	}
}
