package org.eastway.echarts.client.view;

import java.util.Iterator;
import java.util.Vector;

import org.eastway.echarts.client.presenter.PatientListPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PatientListView extends Composite implements PatientListPresenter.Display {

	private FlexTable patientListTable = new FlexTable();

	public PatientListView() {
		initWidget(patientListTable);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(Vector<String> data) {
		Iterator<String> r = data.iterator();
		for (int i = 0; r.hasNext(); i++) {
			Label lbl = new Label(r.next());
			patientListTable.setWidget(i, 0, lbl);
			lbl.addStyleName("patient-Label");
		}
	}

	@Override
	public HasClickHandlers getTable() {
		return patientListTable;
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		int selected = -1;

		FlexTable.Cell cell = patientListTable.getCellForEvent(event);

		if (cell != null) {
			if (cell.getCellIndex() >= 0) {
				selected = cell.getRowIndex();
			}
		}
		return selected;
	}
}
