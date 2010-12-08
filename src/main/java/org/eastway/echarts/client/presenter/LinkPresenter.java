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

import java.util.ArrayList;
import java.util.List;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.LinkProxy;
import org.eastway.echarts.shared.GetLinks;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.HasWidgets;

public class LinkPresenter implements Presenter {

	public interface Display extends EchartsDisplay {
		void setData(List<String[]> list);
	}

	private List<String[]> data;
	private Display display;
	private GetLinks action;
	private EchartsRequestFactory requestFactory;

	public LinkPresenter(Display display, EchartsRequestFactory requestFactory, GetLinks action) {
		this.action = action;
		this.requestFactory = requestFactory;
		this.display = display;
	}

	public void fetchData() {
		requestFactory.linkRequest().findAllLinks().fire(new Receiver<List<LinkProxy>>() {
			@Override
			public void onSuccess(List<LinkProxy> response) {
				setData(response);
				display.setData(getData());
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
			str[1] = (link.getUrl() + "?staffid=" + EchartsUser.staffId + "&PATID=" + action.getCaseNumber());
			str[2] = link.getHeader();
			data.add(str);
		}

		this.data = data;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchData();
	}
}
