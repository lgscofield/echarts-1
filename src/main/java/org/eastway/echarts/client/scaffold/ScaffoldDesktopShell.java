package org.eastway.echarts.client.scaffold;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.ui.EchartsOracle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.NotificationMole;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ScaffoldDesktopShell extends Composite {
	interface Binder extends UiBinder<Widget, ScaffoldDesktopShell> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	interface Style extends CssResource {
		String green();
		String yellow();
		String red();
		String button();
		String searchbox();
		String label();
	}

	@UiField SimplePanel master;
	@UiField SimplePanel details;
	@UiField Hyperlink username;
	@UiField Hyperlink logout;
	@UiField NotificationMole mole;
	private SuggestBox suggestBox;
	@UiField FlowPanel patientIdBox;
	@UiField Style style;

	@Inject
	public ScaffoldDesktopShell(EchartsOracle oracle) {
		initWidget(BINDER.createAndBindUi(this));
		username.setText(EchartsUser.userName);
		this.suggestBox = new SuggestBox(oracle);
		suggestBox.addStyleName(style.searchbox());
		patientIdBox.add(suggestBox);
	}

	public NotificationMole getMole() {
		return mole;
	}

	public SimplePanel getMasterPanel() {
		return master;
	}

	public SimplePanel getDetailsPanel() {
		return details;
	}

	@UiHandler("logout")
	void handleLogout(ClickEvent event) {
		Window.Location.assign("http://" + EchartsUser.dbServerUrl + "/echarts/logout.aspx?continue=" + Window.Location.getHref());
	}
}
