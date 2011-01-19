package org.eastway.echarts.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;

public interface MedicationView<T> extends IsWidget {
	public interface Presenter<T> { }

	public void setRowData(List<T> values);

	public void setError(String message);
}
