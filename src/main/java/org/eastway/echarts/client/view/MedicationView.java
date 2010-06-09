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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import org.eastway.echarts.client.presenter.MedicationPresenter;
import org.eastway.echarts.shared.MedicationDTO;

public class MedicationView extends Composite implements MedicationPresenter.Display {

	private static MedicationViewUiBinder uiBinder = GWT
			.create(MedicationViewUiBinder.class);

	interface MedicationViewUiBinder extends UiBinder<Widget, MedicationView> { }

	private int record = 0;
	@UiField FlexTable medications;

	enum Column {
		MEDICATION,
	}

	public MedicationView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void nextRecord() {
		record++;
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMedication() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMedication(String medication) {
		this.medications.setText(record, Column.MEDICATION.ordinal(), medication);
	}

	@Override
	public MedicationDTO toDto() {
		// TODO Auto-generated method stub
		return null;
	}

}
