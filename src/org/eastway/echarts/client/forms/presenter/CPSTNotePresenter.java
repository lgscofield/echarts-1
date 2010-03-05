package org.eastway.echarts.client.forms.presenter;

import java.util.HashMap;
import java.util.LinkedHashSet;

import org.eastway.echarts.client.EchartsRpc;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.presenter.EchartsDisplay;
import org.eastway.echarts.client.presenter.EchartsPresenter;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class CPSTNotePresenter extends EchartsPresenter<CPSTNotePresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setNonBillable();

		void setData(HashMap<String, LinkedHashSet<HashMap<String, String>>> data);
	}

	public CPSTNotePresenter(Display display, HandlerManager eventBus) {
		super(display, eventBus);
		setData();
	}

	private void setData() {
		display.setNonBillable();
		AsyncCallback<HashMap<String, LinkedHashSet<HashMap<String, String>>>> callback =
			new AsyncCallback<HashMap<String, LinkedHashSet<HashMap<String, String>>>>() {
				@Override
				public void onFailure(Throwable caught) {
					new HandleRpcException(caught);
				}

				@Override
				public void onSuccess(HashMap<String, LinkedHashSet<HashMap<String, String>>> data) {
					display.setData(data);
				}
		};
		EchartsRpc.getRpc().getCPSTNoteData(UserImpl.getSessionId(), callback);
	}
}
