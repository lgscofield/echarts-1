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

import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.UserProxy;
import org.eastway.echarts.client.rpc.UserRequest;
import org.eastway.echarts.client.view.ProfileView;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.inject.Inject;

public class ProfilePresenter implements Presenter, ProfileView.Presenter<UserProxy> {

	private ProfileView<UserProxy> view;
	private EchartsRequestFactory requestFactory;
	private UserProxy user;
	private UserRequest request;

	@Inject
	public ProfilePresenter(ProfileView<UserProxy> view, List<ColumnDefinition<UserProxy>> columnDefinitions, EventBus eventBus, EchartsRequestFactory requestFactory) {
		this.view = view;
		this.view.setColumnDefinitions(columnDefinitions);
		this.view.setPresenter(this);
		this.requestFactory = requestFactory;
	}

	@Override
	public void go(HasWidgets container) {
		fetchData();
	}

	private void fetchData() {
		requestFactory.userRequest().findUser(EchartsUser.userName)
			.with("role")
			.with("supervisor")
				.fire(new Receiver<UserProxy>() {
			@Override
			public void onSuccess(UserProxy response) {
				if (response != null)
					setData(response);
			}
		});
	}

	@Override
	public UserProxy enableEdit(UserProxy userProxy) {
		request = requestFactory.userRequest();
		return request.edit(userProxy);
	}

	public ProfileView<UserProxy> getDisplay() {
		return view;
	}

	public void setData(UserProxy user) {
		this.user = user;
		view.setRowData(user);
	}

	public UserProxy getData() {
		return user;
	}

	@Override
	public void save(UserProxy userProxy) {
		final String program = userProxy.getProgram();
		request.persist().using(userProxy).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				view.setStatus("Settings saved");
				if (program != null)
					view.clearFirstLogin();
			}
		});
	}
}
