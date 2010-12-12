package org.eastway.echarts.client.scaffold;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.ui.EchartsOracle;
import org.eastway.echarts.client.view.DashboardView.Style;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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

	@UiField SimplePanel master;
	@UiField Hyperlink username;
	@UiField NotificationMole mole;
	@Inject EchartsOracle oracle;
	private SuggestBox suggestBox;
	@UiField FlowPanel patientIdBox;
	@UiField Style style;

	public ScaffoldDesktopShell() {
		initWidget(BINDER.createAndBindUi(this));
		username.setHTML(EchartsUser.userName);
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
}
