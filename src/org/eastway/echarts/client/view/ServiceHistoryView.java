package org.eastway.echarts.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

public class ServiceHistoryView extends Widget {
	private static ServiceHistoryUiBinder uiBinder = GWT.create(ServiceHistoryUiBinder.class);

	interface ServiceHistoryUiBinder extends UiBinder<Element, ServiceHistoryView> {}

	@UiField
	SpanElement nameSpan;

	public ServiceHistoryView(String firstName) {
		setElement(uiBinder.createAndBindUi(this));
		nameSpan.setInnerText(firstName);
	}
}
