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

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.events.OpenCpstNoteEvent;
import org.eastway.echarts.client.events.OpenDoctorProgressNoteEvent;
import org.eastway.echarts.client.events.OpenEhrEvent;
import org.eastway.echarts.client.events.OpenIndividualProgressNoteEvent;
import org.eastway.echarts.client.events.OpenIspEvent;
import org.eastway.echarts.client.events.OpenNurseProgressNoteEvent;
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.client.view.TicklerView;
import org.eastway.echarts.shared.GetTickler;
import org.eastway.echarts.shared.GetTicklerResult;
import org.eastway.echarts.shared.Tickler;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class TicklerPresenter implements Presenter, TicklerView.Presenter<Tickler> {
	private TicklerView<Tickler> view;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetTickler action;

	@Inject
	public TicklerPresenter(TicklerView<Tickler> view, List<ColumnDefinition<Tickler>> columnDefinitions, EventBus eventBus, CachingDispatchAsync dispatch, GetTickler action) {
		this.view = view;
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.view.setPresenter(this);
		this.view.setColumnDefinitions(columnDefinitions);
		this.action = action;
	}

	public void fetchData() {
		action.setSessionId(EchartsUser.sessionId);
		action.setStaffId(EchartsUser.staffId);
		dispatch.executeWithCache(action, new EchartsCallback<GetTicklerResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetTicklerResult result) {
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

	public TicklerView<Tickler> getDisplay() {
		return view;
	}

	@Override
	public void openCpstNote(Tickler tickler) {
		eventBus.fireEvent(new OpenCpstNoteEvent(tickler.getCaseNumber()));
	}

	@Override
	public void openIndividualProgressNote(Tickler tickler) {
		eventBus.fireEvent(new OpenIndividualProgressNoteEvent(tickler.getCaseNumber()));
	}

	@Override
	public void openDoctorProgressNote(Tickler tickler) {
		eventBus.fireEvent(new OpenDoctorProgressNoteEvent(tickler.getCaseNumber()));
	}

	@Override
	public void openNurseProgressNote(Tickler tickler) {
		eventBus.fireEvent(new OpenNurseProgressNoteEvent(tickler.getCaseNumber()));
	}
}
