package org.eastway.echarts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Echarts implements EntryPoint {
	private PatientServicesAsync patientSvc = GWT.create(PatientServices.class);
	private RootLayoutPanel root = RootLayoutPanel.get();
	private HandlerManager eventBus = new HandlerManager(null);
	private EchartsController echartsController = new EchartsController(patientSvc, eventBus);

	interface GlobalResources extends ClientBundle {
		@NotStrict
		@Source("echarts.css")
		CssResource css();
	}

	@Override
	public void onModuleLoad() {
		GWT.<GlobalResources>create(GlobalResources.class).css().ensureInjected();
		Window.setMargin("0px");
		Window.enableScrolling(false);
		echartsController.go(root);
	}
}
