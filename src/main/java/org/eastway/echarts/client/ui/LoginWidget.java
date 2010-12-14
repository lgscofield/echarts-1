package org.eastway.echarts.client.ui;

import org.eastway.echarts.client.EchartsUser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public class LoginWidget extends Composite {

	interface LoginWidgetUiBinder extends UiBinder<Widget, LoginWidget> {}

	LoginWidgetUiBinder BINDER = GWT.create(LoginWidgetUiBinder.class);

	@UiField Hyperlink userName;
	@UiField Hyperlink logout;

	public LoginWidget() {
		initWidget(BINDER.createAndBindUi(this));
	}

	@UiHandler("logout")
	void handleLogout(ClickEvent event) {
		Window.Location.assign("http://" + EchartsUser.dbServerUrl + "/echarts/logout.aspx?continue=" + Window.Location.getHref());
	}

	public void setUsername(String userName) {
		this.userName.setText(userName);
	}
}
