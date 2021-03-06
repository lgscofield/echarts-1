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
package org.eastway.echarts.client.ui;

import java.util.HashSet;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class LinkViewImpl<T> extends Composite implements LinkView<T> {
	private static LinkViewUiBinder uiBinder = GWT.create(LinkViewUiBinder.class);

	@SuppressWarnings("unchecked")
	@UiTemplate("LinkView.ui.xml")
	interface LinkViewUiBinder extends UiBinder<Widget, LinkViewImpl> { }

	@UiField FlowPanel panel;

	public LinkViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(List<String[]> linkList) {
		panel.clear();
		HashSet<String> header = new HashSet<String>();
		for (String[] s : linkList)
			while (!header.contains(s[2]))
				header.add(s[2]);
		for (String h : header) {
			panel.add(new HTML("<p>" + h + "</p>"));
			String content = new String();
			content = "<ul>";
			for (String[] s : linkList) {
				if (s[2].equals(h))
					content += "<li>" + new Anchor(s[0], s[1], "_blank") + "</li>";
			}
			content += "</ul>";
			panel.add(new HTML(content));
		}
	}
}
