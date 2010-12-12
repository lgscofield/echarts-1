package org.eastway.echarts.client.scaffold;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.mvp.EchartsActivityMapper;
import org.eastway.echarts.client.mvp.EchartsPlaceHistoryMapper;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.style.client.GlobalResources;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;

public class ScaffoldDesktopApp extends ScaffoldApp {

	private ScaffoldDesktopShell shell;
	private EventBus eventBus;
	private EchartsRequestFactory requestFactory;
	private PlaceController placeController;
	private EchartsActivityMapper activityMapper;

	@Inject
	public ScaffoldDesktopApp(ScaffoldDesktopShell shell, EventBus eventBus,
							  EchartsRequestFactory requestFactory, PlaceController placeController,
							  EchartsActivityMapper activityMapper) {
		this.shell = shell;
		this.eventBus = eventBus;
		this.requestFactory = requestFactory;
		this.placeController = placeController;
		this.activityMapper = activityMapper;
	}

	public void run() {
		init();

		Element loading = Document.get().getElementById("page-loading-message");
		loading.getParentElement().removeChild(loading);

		EchartsUser.sessionId = Cookies.getCookie("session_id");
		EchartsUser.userName = Cookies.getCookie("echarts_user");
		EchartsUser.staffId = Cookies.getCookie("staff_id");
		EchartsUser.staffId = "5434"; // for testing
		GlobalResources.resources().css().ensureInjected();
		Window.enableScrolling(false);

		RootLayoutPanel.get().add(shell);

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(shell.getMasterPanel());

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		EchartsPlaceHistoryMapper historyMapper = GWT.create(EchartsPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, new TicklerPlace());

		requestFactory.initialize(eventBus);

		// Goes to the place represented on URL else default place
		historyHandler.handleCurrentHistory();
	}

	private void init() {
		RequestEvent.register(eventBus, new RequestEvent.Handler() {
			// Only show loading status if a request isn't serviced in 250ms.
			private static final int LOADING_TIMEOUT = 250;

			public void onRequestEvent(RequestEvent requestEvent) {
				if (requestEvent.getState() == RequestEvent.State.SENT) {
					shell.getMole().showDelayed(LOADING_TIMEOUT);
				} else {
					shell.getMole().hide();
				}
			}
		});
	}
}
