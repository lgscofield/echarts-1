package org.eastway.echarts.client.view;

import org.eastway.echarts.client.presenter.ServiceHistoryPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class ServiceHistoryView extends Widget implements ServiceHistoryPresenter.Display {
	private static ServiceHistoryUiBinder uiBinder = GWT.create(ServiceHistoryUiBinder.class);

	interface ServiceHistoryUiBinder extends UiBinder<Element, ServiceHistoryView> {}

	@UiField SpanElement nameSpan;

	public ServiceHistoryView() {
		setElement(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setName(String name) {
		nameSpan.setInnerText(name);
	}
}
