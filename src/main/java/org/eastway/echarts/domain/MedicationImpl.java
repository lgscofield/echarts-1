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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eastway.echarts.shared.Medication;
import org.eastway.echarts.shared.MedicationDTO;

@Entity
@Table(name = "Medication")
public class MedicationImpl implements Medication {

	@Id
	private long id;
	private String medication;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getMedication() {
		return medication;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setMedication(String medication) {
		this.medication = medication;
	}

	@Override
	public MedicationDTO toDto() {
		MedicationDTO dto = new MedicationDTO();
		dto.setId(id);
		dto.setMedication(medication);
		return dto;
	}

}
