package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.presenter.AddMessagePresenter;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.presenter.ServiceHistoryPresenter;
import org.eastway.echarts.client.view.AddMessageView;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.ServiceHistoryView;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TreeItem;

public class PatientTabPresenter extends EchartsPresenter<PatientTabPresenter.Display> {
	public interface Display extends EchartsDisplay {
		HasSelectionHandlers<TreeItem> getMenu();

		void setDisplay(Object view);

		Object getView(TreeItem selectedItem);
	}

	private HandlerManager eventBus;
	private PatientServicesAsync patientSvc;
	private String patientId;

	private ServiceHistoryPresenter serviceHistory;
	private MessagesPresenter messages;
	private AddMessagePresenter addMessage;

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
				setDisplay(display.getView(event.getSelectedItem()));
			}
		});
	}

	private void setDisplay(Object view) {
		if (view.getClass() == ServiceHistoryView.class) {
			if (serviceHistory == null)
				serviceHistory = new ServiceHistoryPresenter(
					(ServiceHistoryView)view, eventBus,
					patientSvc, patientId);
		} else if (view.getClass() == MessagesView.class) {
			if (messages == null)
				messages = new MessagesPresenter(
					(MessagesView)view, eventBus,
					patientSvc, patientId);
		} else if (view.getClass() == AddMessageView.class) {
			addMessage = new AddMessagePresenter(
				(AddMessageView)view, eventBus, patientSvc,
					patientId);
			return;
		}
		display.setDisplay(view);
	}
}
