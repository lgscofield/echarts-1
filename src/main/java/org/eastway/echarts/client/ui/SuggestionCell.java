package org.eastway.echarts.client.ui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

public class SuggestionCell extends AbstractCell<Suggestion> {
	@Override
	public void render(Context context, Suggestion value, SafeHtmlBuilder sb) {
		if (value != null && value.getDisplayString() != null) {
			sb.appendEscaped(value.getDisplayString());
		}
	}
}
