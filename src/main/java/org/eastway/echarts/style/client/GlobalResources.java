package org.eastway.echarts.style.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;

public class GlobalResources {

	public static Resources resources;
	public static CssResource styles;

	public interface Resources extends ClientBundle {
		@NotStrict
		@Source("echarts.css")
		Styles css();
	}

	public interface Styles extends CssResource {
		String table();
	}

	static {
		resources = GWT.create(Resources.class);
	}

	public static Resources resources() {
		return resources;
	}

	public static Styles styles() {
		return resources.css();
	}
}
