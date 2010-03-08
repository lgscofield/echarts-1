package org.eastway.echarts.client.view;

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
		for (String[] s : data) {
			formsList.add(new HTML("<li>" + new Anchor(s[0], s[1], "_blank") + "</li>"));
		}
	}
}
