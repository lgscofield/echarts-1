package org.eastway.echarts.client.scaffold;

import org.eastway.echarts.client.ui.EchartsOracle;
import org.eastway.echarts.client.ui.LoginWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
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
	@UiField LoginWidget loginWidget;
	@UiField NotificationMole mole;
	private SuggestBox suggestBox;
	@UiField FlowPanel patientIdBox;
	@UiField Style style;

	@Inject
	public ScaffoldDesktopShell(EchartsOracle oracle) {
		initWidget(BINDER.createAndBindUi(this));
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

	public LoginWidget getLoginWidget() {
		return loginWidget;
	}
}
