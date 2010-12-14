package org.eastway.echarts.client.scaffold;

import org.eastway.echarts.client.ui.EchartsOracle;
import org.eastway.echarts.client.ui.LoginWidget;
import org.eastway.echarts.client.ui.PatientSearchWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.NotificationMole;
import com.google.gwt.user.client.ui.SimplePanel;
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
		String label();
	}

	@UiField SimplePanel master;
	@UiField SimplePanel details;
	@UiField LoginWidget loginWidget;
	@UiField NotificationMole mole;
	@UiField Style style;
	private EchartsOracle oracle;
	private PlaceController placeController;

	@Inject
	public ScaffoldDesktopShell(EchartsOracle oracle, PlaceController placeController) {
		this.oracle = oracle;
		this.placeController = placeController;
		initWidget(BINDER.createAndBindUi(this));
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

	@UiFactory
	PatientSearchWidget createPatientSearchWidget() {
		return new PatientSearchWidget(oracle, placeController);
	}
}
