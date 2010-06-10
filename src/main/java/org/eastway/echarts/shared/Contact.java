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

import java.util.List;

public interface Contact {

	public void setId(long id);

	public long getId();

	public void setFirstName(String firstName);

	public String getFirstName();

	public void setLastName(String lastName);

	public String getLastName();

	public void setType(String type);

	public String getType();

	public void setRelationship(String relationship);

	public String getRelationship();

	public void addAddress(Address address);

	public void setAddresses(List<Address> addresses);

	public List<Address> getAddresses();

	public ContactDTO toDto();

}
