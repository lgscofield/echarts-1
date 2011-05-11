package org.eastway.echarts.client.scaffold;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.activity.CurrentEhrActivityMapper;
import org.eastway.echarts.client.activity.DetailsActivityMapper;
import org.eastway.echarts.client.activity.EchartsPlaceHistoryMapper;
import org.eastway.echarts.client.activity.MasterActivityMapper;
import org.eastway.echarts.client.place.DashboardPlace;
import org.eastway.echarts.client.request.DbServerConfigProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.scaffold.request.RequestEvent;
import org.eastway.echarts.client.style.GlobalResources;
import org.eastway.echartsrequest.client.ReloadOnAuthenticationFailure;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.inject.Inject;

public class ScaffoldDesktopApp extends ScaffoldApp {

	private static final Logger log = Logger.getLogger(Echarts.class.getName());

	private ScaffoldDesktopShell shell;
	private EventBus eventBus;
	private PlaceController placeController;
	private MasterActivityMapper masterActivityMapper;
	private DetailsActivityMapper detailsActivityMapper;
	private EchartsRequestFactory requestFactory;
	private CurrentEhrActivityMapper currentEhrActivityMapper;

	@Inject
	public ScaffoldDesktopApp(ScaffoldDesktopShell shell,
							  EventBus eventBus,
							  EchartsRequestFactory requestFactory,
							  PlaceController placeController,
							  MasterActivityMapper masterActivityMapper,
							  DetailsActivityMapper detailsActivityMapper,
							  CurrentEhrActivityMapper currentEhrActivityMapper) {
		this.shell = shell;
		this.eventBus = eventBus;
		this.requestFactory = requestFactory;
		this.placeController = placeController;
		this.masterActivityMapper = masterActivityMapper;
		this.detailsActivityMapper = detailsActivityMapper;
		this.currentEhrActivityMapper = currentEhrActivityMapper;
	}

	public void run() {
		GlobalResources.styles().ensureInjected();
		init();
	}

	private void init() {



		if (LogConfiguration.loggingIsEnabled()) {
			GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
				public void onUncaughtException(Throwable e) {
					log.log(Level.SEVERE, Window.Location.createUrlBuilder().buildString(), e);
				}
			});
		}

		RequestEvent.register(eventBus, new RequestEvent.Handler() {
			// Only show loading status if a request isn't serviced in 250ms.
			private static final int LOADING_TIMEOUT = 250;

			public void onRequestEvent(RequestEvent requestEvent) {
				if (requestEvent.getState() == RequestEvent.State.SENT) {
					shell.getMole().setMessage(shell.getMoleLoadingMessage());
					shell.getMole().showDelayed(LOADING_TIMEOUT);
				} else if (requestEvent.getState() == RequestEvent.State.RECEIVED){
					shell.getMole().hide();
				} else if (requestEvent.getState() == RequestEvent.State.ERROR) {
					shell.getMole().hideNow();
					shell.getMole().show(requestEvent.getMessage());
				}
			}
		});

		new ReloadOnAuthenticationFailure().register(eventBus);

		requestFactory.dbServerConfigRequest().findDbServerConfigsByConfigName("dbServerUrl").fire(new Receiver<List<DbServerConfigProxy>>() {
			@Override
			public void onSuccess(List<DbServerConfigProxy> response) {
				EchartsUser.dbServerUrl = response.get(0).getConfigValue();
				if ((EchartsUser.userName == null || EchartsUser.userName == "null") ||
						(EchartsUser.staffId == null || EchartsUser.staffId == "null")) {
					Window.Location.assign("http://" + EchartsUser.dbServerUrl + "/echarts/logout.aspx?continue=" + Window.Location.getHref());
				} else {
					shell.setAlertMessage(response.get(0).getServerMode());
					shell.getLoginWidget().setUsername(EchartsUser.userName);
					Element loading = Document.get().getElementById("page-loading-message");
					loading.getParentElement().removeChild(loading);

					// Start ActivityManager for the main widget with our ActivityMapper
					ActivityManager masterActivityManager = new ActivityManager(masterActivityMapper, eventBus);
					masterActivityManager.setDisplay(shell.getMasterPanel());

					// Start ActivityManager for the details side bar
					ActivityManager detailsActivityManager = new ActivityManager(detailsActivityMapper, eventBus);
					detailsActivityManager.setDisplay(shell.getDetailsPanel());

					// Start ActivityManager for the CurrentEhrWidget
					ActivityManager currentEhrActivityManager = new ActivityManager(currentEhrActivityMapper, eventBus);
					currentEhrActivityManager.setDisplay(shell.getCurrentEhrPanel());

					RootLayoutPanel.get().add(shell);

					// Start PlaceHistoryHandler with our PlaceHistoryMapper
					EchartsPlaceHistoryMapper historyMapper = GWT.create(EchartsPlaceHistoryMapper.class);
					PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
					historyHandler.register(placeController, eventBus, new DashboardPlace());

					// Goes to the place represented on URL else default place
					historyHandler.handleCurrentHistory();
				}
			}
		});
	}
}
