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

import java.util.Date;

public interface Address {

	public void setId(Long id);

	public Long getId();

	public void setCaseNumber(String caseNumber);

	public String getCaseNumber();

	public void setDescriptor(String descriptor);

	public String getDescriptor();

	public void setTitle(String title);

	public String getTitle();

	public void setStreet1(String street1);

	public String getStreet1();

	public void setStreet2(String street2);

	public String getStreet2();

	public void setCity(String city);

	public String getCity();

	public void setState(String state);

	public String getState();

	public void setZip(String zip);

	public String getZip();

	public void setCounty(String county);

	public String getCounty();

	public void setPhone1(String phone1);

	public String getPhone1();

	public void setPhone1Desc(String phone1desc);

	public String getPhone1Desc();

	public void setPhone2(String phone2);

	public String getPhone2();

	public void setPhone2Desc(String phone2desc);

	public String getPhone2Desc();

	public void setLastEdit(Date lastEdit);

	public Date getLastEdit();

	public void setLastEditBy(String lastEditBy);

	public String getLastEditBy();

	public AddressDTO toDto();
}