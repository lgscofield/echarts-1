package org.eastway.echarts.client.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.event.dom.client.ScrollEvent;
import com.google.gwt.event.dom.client.ScrollHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HasAnimation;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestBox.SuggestionCallback;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;
import com.google.gwt.view.client.HasRows;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class CellListSuggestionDisplay extends SuggestBox.SuggestionDisplay
		implements HasAnimation {

	private CellList<Suggestion> suggestionCellList;
	private ListDataProvider<Suggestion> suggestionDataProvider = new ListDataProvider<Suggestion>();
	private ScrollPanel pager = new ScrollPanel();
	private PopupPanel suggestionPopup;
	final private SingleSelectionModel<Suggestion> selectionModel = new SingleSelectionModel<Suggestion>();
	private SelectionChangeEvent.Handler handler = null;
	/**
	 * We need to keep track of the last {@link SuggestBox} because it acts as
	 * an autoHide partner for the {@link PopupPanel}. If we use the same
	 * display for multiple {@link SuggestBox}, we need to switch the autoHide
	 * partner.
	 */
	private SuggestBox lastSuggestBox = null;

	private boolean hideWhenEmpty = false;
	private boolean isAnimationEnabled = false;

	private int lastScrollPos = 0;

	public CellListSuggestionDisplay() {
		suggestionCellList = new CellList<Suggestion>(new SuggestionCell());
		suggestionCellList.setEmptyListMessage(new SafeHtmlBuilder().appendEscaped("No matches found").toSafeHtml());
		suggestionCellList.setSelectionModel(selectionModel);
		suggestionCellList.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
		suggestionCellList.setPageSize(20);
		suggestionCellList
				.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		suggestionDataProvider.addDataDisplay(suggestionCellList);
		pager.setWidget(suggestionCellList);
		pager.setHeight("300px");
		suggestionPopup = createPopup();
		suggestionPopup.setWidget(pager);
		pager.getElement().setTabIndex(-1);
		pager.addScrollHandler(new ScrollHandler() {
			private int incrementSize = 20;

			public void onScroll(ScrollEvent event) {
				// If scrolling up, ignore the event.
				int oldScrollPos = lastScrollPos;
				lastScrollPos = pager.getScrollPosition();
				if (oldScrollPos >= lastScrollPos) {
					return;
				}

				HasRows display = getDisplay();
				if (display == null) {
					return;
				}
				int maxScrollTop = pager.getWidget().getOffsetHeight()
						- pager.getOffsetHeight();
				if (lastScrollPos >= maxScrollTop) {
					// We are near the end, so increase the page size.
					int newPageSize = Math.min(display.getVisibleRange()
							.getLength() + incrementSize , display.getRowCount());
					display.setVisibleRange(0, newPageSize);
				}
			}
		});
	}

	private HasRows getDisplay() {
		return suggestionCellList;
	}

	public void setEmptyListMessage(String emptyListMessage) {
		suggestionCellList.setEmptyListMessage(new SafeHtmlBuilder().appendEscaped(emptyListMessage).toSafeHtml());
	}

	private PopupPanel createPopup() {
		DecoratedPopupPanel p = new DecoratedPopupPanel(true, false);
		p.setPreviewingAllNativeEvents(true);
		return p;
	}

	@Override
	public boolean isAnimationEnabled() {
		return isAnimationEnabled;
	}

	@Override
	public void setAnimationEnabled(boolean enable) {
		this.isAnimationEnabled = enable;
		suggestionPopup.setAnimationEnabled(enable);
	}

	@Override
	protected Suggestion getCurrentSelection() {
		if (!suggestionPopup.isShowing())
			return null;
		if (suggestionCellList.getRowCount() > 0)
			return suggestionCellList.getVisibleItem(0);
		else
			return null;
	}

	@Override
	protected void hideSuggestions() {
		suggestionPopup.hide();
	}

	@Override
	protected void moveSelectionDown() {
	}

	@Override
	protected void moveSelectionUp() {
	}

	@Override
	protected void showSuggestions(SuggestBox suggestBox,
			Collection<? extends Suggestion> suggestions,
			boolean isDisplayStringHTML, boolean isAutoSelectEnabled,
			final SuggestionCallback callback) {

		boolean anySuggestions = (suggestions != null && suggestions.size() > 0);
		if (!anySuggestions && hideWhenEmpty) {
			hideSuggestions();
			return;
		}

		if (suggestionPopup.isAttached()) {
			suggestionPopup.hide();
		}

		List<Suggestion> curSuggestions = new ArrayList<Suggestion>();

		for (Suggestion suggestion : suggestions)
			curSuggestions.add(suggestion);

		if (handler == null) {
			handler = new SelectionChangeEvent.Handler() {
				@Override
				public void onSelectionChange(SelectionChangeEvent event) {
					callback.onSuggestionSelected(selectionModel
							.getSelectedObject());
				}
			};
			selectionModel.addSelectionChangeHandler(handler);
		}

		suggestionDataProvider.setList(curSuggestions);

		// Link the popup autoHide to the TextBox.
		if (lastSuggestBox != suggestBox) {
			// If the suggest box has changed, free the old one first.
			if (lastSuggestBox != null) {
				suggestionPopup.removeAutoHidePartner(lastSuggestBox
						.getElement());
			}
			lastSuggestBox = suggestBox;
			suggestionPopup.addAutoHidePartner(suggestBox.getElement());
		}
		suggestionPopup.showRelativeTo(suggestBox);
	}

}
