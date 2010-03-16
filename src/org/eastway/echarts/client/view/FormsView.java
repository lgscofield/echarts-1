package org.eastway.echarts.client.view;

import java.util.HashSet;
import java.util.LinkedHashSet;

import org.eastway.echarts.client.presenter.FormsPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class FormsView extends Composite implements FormsPresenter.Display {
	private static FormsViewUiBinder uiBinder = GWT.create(FormsViewUiBinder.class);

	interface FormsViewUiBinder extends UiBinder<Widget, FormsView> { }

	@UiField FlowPanel formsList;

	public FormsView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(LinkedHashSet<String[]> data) {
		HashSet<String> header = new HashSet<String>();
		for (String[] s : data)
			while (!header.contains(s[2]))
				header.add(s[2]);
		for (String h : header) {
			formsList.add(new HTML("<p>" + h + "</p>"));
			String content = new String();
			content = "<ul>";
			for (String[] s : data) {
				if (s[2].equals(h))
					content += "<li>" + new Anchor(s[0], s[1], "_blank") + "</li>";
			}
			content += "</ul>";
			formsList.add(new HTML(content));
		}
	}
}
