package org.eastway.echarts.client.view;

import java.util.Vector;

import org.eastway.echarts.client.presenter.TopPanelPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class TopPanelView extends Composite implements TopPanelPresenter.Display {
	private static TopPanelViewUiBinder uiBinder = GWT.create(TopPanelViewUiBinder.class);

	interface TopPanelViewUiBinder extends UiBinder<Widget, TopPanelView> {}

	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private SuggestBox patientIdBox = new SuggestBox(oracle);
	private Button patientSearchButton = new Button("Search");

	@UiField
	ButtonStyle style;

	interface ButtonStyle extends CssResource {
		String button();

		String searchbox();
	}

	@UiField
	FlowPanel patientSearch;
	@UiField
	Anchor logoutButton;

	public TopPanelView() {
		initWidget(uiBinder.createAndBindUi(this));
		patientSearch.add(patientIdBox);
		patientSearch.add(patientSearchButton);
		patientSearchButton.addStyleName(style.button());
		patientIdBox.addStyleName(style.searchbox());
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(Vector<String> data) {
		oracle.addAll(data);
	}

	@Override
	public HasClickHandlers getSearchButton() {
		return patientSearchButton;
	}

	@Override
	public HasText getSuggestBox() {
		return patientIdBox;
	}

	@Override
	public HasClickHandlers getLogoutButton() {
		return logoutButton;
	}
}
