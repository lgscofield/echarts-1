package org.eastway.echarts.client.presenter;

import java.util.LinkedHashSet;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.UserImpl;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class FormsPresenter extends Presenter<FormsPresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setData(LinkedHashSet<String[]> data);
	}

	private String patientid;
	private LinkedHashSet<String[]> data;
	private RpcServicesAsync rpcServices;

	public FormsPresenter(Display display, HandlerManager eventBus,
			RpcServicesAsync rpcServices, String patientid) {
		super(display, eventBus);
		this.patientid = patientid;
		this.rpcServices = rpcServices;
	}

	private void fetchData() {
		AsyncCallback<LinkedHashSet<String[]>> callback = new AsyncCallback<LinkedHashSet<String[]>>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(LinkedHashSet<String[]> data) {
				setData(data);
				display.setData(getData());
			}
		};
		rpcServices.getFormsList(UserImpl.getSessionId(), patientid, callback);
	}

	public LinkedHashSet<String[]> getData() {
		return data;
	}

	public void setData(LinkedHashSet<String[]> d) {
		this.data = d;
		for (String[] s : data) {
			s[1] += "?staffid=" + UserImpl.getStaffId() + "&PATID=" + patientid;
		}
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchData();
	}
}
