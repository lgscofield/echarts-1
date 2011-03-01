package org.eastway.echarts.client.ui;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.request.LinkProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

public class DashboardSideBarViewImpl extends Composite implements DashboardSideBarView {
	@SuppressWarnings("unchecked")
	@UiTemplate("DashboardSideBarView.ui.xml")
	interface DashboardSideBarViewUiBinder extends UiBinder<Widget, DashboardSideBarViewImpl> { }

	private static DashboardSideBarViewUiBinder BINDER = GWT.create(DashboardSideBarViewUiBinder.class);

	@UiField FlowPanel panel;
	@UiField Style style;
	@UiField Hyperlink tickler;

	private List<LinkProxy> links;

	private Presenter presenter;

	private static final Template TEMPLATE = GWT.create(Template.class);

	public DashboardSideBarViewImpl() {
		initWidget(BINDER.createAndBindUi(this));
		tickler.setTargetHistoryToken("TicklerPlace:" + EchartsUser.staffId);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@UiHandler("groupProgressNote")
	void handleGroupProgressNoteClicked(ClickEvent event) {
		event.preventDefault();
		Window.open(new UrlBuilder()
			.setProtocol(Window.Location.getProtocol())
			.setHost(EchartsUser.dbServerUrl)
			.setPath("/echarts-asp/Forms/108GroupSetup.asp")
			.setParameter("staffid",EchartsUser.staffId).buildString(), "_blank", "");
	}

	@Override
	public void setLinks(List<LinkProxy> links) {
		this.links = links;
		Set<String> headers = new HashSet<String>();

		for (LinkProxy link : links)
			headers.add(link.getHeader());

		for (String header : headers) {
			panel.add(new HTML(TEMPLATE.header(header)));
			for (LinkProxy link : links) {
				if (link.getHeader().equals(header)) {
					Hyperlink hyperlink = new Hyperlink(SafeHtmlUtils.fromString(link.getName()), "DashboardFramePlace:" + link.getUrl());
					panel.add(hyperlink);
					hyperlink.addStyleName(style.link());
				}
			}
		}
	}

	@Override
	public boolean isLoaded() {
		if (links == null)
			return false;
		return true;
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
}
