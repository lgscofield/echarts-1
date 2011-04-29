package org.eastway.echarts.client.scaffold;

import org.eastway.echarts.client.ui.EchartsNotificationMole;
import org.eastway.echarts.client.ui.EchartsOracle;
import org.eastway.echarts.client.ui.PatientSearchWidget;
import org.eastway.echartsrequest.client.LoginWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ScaffoldDesktopShell extends Composite {
	interface Binder extends UiBinder<Widget, ScaffoldDesktopShell> {
	}

	private static final Binder BINDER = GWT.create(Binder.class);

	@UiField SimplePanel master;
	@UiField SimplePanel details;
	@UiField LoginWidget loginWidget;
	@UiField EchartsNotificationMole mole;
	@UiField SimplePanel currentEhrPanel;

	private String moleLoadingMessage = "Loading ...";
	private EchartsOracle oracle;
	private PlaceController placeController;

	@Inject
	public ScaffoldDesktopShell(EchartsOracle oracle, PlaceController placeController) {
		this.oracle = oracle;
		this.placeController = placeController;
		initWidget(BINDER.createAndBindUi(this));
	}

	public EchartsNotificationMole getMole() {
		return mole;
	}

	public String getMoleLoadingMessage() {
		return moleLoadingMessage;
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

	public SimplePanel getCurrentEhrPanel() {
		return currentEhrPanel;
	}

	interface Style extends CssResource {
		String alertMessage();
	}

	@UiField Style style;
	@UiField SpanElement alertMessage;
	@UiField DivElement alertMessageContainer;

	public void setAlertMessage(String serverMode) {
		if (serverMode != null && serverMode.length() != 0) {
			alertMessageContainer.setClassName(style.alertMessage());
			alertMessage.setInnerText(serverMode);
		}
	}
}
