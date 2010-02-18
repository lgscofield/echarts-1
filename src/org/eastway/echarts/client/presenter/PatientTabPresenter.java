package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.presenter.AddMessagePresenter;
import org.eastway.echarts.client.presenter.IspPresenter;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.presenter.PersonalPresenter;
import org.eastway.echarts.client.presenter.ProgressNotePresenter;
import org.eastway.echarts.client.presenter.ServiceHistoryPresenter;
import org.eastway.echarts.client.view.AddMessageView;
import org.eastway.echarts.client.view.IspView;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.PersonalView;
import org.eastway.echarts.client.view.ProgressNoteView;
import org.eastway.echarts.client.view.ServiceHistoryView;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
	private Patient patient;

	private ServiceHistoryPresenter serviceHistory;
	private MessagesPresenter messages;
	private AddMessagePresenter addMessage;
	private ProgressNotePresenter progressNote;
	private PersonalPresenter personal;
	private IspPresenter isp;

	public PatientTabPresenter(Display display, HandlerManager eventBus,
					PatientServicesAsync patientSvc,
							String patientId) {
		super(display, eventBus);
		this.eventBus = eventBus;
		this.patientSvc = patientSvc;
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
		if (view.getClass() == ServiceHistoryView.class) {
			if (serviceHistory == null)
				serviceHistory = new ServiceHistoryPresenter(
					(ServiceHistoryView)view, eventBus,
					patientSvc, patient);
		} else if (view.getClass() == MessagesView.class) {
			if (messages == null)
				messages = new MessagesPresenter(
					(MessagesView)view, eventBus,
					patientSvc, patient);
		} else if (view.getClass() == AddMessageView.class) {
			if (addMessage == null)
				addMessage = new AddMessagePresenter(
					(AddMessageView)view, eventBus, patientSvc,
					patient);
			else
				addMessage.display.show();
			return;
		} else if (view.getClass() == ProgressNoteView.class) {
			if (progressNote == null)
				progressNote = new ProgressNotePresenter(
					(ProgressNoteView)view, eventBus,
					patientSvc, patient);
		} else if (view.getClass() == PersonalView.class) {
			if (personal == null)
				personal = new PersonalPresenter(
					(PersonalView)view, eventBus,
					patientSvc, patient);
		} else if (view.getClass() == IspView.class) {
			if (isp == null)
				isp = new IspPresenter(
					(IspView)view, eventBus,
					patientSvc, patient);
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
		patientSvc.getPatient(patientId, Cookies.getCookie("sessionId"),
				callback);
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Patient getPatient() {
		return this.patient;
	}
}
