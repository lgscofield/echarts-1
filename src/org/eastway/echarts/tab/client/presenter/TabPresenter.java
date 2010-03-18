package org.eastway.echarts.tab.client.presenter;

import org.eastway.echarts.client.Rpc;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.presenter.DemographicsPresenter;
import org.eastway.echarts.client.presenter.EchartsDisplay;
import org.eastway.echarts.client.presenter.FormsPresenter;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.presenter.PersonalPresenter;
import org.eastway.echarts.client.presenter.Presenter;
import org.eastway.echarts.client.presenter.ServiceHistoryPresenter;
import org.eastway.echarts.client.view.DemographicsView;
import org.eastway.echarts.client.view.FormsView;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.PersonalView;
import org.eastway.echarts.client.view.ServiceHistoryView;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TreeItem;

public class TabPresenter extends Presenter<TabPresenter.Display> {
	public interface Display extends EchartsDisplay {
		HasSelectionHandlers<TreeItem> getMenu();

		void setDisplay(Object view);

		Object getView(TreeItem selectedItem);
	}

	private HandlerManager eventBus;
	private String patientId;
	private Patient patient;

	private ServiceHistoryPresenter serviceHistory;
	private MessagesPresenter messages;
	private PersonalPresenter personal;
	private DemographicsPresenter demographics;
	private FormsPresenter forms;

	public TabPresenter(Display display, HandlerManager eventBus,
							String patientId) {
		super(display, eventBus);
		this.eventBus = eventBus;
		this.patientId = patientId;
		bind();
		fetchPatient();
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
		if (view instanceof ServiceHistoryView) {
			if (serviceHistory == null)
				serviceHistory = new ServiceHistoryPresenter(
					(ServiceHistoryView)view, eventBus,
					patient);
		} else if (view instanceof MessagesView) {
			if (messages == null)
				messages = new MessagesPresenter(
					(MessagesView)view, eventBus,
					null, patient);
		} else if (view instanceof PersonalView) {
			if (personal == null)
				personal = new PersonalPresenter(
					(PersonalView)view, eventBus,
					patient);
		} else if (view instanceof DemographicsView) {
			if (demographics == null)
				demographics = new DemographicsPresenter(
						(DemographicsView)view, eventBus,
						patient);
		} else if (view instanceof FormsView) {
			if (forms == null)
				forms = new FormsPresenter(
						(FormsView)view, eventBus, patient);
		}
		display.setDisplay(view);
	}

	private void fetchPatient() {
		AsyncCallback<Patient> callback = new AsyncCallback<Patient>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Patient patient) {
				setPatient(patient);
			}
		};
		Rpc.singleton().getPatient(patientId, UserImpl.getSessionId(),
				callback);
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return this.patient;
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
