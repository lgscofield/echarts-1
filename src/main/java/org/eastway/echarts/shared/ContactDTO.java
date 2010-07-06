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
import java.util.List;

@SuppressWarnings("serial")
public class ContactDTO implements Serializable, Contact {

	private List<Address> addresses;
	private String firstName;
	private long id;
	private String lastName;
	private String type;
	private String relationship;
	private String caseNumber;

	@Override
	public void addAddress(Address address) {
		this.addresses.add(address);
	}

	@Override
	public List<Address> getAddresses() {
		return addresses;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getRelationship() {
		return relationship;
	}

	@Override
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@Override
	public ContactDTO toDto() {
		return this;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

}
