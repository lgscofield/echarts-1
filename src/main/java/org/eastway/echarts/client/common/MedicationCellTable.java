package org.eastway.echarts.client.common;

import org.eastway.echarts.client.request.MedicationProxy;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;

public class MedicationCellTable extends CellTable<MedicationProxy> {

	public MedicationCellTable() {
		this.addColumn(new TextColumn<MedicationProxy>() {
			@Override
			public String getValue(MedicationProxy object) {
				if (object == null)
					return "";
				return object.getMedication() == null ? "" : String.valueOf(object.getMedication());
			}
		}, "Medication");
		this.addColumn(new TextColumn<MedicationProxy>() {
			@Override
			public String getValue(MedicationProxy object) {
				if (object == null)
					return "";
				return object.getLabsOrdered() == null ? "" : String.valueOf(object.getLabsOrdered());
			}
		}, "Labs Ordered");
		this.addColumn(new TextColumn<MedicationProxy>() {
			@Override
			public String getValue(MedicationProxy object) {
				if (object == null)
					return "";
				return object.getMedMonitoring() == null ? "" : String.valueOf(object.getMedMonitoring());
			}
		}, "Med Monitoring");
		this.addColumn(new TextColumn<MedicationProxy>() {
			@Override
			public String getValue(MedicationProxy object) {
				if (object == null)
					return "";
				if (object.getLastEdit() == null)
					return "";
				return GlobalResources.getDateFormat().format(object.getLastEdit());
			}
		}, "Last Edit");
		this.addColumn(new TextColumn<MedicationProxy>() {
			@Override
			public String getValue(MedicationProxy object) {
				if (object == null)
					return "";
				return object.getLastEditBy() == null ? "" : object.getLastEditBy();
			}
		}, "Last Edit By");
	}
}
