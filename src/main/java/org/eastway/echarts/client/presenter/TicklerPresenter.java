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
package org.eastway.echarts.client.presenter;

import java.util.List;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.CachingDispatchAsync;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.events.OpenIspEvent;
import org.eastway.echarts.client.view.TicklerView;
import org.eastway.echarts.shared.GetTickler;
import org.eastway.echarts.shared.GetTicklerResult;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class TicklerPresenter implements Presenter, TicklerView.Presenter<Tickler> {
	private TicklerView<Tickler> view;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetTickler action;

	public TicklerPresenter(TicklerView<Tickler> view, List<ColumnDefinition<Tickler>> columnDefinitions, EventBus eventBus, CachingDispatchAsync dispatch, GetTickler action) {
		this.view = view;
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.view.setPresenter(this);
		this.action = action;
		this.view.setColumnDefinitions(columnDefinitions);
	}

	public void fetchData() {
		eventBus.fireEvent(new RequestEvent(State.SENT));
		dispatch.executeWithCache(action, new AsyncCallback<GetTicklerResult>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(GetTicklerResult result) {
				eventBus.fireEvent(new RequestEvent(State.RECEIVED));
				view.setRowData(result.getTicklers());
			}
		});
	}

	@Override
	public void go(HasWidgets container) {
		fetchData();
	}

	@Override
	public void openEhr(Tickler tickler) {
		eventBus.fireEvent(new OpenEhrEvent(tickler.getCaseNumber()));
	}

	@Override
	public void openIsp(Tickler tickler) {
		eventBus.fireEvent(new OpenIspEvent(tickler.getCaseNumber()));
	}
}
