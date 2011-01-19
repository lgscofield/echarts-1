package org.eastway.echarts.client.common;

import org.eastway.echarts.client.request.DiagnosisProxy;
import org.eastway.echarts.client.style.GlobalResources;
import org.eastway.echarts.client.ui.DiagnosisCodeProxyRenderer;

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
				return object.getAxis1A() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(object.getAxis1A());
			}
		}, "Axis 1A");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis1B() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(object.getAxis1B());
			}
		}, "Axis 1B");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis1C() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(object.getAxis1C());
			}
		}, "Axis 1C");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis1D() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(object.getAxis1D());
			}
		}, "Axis 1D");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis1E() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(object.getAxis1E());
			}
		}, "Axis 1E");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis2A() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(object.getAxis2A());
			}
		}, "Axis 2A");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis2B() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(object.getAxis2B());
			}
		}, "Axis 2B");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis2C() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(object.getAxis2C());
			}
		}, "Axis 2C");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis3() == null ? "" : String.valueOf(object.getAxis3());
			}
		}, "Axis 3");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getAxis4() == null ? "" : String.valueOf(object.getAxis4());
			}
		}, "Axis 4");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getCurrentGAF() == null ? "" : String.valueOf(object.getCurrentGAF());
			}
		}, "Current GAF");
		this.addColumn(new TextColumn<DiagnosisProxy>() {
			@Override
			public String getValue(DiagnosisProxy object) {
				if (object == null)
					return "";
				return object.getHighestGAF() == null ? "" : String.valueOf(object.getHighestGAF());
			}
		}, "Highest GAF");
	}
}
