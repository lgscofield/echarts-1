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
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.client.view.ProfileView;
import org.eastway.echarts.shared.GetProfile;
import org.eastway.echarts.shared.GetProfileResult;
import org.eastway.echarts.shared.SaveProfile;
import org.eastway.echarts.shared.SaveProfileResult;
import org.eastway.echarts.shared.User;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class ProfilePresenter implements Presenter, ProfileView.Presenter<User> {

	private ProfileView<User> view;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private User user;

	@Inject
	public ProfilePresenter(ProfileView<User> view, List<ColumnDefinition<User>> columnDefinitions, EventBus eventBus, CachingDispatchAsync dispatch) {
		this.view = view;
		this.view.setColumnDefinitions(columnDefinitions);
		this.view.setPresenter(this);
		this.eventBus = eventBus;
		this.dispatch = dispatch;
	}

	@Override
	public void go(HasWidgets container) {
		fetchData();
	}

	private void fetchData() {
		dispatch.executeWithCache(new GetProfile(EchartsUser.sessionId, EchartsUser.userName), new EchartsCallback<GetProfileResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetProfileResult result) {
				try {
					setData(result.getUser());
				} catch (NullPointerException e) {
					setData(null);
				}
			}
		});
	}

	public ProfileView<User> getDisplay() {
		return view;
	}

	public void setData(User user) {
		this.user = user;
		view.setRowData(user);
	}

	public User getData() {
		return user;
	}

	@Override
	public void save(User data) {
		SaveProfile action = new SaveProfile(EchartsUser.sessionId);
		action.setUser(data);
		dispatch.executeWithCache(action, new EchartsCallback<SaveProfileResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(SaveProfileResult t) {
				view.setStatus("Settings saved");
				try {
					setData(t.getUser());
				} catch (NullPointerException e) {
					setData(null);
				}
			}
		});
	}
}
