package org.eastway.echarts.client.ui;

import org.eastway.echarts.client.request.CodeProxy;

import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;

public class CodeProxyRenderer extends ProxyRenderer<CodeProxy> {
	private static CodeProxyRenderer INSTANCE;

	public static CodeProxyRenderer instance() {
		if (INSTANCE == null)
			INSTANCE = new CodeProxyRenderer();
		return INSTANCE;
	}

	protected CodeProxyRenderer() {
		super(new String[] {"codeDescriptor"});
	}

	@Override
	public String render(CodeProxy object) {
		if (object == null)
			return null;
		return object.getCodeDescriptor();
	}

}
