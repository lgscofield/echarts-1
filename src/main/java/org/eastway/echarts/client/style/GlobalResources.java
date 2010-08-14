package org.eastway.echarts.client.style;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;

public interface GlobalResources extends ClientBundle {
	@NotStrict
	@Source("echarts.css")
	CssResource css();

	interface Styles extends CssResource {
		String table();
	}
}
