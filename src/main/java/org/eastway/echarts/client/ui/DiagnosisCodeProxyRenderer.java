package org.eastway.echarts.client.ui;

import org.eastway.echarts.client.request.DiagnosisCodeProxy;

import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;

public class DiagnosisCodeProxyRenderer extends ProxyRenderer<DiagnosisCodeProxy> {
	private static DiagnosisCodeProxyRenderer INSTANCE;

	public static DiagnosisCodeProxyRenderer instance() {
		if (INSTANCE == null)
			INSTANCE = new DiagnosisCodeProxyRenderer();
		return INSTANCE;
	}

	private DiagnosisCodeProxyRenderer() {
		super(new String[] {"id","description"});
	}

	@Override
	public String render(DiagnosisCodeProxy object) {
		if (object == null)
			return null;
		return object.getId() + " (" + object.getDescription() + ")";
	}

}
