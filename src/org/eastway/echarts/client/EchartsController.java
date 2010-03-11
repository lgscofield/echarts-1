package org.eastway.echarts.client;

import org.eastway.echarts.client.events.LogoutEvent;
import org.eastway.echarts.client.events.LogoutEventHandler;
import org.eastway.echarts.client.presenter.DashboardPresenter;
import org.eastway.echarts.client.presenter.EchartsPresenter;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.shared.UserData;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class EchartsController implements ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private HasWidgets container;

	public EchartsController(HandlerManager eventBus) {
		this.eventBus = eventBus;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
			@Override
			public void onLogout(LogoutEvent event) {
			}
		});
	}

	public void go(HasWidgets container) {
		String sessionId = Cookies.getCookie("sessionId");
		this.container = container;
		if (sessionId != null && sessionId != "null")
			fetchUser(sessionId);
	}

	private void fetchUser(String sessionId) {
		AsyncCallback<UserData> callback = new AsyncCallback<UserData>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(UserData userData) {
				userData.initUser();
				if ("".equals(History.getToken())) {
					History.newItem("dashboard");
				} else {
					History.fireCurrentHistoryState();
				}
			}
		};
		EchartsRpc.getRpc().getUserData(sessionId, callback);
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String token = event.getValue();
		if (token != null) {
			if (token.equals("dashboard")) {
				EchartsPresenter<DashboardPresenter.Display> presenter = new DashboardPresenter(new DashboardView(), eventBus);
				container.clear();
				container.add(presenter.getDisplay().asWidget());
			}
		}
	}
}
