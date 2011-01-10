package org.eastway.echarts.client.ui;

import org.eastway.echarts.client.EchartsUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
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

	@UiHandler("groupProgressNote")
	void handleGroupProgressNoteClicked(ClickEvent event) {
		event.preventDefault();
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/Forms/108GroupSetup.asp?staffid=" + EchartsUser.staffId, "_blank", "");
	}

	@UiHandler("lastSeenReport")
	void handleLastSeenReportClicked(ClickEvent event) {
		event.preventDefault();
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/lastseen.asp", "_blank", "");
	}

	@UiHandler("overlapsReport")
	void handleOverLapsReportClicked(ClickEvent event) {
		event.preventDefault();
		Window.open("http://" + EchartsUser.dbServerUrl + "/echarts-asp/overlaps.asp?staffid=" + EchartsUser.staffId, "_blank", "");
	}
}
