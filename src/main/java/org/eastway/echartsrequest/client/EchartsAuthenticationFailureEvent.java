package org.eastway.echartsrequest.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class EchartsAuthenticationFailureEvent extends GwtEvent<EchartsAuthenticationFailureEvent.Handler> {
	public interface Handler extends EventHandler {
		void onAuthFailure(EchartsAuthenticationFailureEvent requestEvent);
	}

	private static final Type<Handler> TYPE = new Type<Handler>();

	public static HandlerRegistration register(EventBus eventBus, EchartsAuthenticationFailureEvent.Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}

	private final String loginUrl;

	public EchartsAuthenticationFailureEvent(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.onAuthFailure(this);
	}
}
