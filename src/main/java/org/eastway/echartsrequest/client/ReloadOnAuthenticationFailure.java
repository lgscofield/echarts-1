package org.eastway.echartsrequest.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Window.Location;

public class ReloadOnAuthenticationFailure implements EchartsAuthenticationFailureEvent.Handler {

	public HandlerRegistration register(EventBus eventBus) {
		return EchartsAuthenticationFailureEvent.register(eventBus, this);
	}

	@Override
	public void onAuthFailure(EchartsAuthenticationFailureEvent requestEvent) {
		Location.replace(requestEvent.getLoginUrl());
	}

}
