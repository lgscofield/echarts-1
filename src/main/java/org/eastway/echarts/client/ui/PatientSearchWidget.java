package org.eastway.echarts.client.ui;

import org.eastway.echarts.client.place.PatientSummaryPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class PatientSearchWidget extends Composite {

	private static PatientSearchWidgetUiBinder uiBinder = GWT
			.create(PatientSearchWidgetUiBinder.class);

	interface PatientSearchWidgetUiBinder extends
			UiBinder<Widget, PatientSearchWidget> {
	}

	interface Style extends CssResource {
		String searchbox();
	}

	private SuggestBox suggestBox;

	@UiField Style style;
	@UiField FlowPanel patientIdBox;
	@UiField Button patientSearchButton;

	private PlaceController placeController;

	public PatientSearchWidget(EchartsOracle oracle, PlaceController placeController) {
		initWidget(uiBinder.createAndBindUi(this));
		suggestBox = new SuggestBox(oracle);
		suggestBox.addStyleName(style.searchbox());
		patientIdBox.add(suggestBox);
		this.placeController = placeController;
	}

	@UiHandler("patientSearchButton")
	void searchClicked(ClickEvent event) {
		String id = suggestBox.getText();
		if (id.isEmpty())
			return;
		placeController.goTo(new PatientSummaryPlace(id.replaceAll("(.*) - .*", "$1")));
		suggestBox.setText("");
	}
}
