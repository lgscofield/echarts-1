package org.eastway.echarts.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class ServiceHistory extends Widget {
	private static ServiceHistoryUiBinder uiBinder = GWT.create(ServiceHistoryUiBinder.class);

	interface ServiceHistoryUiBinder extends UiBinder<Element, ServiceHistory> {}

	@UiField
	SpanElement nameSpan;

	public ServiceHistory(String firstName) {
		setElement(uiBinder.createAndBindUi(this));
		nameSpan.setInnerText(firstName);
	}
}
