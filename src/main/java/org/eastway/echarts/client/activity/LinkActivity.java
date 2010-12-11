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
package org.eastway.echarts.client.activity;

import java.util.ArrayList;
import java.util.List;

import org.eastway.echarts.client.EchartsClientFactory;
import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.LinkPlace;
import org.eastway.echarts.client.rpc.LinkProxy;
import org.eastway.echarts.client.ui.LinkView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class LinkActivity extends AbstractActivity implements LinkView.Presenter<LinkProxy> {
	private List<String[]> data;
	private String caseNumber;
	private EchartsClientFactory clientFactory;
	private LinkView<LinkProxy> view;

	public LinkActivity(LinkPlace place, EchartsClientFactory clientFactory) {
		this.caseNumber = place.getCaseNumber();
		this.clientFactory = clientFactory;
	}

	public void fetchData() {
		clientFactory.getRequestFactory().linkRequest().findAllLinks().fire(new Receiver<List<LinkProxy>>() {
			@Override
			public void onSuccess(List<LinkProxy> response) {
				if (response != null) {
					setData(response);
					view.setData(getData());
				}
			}
			
		});
	}

	public List<String[]> getData() {
		return data;
	}

	public void setData(List<LinkProxy> linkList) {
		List<String[]> data = new ArrayList<String[]>();

		for (LinkProxy link : linkList) {
			String[] str = new String[3];
			str[0] = link.getName();
			str[1] = (link.getUrl() + "?staffid=" + EchartsUser.staffId + "&PATID=" + caseNumber);
			str[2] = link.getHeader();
			data.add(str);
		}

		this.data = data;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view = clientFactory.getLinkView();
		panel.setWidget(view.asWidget());
		fetchData();
	}
}
