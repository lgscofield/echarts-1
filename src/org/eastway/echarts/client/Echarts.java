package org.eastway.echarts.client;

import org.eastway.echarts.appcontroller.client.AdministratorController;
import org.eastway.echarts.shared.User;
import org.eastway.echarts.shared.UserData;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.CssResource.NotStrict;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Echarts implements EntryPoint {
	private RootLayoutPanel root = RootLayoutPanel.get();

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
		go();
	}

	public void go() {
		String sessionId = Cookies.getCookie("sessionId");
		if (sessionId != null && sessionId != "null")
			fetchUser(sessionId);
		else
			Window.alert("Please login");
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
				UserImpl.setSessionId(Cookies.getCookie("sessionId"));
				switch (UserImpl.getJobClassId()) {
				case User.Role.ACCOUNTS_RECEIVABLE : break;
				case User.Role.ADMINISTRATOR :
					AdministratorController controller = new AdministratorController(new HandlerManager(null));
					controller.go(root);
					break;
				case User.Role.CLINICAL_ADMINISTRATOR : break;
				case User.Role.COMMUNITY_SUPPORT_SPECIALIST : break;
				case User.Role.GENERAL_ADMINISTRATOR : break;
				case User.Role.HUMAN_RESOURCES : break;
				case User.Role.NURSE : break;
				case User.Role.PSYCHIATRIST : break;
				case User.Role.RESIDENTIAL_SUPPORT_SPECIALIST : break;
				case User.Role.THERAPIST : break;
				case User.Role.UNPRIVILEGED : break;
				}
			}
		};
		Rpc.singleton().getUserData(Cookies.getCookie("echarts_user"), sessionId, callback);
	}
}
