package org.eastway.echarts.client.scaffold;

import org.eastway.echarts.client.activity.DetailsActivityMapper;
import org.eastway.echarts.client.activity.EchartsPlaceHistoryMapper;
import org.eastway.echarts.client.activity.MasterActivityMapper;
import org.eastway.echarts.client.place.DashboardPlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;

public class ScaffoldDesktopApp extends ScaffoldApp {

	private ScaffoldDesktopShell shell;
	private EventBus eventBus;
	private PlaceController placeController;
	private MasterActivityMapper masterActivityMapper;
	private DetailsActivityMapper detailsActivityMapper;

	@Inject
	public ScaffoldDesktopApp(ScaffoldDesktopShell shell,
							  EventBus eventBus,
							  PlaceController placeController,
							  MasterActivityMapper masterActivityMapper,
							  DetailsActivityMapper detailsActivityMapper) {
		this.shell = shell;
		this.eventBus = eventBus;
		this.placeController = placeController;
		this.masterActivityMapper = masterActivityMapper;
		this.detailsActivityMapper = detailsActivityMapper;
	}

	public void run() {
		init();

		Element loading = Document.get().getElementById("page-loading-message");
		loading.getParentElement().removeChild(loading);

		Window.enableScrolling(false);

		// Start ActivityManager for the main widget with our ActivityMapper
		ActivityManager masterActivityManager = new ActivityManager(masterActivityMapper, eventBus);
		masterActivityManager.setDisplay(shell.getMasterPanel());

		// Start ActivityManager for the details side bar
		ActivityManager detailsActivityManager = new ActivityManager(detailsActivityMapper, eventBus);
		detailsActivityManager.setDisplay(shell.getDetailsPanel());

		RootLayoutPanel.get().add(shell);

		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		EchartsPlaceHistoryMapper historyMapper = GWT.create(EchartsPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, new DashboardPlace());

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
