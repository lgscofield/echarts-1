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
package org.eastway.echarts.client.rpc;

import java.util.Date;

import org.eastway.echarts.domain.Address;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyFor;

@ProxyFor(Address.class)
public interface AddressProxy extends EntityProxy {

	void setId(Long id);

	Long getId();

	void setCaseNumber(String caseNumber);

	String getCaseNumber();

	void setDescriptor(String descriptor);

	String getDescriptor();

	void setTitle(String title);

	String getTitle();

	void setStreet1(String street1);

	String getStreet1();

	void setStreet2(String street2);

	String getStreet2();

	void setCity(String city);

	String getCity();

	void setState(String state);

	String getState();

	void setZip(String zip);

	String getZip();

	void setCounty(String county);

	String getCounty();

	void setPhone1(String phone1);

	String getPhone1();

	void setPhone1Desc(String phone1Desc);

	String getPhone1Desc();

	void setPhone2(String phone2);

	String getPhone2();

	void setPhone2Desc(String phone2Desc);

	String getPhone2Desc();

	void setLastEdit(Date lastEdit);

	Date getLastEdit();

	void setLastEditBy(String lastEditBy);

	String getLastEditBy();

	Integer getVersion();

	void setVersion(Integer version);
}