package org.eastway.echarts.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DashboardSideBarViewImpl extends Composite implements DashboardSideBarView {
	@SuppressWarnings("unchecked")
	@UiTemplate("DashboardSideBarView.ui.xml")
	interface DashboardSideBarViewUiBinder extends UiBinder<Widget, DashboardSideBarViewImpl> { }

	private static DashboardSideBarViewUiBinder BINDER = GWT.create(DashboardSideBarViewUiBinder.class);

	public DashboardSideBarViewImpl() {
		initWidget(BINDER.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
