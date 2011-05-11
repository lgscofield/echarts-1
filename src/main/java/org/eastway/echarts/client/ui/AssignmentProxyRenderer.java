package org.eastway.echarts.client.ui;

import java.io.IOException;

import org.eastway.echarts.client.request.AssignmentProxy;

import com.google.web.bindery.requestfactory.gwt.ui.client.ProxyRenderer;

public class AssignmentProxyRenderer extends ProxyRenderer<AssignmentProxy> {
	private static AssignmentProxyRenderer INSTANCE;

	public static AssignmentProxyRenderer instance() {
		if (INSTANCE == null)
			INSTANCE = new AssignmentProxyRenderer();
		return INSTANCE;
	}

	public AssignmentProxyRenderer() {
		super(new String[]{"staffName"});
	}

	@Override
	public String render(AssignmentProxy object) {
		if (object == null)
			return null;
		return object.getStaffName();
	}

	@Override
	public void render(AssignmentProxy object, Appendable appendable) {
		if (object == null)
			return;
		try {
			appendable.append(object.getStaffName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
