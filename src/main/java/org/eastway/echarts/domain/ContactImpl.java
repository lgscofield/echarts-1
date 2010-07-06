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
package org.eastway.echarts.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.Contact;
import org.eastway.echarts.shared.ContactDTO;

@Entity
@Table(name = "Contact")
public class ContactImpl implements Contact {

	@Id
	private long id;
	@OneToMany(targetEntity = AddressImpl.class)
	@JoinColumn
	private List<AddressImpl> addresses;
	private String firstName;
	private String lastName;
	private String type;
	private String relationship;
	private String caseNumber;

	@Override
	public void addAddress(Address address) {
		this.addresses.add((AddressImpl)address);
	}

	@Override
	public List<Address> getAddresses() {
		List<Address> addresses = new ArrayList<Address>();
		for (AddressImpl address : this.addresses)
			addresses.add(address);
		return addresses;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public String getType() {
		return type;
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
		this.addresses.clear();
		for (Address address : addresses)
			this.addresses.add((AddressImpl) address);
	}

	@Override
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@Override
	public ContactDTO toDto() {
		ContactDTO dto = new ContactDTO();
		dto.setFirstName(firstName);
		dto.setId(id);
		dto.setLastName(lastName);
		dto.setRelationship(relationship);
		dto.setType(type);

		List<Address> addresses = new ArrayList<Address>();
		for (Address address : this.addresses)
			addresses.add(address.toDto());
		dto.setAddresses(addresses);

		return dto;
	}

}
