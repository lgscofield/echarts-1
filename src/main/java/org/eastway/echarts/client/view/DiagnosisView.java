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

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import org.eastway.echarts.client.presenter.DiagnosisPresenter;
import org.eastway.echarts.shared.DiagnosisDTO;

public class DiagnosisView extends Composite implements DiagnosisPresenter.Display {

	private static DiagnosisViewUiBinder uiBinder = GWT
			.create(DiagnosisViewUiBinder.class);

	interface DiagnosisViewUiBinder extends UiBinder<Widget, DiagnosisView> { }

	@UiField FlexTable diagnoses;
	private int record = 0;

	public DiagnosisView() {
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
	public String getAxis1A() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAxis1B() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAxis1C() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAxis1D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAxis1E() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAxis2A() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAxis2B() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAxis2C() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAxis3() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAxis4() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCaseNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCurrentGAF() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHighestGAF() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Date getLastEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastEditBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAxis1A(String axis1a) {
		this.diagnoses.setText(record, 0, axis1a);
	}

	@Override
	public void setAxis1B(String axis1b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAxis1C(String axis1c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAxis1D(String axis1d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAxis1E(String axis1e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAxis2A(String axis2a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAxis2B(String axis2b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAxis2C(String axis2c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAxis3(String axis3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAxis4(String axis4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentGAF(int currentGAF) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDate(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHighestGAF(int highestGAF) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastEdit(Date lastEdit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastEditBy(String lastEditBy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DiagnosisDTO toDto() {
		// TODO Auto-generated method stub
		return null;
	}

}
