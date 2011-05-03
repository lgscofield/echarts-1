package org.eastway.echarts.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.eastway.echarts.client.place.PatientSummaryPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestBox.DefaultSuggestionDisplay;
import com.google.gwt.user.client.ui.SuggestOracle.Callback;
import com.google.gwt.user.client.ui.SuggestOracle.Request;
import com.google.gwt.user.client.ui.SuggestOracle.Response;
import com.google.gwt.user.client.ui.TextBox;
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

	private final EchartsOracle oracle;

	private SuggestionDisplay suggestionDisplay;

	private static class SuggestionDisplay extends DefaultSuggestionDisplay {
		public SuggestionDisplay() {
			super();
		}

		@Override
		protected Widget decorateSuggestionList(Widget suggestionList) {
			ScrollPanel scrollPanel = new ScrollPanel(suggestionList);
			scrollPanel.setHeight("300px");
			return scrollPanel;
		}
	}

	public PatientSearchWidget(EchartsOracle oracle, PlaceController placeController) {
		initWidget(uiBinder.createAndBindUi(this));
		this.oracle = oracle;
		this.suggestionDisplay = new SuggestionDisplay();
		suggestBox = createSuggestBox();
		patientIdBox.add(suggestBox);
		this.placeController = placeController;
		setNoResultMessages();
	}

	private SuggestBox createSuggestBox() {
		SuggestBox suggestBox = new SuggestBox(oracle, new TextBox(), suggestionDisplay);
		suggestBox.addStyleName(style.searchbox());
		return suggestBox;
	}

	List<String> noResultMessages = new ArrayList<String>();
	private void setNoResultMessages() {
		noResultMessages.add("No matches found");
		noResultMessages.add("... still no results");
		noResultMessages.add("I did say no results, right?");
		noResultMessages.add("Retrieving results 1-100...oh wait, no results found.");
		noResultMessages.add("And yet you still think there might be search results for your brain dead query");
		noResultMessages.add("when will it stop?");
		noResultMessages.add("I'm speechless.");
		noResultMessages.add("This isn't funny anymore.");
		noResultMessages.add("Ok, I give up.");
	}

	private static class NoSearchResultTimer extends Timer {

		public static int FAIL_COUNT = 0;
		public static int TIMEOUT = 1000;

		@Override
		public void run() {
			TIMEOUT = 1000;
			FAIL_COUNT = 0;
		}
	}

	private String getNoResultMessage(int idx) {
		if (idx > noResultMessages.size()-1)
			return noResultMessages.get(idx%noResultMessages.size());
		return noResultMessages.get(idx);
	}

	private NoSearchResultTimer noSearchResultTimer = new NoSearchResultTimer();

	private String searchPattern = "(.*) - .*";

	@UiHandler("patientSearchButton")
	void searchClicked(ClickEvent event) {
		final String id = suggestBox.getText().replaceAll(searchPattern, "$1");
		if (id.isEmpty())
			return;
		suggestBox.getSuggestOracle().requestSuggestions(new Request(id), new Callback() {
			@Override
			public void onSuggestionsReady(Request request, Response response) {
				if (response != null && response.getSuggestions() != null && response.getSuggestions().size() == 1) {
					noSearchResultTimer.cancel();
					NoSearchResultTimer.FAIL_COUNT = 0;
					//suggestionDisplay.setEmptyListMessage(getNoResultMessage(0));
					placeController.goTo(new PatientSummaryPlace(response.getSuggestions().iterator().next().getDisplayString().replaceAll(searchPattern, "$1")));
					suggestBox.setText("");
				} else {
					noSearchResultTimer.cancel();
					noSearchResultTimer.schedule(NoSearchResultTimer.TIMEOUT += 1000);
					//suggestionDisplay.setEmptyListMessage(getNoResultMessage(NoSearchResultTimer.FAIL_COUNT++));
					suggestBox.showSuggestionList();
				}
			}
		});
	}
}
