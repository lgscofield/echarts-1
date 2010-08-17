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

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

public class CachingDispatchAsyncImpl implements DispatchAsync, CachingDispatchAsync {

	private DispatchAsync dispatcher;
	private Map<Action<Result>, Result> cache = new HashMap<Action<Result>, Result>();

	@Inject
	public CachingDispatchAsyncImpl(final DispatchAsync dispatcher) {
		this.dispatcher = dispatcher;
	}

	@Override
	public <A extends Action<R>, R extends Result> void execute(A action, AsyncCallback<R> callback) {
		dispatcher.execute(action, callback);
	}

	@SuppressWarnings("unchecked")
	public <A extends Action<R>, R extends Result> void executeWithCache(final A action, final AsyncCallback<R> callback) {
		final Result r = cache.get(action);
		if (r != null) {
			callback.onSuccess((R) r);
		} else {
			dispatcher.execute(action, new AsyncCallback<R>() {

				@Override
				public void onFailure(Throwable caught) {
					callback.onFailure(caught);
				}

				@Override
				public void onSuccess(R result) {
					cache.put((Action) action, (R) result);
					callback.onSuccess(result);
				}
				
			});
		}
	}

	public void clear() {
		cache.clear();
	}
}
