package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.PatientServicesAsync;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TreeItem;

public class PatientTabPresenter extends EchartsPresenter<PatientTabPresenter.Display> {
	public interface Display extends EchartsDisplay {
		void setPatientId(String patientId);

		String getPatientId();

		HasSelectionHandlers<TreeItem> getPatientMenu();

		void setDisplay(HasText selectedItem);

		void initDisplayArea();
	}

	public PatientTabPresenter(Display display, HandlerManager eventBus, PatientServicesAsync patientSvc, String patientId) {
		super(display, eventBus);
		display.setPatientId(patientId);
		display.initDisplayArea();
		bind();
	}

	private void bind() {
		display.getPatientMenu().addSelectionHandler(new SelectionHandler<TreeItem>() {
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				display.setDisplay(event.getSelectedItem());
			}
		});
	}
}
