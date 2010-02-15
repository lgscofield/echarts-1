package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.presenter.ServiceHistoryPresenter;
import org.eastway.echarts.client.view.ServiceHistoryView;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class PatientTabPresenter extends EchartsPresenter<PatientTabPresenter.Display> {
	public interface Display extends EchartsDisplay {
		HasSelectionHandlers<TreeItem> getMenu();

		void setDisplay(Widget selectedItem);
	}

	private HandlerManager eventBus;
	private PatientServicesAsync patientSvc;
	private String patientId;

	private ServiceHistoryPresenter serviceHistory;

	public PatientTabPresenter(Display display, HandlerManager eventBus,
					PatientServicesAsync patientSvc,
							String patientId) {
		super(display, eventBus);
		this.eventBus = eventBus;
		this.patientSvc = patientSvc;
		this.patientId = patientId;
		bind();
	}

	private void bind() {
		display.getMenu().addSelectionHandler(new SelectionHandler<TreeItem>() {
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
				setDisplay(event.getSelectedItem());
			}
		});
	}

	private void setDisplay(TreeItem selectedItem) {
		Widget menuItemUserObject = (Widget)selectedItem.getUserObject();
		if (menuItemUserObject.getClass() == ServiceHistoryView.class) {
			if (serviceHistory == null)
				serviceHistory = new ServiceHistoryPresenter(
					(ServiceHistoryView)menuItemUserObject,
					 eventBus,
					patientSvc, patientId);
		}
		display.setDisplay(menuItemUserObject);
	}
}
