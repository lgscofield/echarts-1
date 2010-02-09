package org.eastway.echarts.client;

import org.eastway.echarts.client.events.LogoutEvent;
import org.eastway.echarts.client.events.LogoutEventHandler;
import org.eastway.echarts.client.presenter.DashboardPresenter;
import org.eastway.echarts.client.presenter.EchartsPresenter;
import org.eastway.echarts.client.presenter.LoginPresenter;
import org.eastway.echarts.client.view.DashboardView;
import org.eastway.echarts.client.view.LoginView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class EchartsController implements ValueChangeHandler<String> {
	private final HandlerManager eventBus;
	private final PatientServicesAsync patientSvc;
	private HasWidgets container;

	public EchartsController(PatientServicesAsync patientSvc, HandlerManager eventBus) {
		this.patientSvc = patientSvc;
		this.eventBus = eventBus;
		bind();
	}

	private void bind() {
		History.addValueChangeHandler(this);

		eventBus.addHandler(LogoutEvent.TYPE, new LogoutEventHandler() {
			@Override
			public void onLogout(LogoutEvent event) {
				doLogout();
			}
		});
	}

	protected void doLogout() {
		Cookies.removeCookie("sessionId", "/");
		HandleRpcException.setSessionExpiredState(false);
		EchartsPresenter<LoginPresenter.Display> presenter = new LoginPresenter(new LoginView(), eventBus, patientSvc);
		container.clear();
		container.add(presenter.getDisplay().asWidget());
	}

	public void go(HasWidgets container) {
		String sessionId = Cookies.getCookie("sessionId");
		this.container = container;
		if (sessionId == null || sessionId == "null") {
			EchartsPresenter<LoginPresenter.Display> presenter = new LoginPresenter(new LoginView(), eventBus, patientSvc);
			container.clear();
			container.add(presenter.getDisplay().asWidget());
		} else {
			if ("".equals(History.getToken())) {
				History.newItem("dashboard");
			} else {
				History.fireCurrentHistoryState();
			}
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
		String sessionId = Cookies.getCookie("sessionId");
		String token = event.getValue();
		if (token != null) {
			if (sessionId == null || sessionId == "null") {
				EchartsPresenter<LoginPresenter.Display> presenter = new LoginPresenter(new LoginView(), eventBus, patientSvc);
				container.clear();
				container.add(presenter.getDisplay().asWidget());
			} else if (token.equals("dashboard")) {
				EchartsPresenter<DashboardPresenter.Display> presenter = new DashboardPresenter(new DashboardView(), eventBus, patientSvc);
				container.clear();
				container.add(presenter.getDisplay().asWidget());
			}
		}
	}
}
