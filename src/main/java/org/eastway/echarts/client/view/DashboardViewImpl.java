package org.eastway.echarts.client.view;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DashboardViewImpl<T> extends Composite implements DashboardView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("Dashboard.ui.xml")
	interface DashboardViewUiBinder extends UiBinder<Widget, DashboardViewImpl> { }

	private static DashboardViewUiBinder uiBinder = GWT.create(DashboardViewUiBinder.class);

	@UiField Anchor logoutButton;
	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();

	public DashboardViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		logoutButton.setHref("http://ewsql.eastway.local/echarts/logout.aspx?continue=" + Window.Location.getHref());
	}

	private Presenter<T> presenter;

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		this.presenter = presenter;
	}

	@UiField TabLayoutPanel tabLayoutPanel;

	@Override
	public void addTab(final Widget widget, String string) {
		Label closeTab = new Label();
		HorizontalPanel tabHeader = new HorizontalPanel();
		closeTab.setTitle("Close");
		closeTab.addStyleName("close-Tab");
		closeTab.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Integer idx = tabLayoutPanel.getWidgetIndex(widget);
				if (tabLayoutPanel.remove(idx))
					tabLayoutPanel.selectTab(idx - 1);
			}
		});
		tabHeader.add(new Label(string));
		tabHeader.add(closeTab);
		tabLayoutPanel.add(widget, tabHeader);
		setSelectedTab(tabLayoutPanel.getWidgetIndex(widget));
	}

	@Override
	public void setSelectedTab(int idx) {
		tabLayoutPanel.selectTab(idx);
	}

	@UiFactory SuggestBox initSuggestBox() {
		return new SuggestBox(oracle);
	}

	@Override
	public void setPatientSearchData(List<String> list) {
		for (String s : list)
			oracle.add(s);
	}

	@Override
	public void addPatientSearchData(String str) {
		oracle.add(str);
	}
}
