/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.client.rpc;

import com.google.gwt.event.shared.EventBus;

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
		eventBus.fireEvent(new RequestEvent(State.SENT, null));
	}

	private void stopProcessing() {
		eventBus.fireEvent(new RequestEvent(State.RECEIVED, null));
	}
}
