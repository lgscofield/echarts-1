package org.eastway.echarts.client.rpc;

import org.eastway.echarts.client.HandleRpcException;

import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class EchartsCallback<T> implements AsyncCallback<T> {

	private EventBus eventBus;

	public EchartsCallback(EventBus eventBus) {
		this.eventBus = eventBus;
		startProcessing();
	}

	protected abstract void handleFailure(Throwable caught);

	@Override
	public void onFailure(Throwable caught) {
		try {
			new HandleRpcException(caught);
			handleFailure(caught);
		} finally {
			stopProcessing();
		}
	}

	protected abstract void handleSuccess(T t);

	@Override
	public void onSuccess(T t) {
		try {
			handleSuccess(t);
		} finally {
			stopProcessing();
		}
	}

	private void startProcessing() {
		eventBus.fireEvent(new RequestEvent(State.SENT));
	}

	private void stopProcessing() {
		eventBus.fireEvent(new RequestEvent(State.RECEIVED));
	}
}
