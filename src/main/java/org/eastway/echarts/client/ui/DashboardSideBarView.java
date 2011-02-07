package org.eastway.echarts.client.ui;

import java.util.List;

import org.eastway.echarts.client.request.LinkProxy;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.IsWidget;

public interface DashboardSideBarView extends IsWidget {

	void setLinks(List<LinkProxy> response);

	public interface Template extends SafeHtmlTemplates {
		@Template("<b>{0}</b>")
		SafeHtml header(String string);
	}

	public interface Style extends CssResource {
		String link();
	}

	boolean isLoaded();
}
