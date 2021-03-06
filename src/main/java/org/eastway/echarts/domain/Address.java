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

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Address {
	@PersistenceContext
	transient EntityManager entityManager;
	@Id
	@TableGenerator(name="tg", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tg")
	private Long id;
	private String caseNumber;
	private String descriptor;
	private String title;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zip;
	private String county;
	private String phone1;
	private String phone1Desc;
	private String phone2;
	private String phone2Desc;
	private Date lastEdit;
	private String lastEditBy;
	@Version
	@Column(name = "version")
	private Integer version;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getStreet2() {
		return street2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getZip() {
		return zip;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCounty() {
		return county;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1Desc(String phone1Desc) {
		this.phone1Desc = phone1Desc;
	}

	public String getPhone1Desc() {
		return phone1Desc;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2Desc(String phone2Desc) {
		this.phone2Desc = phone2Desc;
	}

	public String getPhone2Desc() {
		return phone2Desc;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new Address().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static Address findAddress(Long id) {
		if (id == null)
			return null;
		return entityManager().find(Address.class, id);
	}

	public static List<Address> findAddressesByCaseNumber(String caseNumber) {
		return entityManager().createQuery("SELECT a FROM Address a where a.caseNumber = :caseNumber", Address.class)
			.setParameter("caseNumber", caseNumber)
			.getResultList();
	}
}
