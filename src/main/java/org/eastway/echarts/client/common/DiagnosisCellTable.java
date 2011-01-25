package org.eastway.echarts.client.common;

import org.eastway.echarts.client.request.DiagnosisProxy;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;

public class DiagnosisCellTable extends CellTable<DiagnosisProxy> {
	public DiagnosisCellTable() {
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getDate() == null ? "" : GlobalResources.getDateFormat().format(object.getDate());
			}
		}, "Date");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis1A() == null ? "" : object.getAxis1A().getId();
			}
		}, "Axis 1A");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis1B() == null ? "" : object.getAxis1B().getId();
			}
		}, "Axis 1B");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis1C() == null ? "" : object.getAxis1C().getId();
			}
		}, "Axis 1C");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis1D() == null ? "" : object.getAxis1D().getId();
			}
		}, "Axis 1D");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis1E() == null ? "" : object.getAxis1E().getId();
			}
		}, "Axis 1E");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis2A() == null ? "" : object.getAxis2A().getId();
			}
		}, "Axis 2A");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis2B() == null ? "" : object.getAxis2B().getId();
			}
		}, "Axis 2B");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis2C() == null ? "" : object.getAxis2C().getId();
			}
		}, "Axis 2C");
	}
}
