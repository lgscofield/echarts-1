package org.eastway.echarts.client.forms.presenter;

import java.util.HashMap;
import java.util.LinkedHashSet;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.forms.view.CPSTNoteView;
import org.eastway.echarts.client.forms.view.IPNNoteView;
import org.eastway.echarts.client.forms.view.NursingNoteView;
import org.eastway.echarts.client.presenter.EchartsDisplay;
import org.eastway.echarts.client.presenter.EchartsPresenter;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ProgressNotePresenter extends EchartsPresenter<ProgressNotePresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(HashMap<String, LinkedHashSet<HashMap<String, ?>>> data);

		HasChangeHandlers getServiceCodesList();

		String getSelectedServiceCode(ChangeEvent event);

		void setNoteBody(EchartsPresenter<?> presenter);

		void setPatientId(String patientId);

		HasClickHandlers getNext();

		HasClickHandlers getFinish();

		void doNext();

		void doFinish();

		HasClickHandlers getPrevious();

		void doPrevious();
	}

	private Patient patient;
	private PatientServicesAsync patientSvc;

	public ProgressNotePresenter(Display display, HandlerManager eventBus,
			PatientServicesAsync patientSvc, Patient patient) {
		super(display, eventBus);
		this.patient = patient;
		this.patientSvc = patientSvc;
		bind();
		setData();
	}

	private void bind() {
		display.getServiceCodesList().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				getService(event);
			}
		});
		display.getNext().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doNext();
			}
		});
		display.getFinish().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doFinish();
			}
		});
		display.getPrevious().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				doPrevious();
			}
		});
	}

	private void doPrevious() {
		display.doPrevious();
	}

	private void doFinish() {
		display.doFinish();
	}

	private void doNext() {
		display.doNext();
	}

	private void setData() {
		AsyncCallback<HashMap<String, LinkedHashSet<HashMap<String, ?>>>>
				callback = new AsyncCallback<HashMap<String, LinkedHashSet<HashMap<String, ?>>>>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(HashMap<String, LinkedHashSet<HashMap<String, ?>>> data) {
				display.setData(data);
			}
		};
		patientSvc.getBillingStripData(UserImpl.getSessionId(),
				callback);

		display.setPatientId(patient.getPatientId());
	}

	private void getService(ChangeEvent event) {
		String service = display.getSelectedServiceCode(event);
		if (service.isEmpty() || service.matches("null")) {
			Window.alert("There is no template associated with this service.");
		} else {
			getNote(service);
		}
	}

	private void getNote(String service) {
		if (service.equals("101")) {
			display.setNoteBody(new CPSTNotePresenter(new CPSTNoteView(), eventBus));
		} else if (service.equals("102")) {
			display.setNoteBody(new IPNNotePresenter(new IPNNoteView(), eventBus));
		} else if (service.equals("103")) {
			display.setNoteBody(new NursingNotePresenter(new NursingNoteView(), eventBus));
		}
	}
}
