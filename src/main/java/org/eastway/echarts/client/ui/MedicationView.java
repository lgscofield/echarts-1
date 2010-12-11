package org.eastway.echarts.client.ui;

import com.google.gwt.user.client.ui.IsWidget;

public interface MedicationView<T> extends IsWidget {
	public interface Presenter<T> { }

	public void nextRecord();

	public void setMedication(String medication);
}
