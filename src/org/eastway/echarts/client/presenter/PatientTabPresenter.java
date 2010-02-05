package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.PatientServicesAsync;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TreeItem;

import org.eastway.echarts.client.MessagePanel;

public class PatientTabPresenter extends EchartsPresenter<PatientTabPresenter.Display> {
	public interface Display extends EchartsDisplay {
		void setPatientId(String patientId);

		String getPatientId();

		HasSelectionHandlers<TreeItem> getPatientMenu();

		void setDisplay(HasText selectedItem);

		void initDisplayArea();

		void setMessages(MessagePanel messagePanel);
	}

	public PatientTabPresenter(Display display, HandlerManager eventBus, PatientServicesAsync patientSvc, String patientId) {
		super(display, eventBus);
		display.setPatientId(patientId);
		display.initDisplayArea();
		display.setMessages(new MessagePanel(patientId, patientSvc));
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
