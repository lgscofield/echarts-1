package org.eastway.echarts.client.presenter;

import java.util.LinkedHashSet;

import org.eastway.echarts.client.EchartsRpc;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.shared.Patient;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class FormsPresenter extends EchartsPresenter<FormsPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(LinkedHashSet<String[]> data);
	}

	private Patient patient;
	public FormsPresenter(Display display, HandlerManager eventBus,
			Patient patient) {
		super(display, eventBus);
		this.patient = patient;
		setData();
	}

	private void setData() {
		AsyncCallback<LinkedHashSet<String[]>> callback = new AsyncCallback<LinkedHashSet<String[]>>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(LinkedHashSet<String[]> data) {
				for (String[] s : data) {
					s[1] += "?staffid=" + UserImpl.getStaffId() + "&PATID=" + patient.getPatientId();
				}
				display.setData(data);
			}
		};
		EchartsRpc.getRpc().getFormsList(UserImpl.getSessionId(), patient.getPatientId(), callback);
	}
}
