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
	private String emptyCellFiller = "&nbsp;";

	enum Column {
		AXIS1A,
		AXIS1B,
		AXIS1C,
		AXIS1D,
		AXIS1E,
		AXIS2A,
		AXIS2B,
		AXIS2C,
		AXIS3,
		AXIS4,
		CURRENTGAF,
		HIGHESTGAF,
		DATE,
	}

	public DiagnosisView() {
		initWidget(uiBinder.createAndBindUi(this));
		diagnoses.setHTML(record, Column.AXIS1A.ordinal(), "<b>AXIS 1A</b>");
		diagnoses.setHTML(record, Column.AXIS1B.ordinal(), "<b>AXIS 1B</b>");
		diagnoses.setHTML(record, Column.AXIS1C.ordinal(), "<b>AXIS 1C</b>");
		diagnoses.setHTML(record, Column.AXIS1D.ordinal(), "<b>AXIS 1D</b>");
		diagnoses.setHTML(record, Column.AXIS1E.ordinal(), "<b>AXIS 1E</b>");
		diagnoses.setHTML(record, Column.AXIS2A.ordinal(), "<b>AXIS 2A</b>");
		diagnoses.setHTML(record, Column.AXIS2B.ordinal(), "<b>AXIS 2B</b>");
		diagnoses.setHTML(record, Column.AXIS2C.ordinal(), "<b>AXIS 2C</b>");
		diagnoses.setHTML(record, Column.AXIS3.ordinal(), "<b>AXIS 3</b>");
		diagnoses.setHTML(record, Column.AXIS4.ordinal(), "<b>AXIS 4</b>");
		diagnoses.setHTML(record, Column.CURRENTGAF.ordinal(), "<b>CURRENT GAF</b>");
		diagnoses.setHTML(record, Column.HIGHESTGAF.ordinal(), "<b>HIGHEST GAF</b>");
		diagnoses.setHTML(record, Column.DATE.ordinal(), "<b>DATE</b>");
		diagnoses.setBorderWidth(1);
		nextRecord();
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
		if (axis1a == null || axis1a.matches(""))
			diagnoses.setHTML(record, Column.AXIS1A.ordinal(), emptyCellFiller);
		else
			diagnoses.setText(record, Column.AXIS1A.ordinal(), axis1a);
	}

	@Override
	public void setAxis1B(String axis1b) {
		if (axis1b == null || axis1b.matches(""))
			diagnoses.setHTML(record, Column.AXIS1B.ordinal(), emptyCellFiller);
		else
			diagnoses.setText(record, Column.AXIS1B.ordinal(), axis1b);
	}

	@Override
	public void setAxis1C(String axis1c) {
		if (axis1c == null || axis1c.matches(""))
			diagnoses.setHTML(record, Column.AXIS1C.ordinal(), emptyCellFiller);
		else
			diagnoses.setText(record, Column.AXIS1C.ordinal(), axis1c);
	}

	@Override
	public void setAxis1D(String axis1d) {
		if (axis1d == null || axis1d.matches(""))
			diagnoses.setHTML(record, Column.AXIS1D.ordinal(), emptyCellFiller);
		else
			diagnoses.setText(record, Column.AXIS1D.ordinal(), axis1d);
	}

	@Override
	public void setAxis1E(String axis1e) {
		if (axis1e == null || axis1e.matches(""))
			diagnoses.setHTML(record, Column.AXIS1E.ordinal(), emptyCellFiller);
		else
			diagnoses.setText(record, Column.AXIS1E.ordinal(), axis1e);
	}

	@Override
	public void setAxis2A(String axis2a) {
		if (axis2a == null || axis2a.matches(""))
			diagnoses.setHTML(record, Column.AXIS2A.ordinal(), emptyCellFiller);
		else
			diagnoses.setText(record, Column.AXIS2A.ordinal(), axis2a);
	}

	@Override
	public void setAxis2B(String axis2b) {
		if (axis2b == null || axis2b.matches(""))
			diagnoses.setHTML(record, Column.AXIS2B.ordinal(), emptyCellFiller);
		else
			diagnoses.setText(record, Column.AXIS2B.ordinal(), axis2b);
	}

	@Override
	public void setAxis2C(String axis2c) {
		if (axis2c == null || axis2c.matches(""))
			diagnoses.setHTML(record, Column.AXIS2C.ordinal(), emptyCellFiller);
		else
			diagnoses.setText(record, Column.AXIS2C.ordinal(), axis2c);
	}

	@Override
	public void setAxis3(String axis3) {
		if (axis3 == null || axis3.matches(""))
			diagnoses.setHTML(record, Column.AXIS3.ordinal(), emptyCellFiller);
		else
			diagnoses.setText(record, Column.AXIS3.ordinal(), axis3);
	}

	@Override
	public void setAxis4(String axis4) {
		if (axis4 == null || axis4.matches(""))
			diagnoses.setHTML(record, Column.AXIS4.ordinal(), emptyCellFiller);
		else
			diagnoses.setText(record, Column.AXIS4.ordinal(), axis4);
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrentGAF(int currentGAF) {
		diagnoses.setText(record, Column.CURRENTGAF.ordinal(), new Integer(currentGAF).toString());
	}

	@Override
	public void setDate(Date date) {
		diagnoses.setText(record, Column.DATE.ordinal(), date.toString());
	}

	@Override
	public void setHighestGAF(int highestGAF) {
		diagnoses.setText(record, Column.HIGHESTGAF.ordinal(), new Integer(highestGAF).toString());
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
