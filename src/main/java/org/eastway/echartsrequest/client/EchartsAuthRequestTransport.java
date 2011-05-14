package org.eastway.echartsrequest.client;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.web.bindery.requestfactory.gwt.client.DefaultRequestTransport;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class EchartsAuthRequestTransport extends DefaultRequestTransport {
	private final EventBus eventBus;

	public EchartsAuthRequestTransport(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	@Override
	protected RequestCallback createRequestCallback(final TransportReceiver receiver) {
		final RequestCallback superCallback = super.createRequestCallback(receiver);
		return new RequestCallback() {
			@Override
			public void onResponseReceived(Request request, Response response) {
				if (Response.SC_UNAUTHORIZED == response.getStatusCode()) {
					String loginUrl = response.getHeader("login");
					if (loginUrl != null) {
						receiver.onTransportFailure(new ServerFailure("Unauthenticated user", null, null, false));
						eventBus.fireEvent(new EchartsAuthenticationFailureEvent(loginUrl));
						return;
					}
				} else if (response.getStatusCode() == Response.SC_INTERNAL_SERVER_ERROR) {
					receiver.onTransportFailure(new ServerFailure("Server Failure: Internal Server Error"));
					return;
				} else if (response.getStatusCode() == Response.SC_NOT_IMPLEMENTED) {
					receiver.onTransportFailure(new ServerFailure("Server Failure: Not Implemented"));
					return;
				} else if (response.getStatusCode() == Response.SC_BAD_GATEWAY) {
					receiver.onTransportFailure(new ServerFailure("Server Failure: Bad Gateway"));
					return;
				} else if (response.getStatusCode() == Response.SC_SERVICE_UNAVAILABLE) {
					receiver.onTransportFailure(new ServerFailure("Server Failure: Service Unavailable"));
					return;
				} else if (response.getStatusCode() == Response.SC_GATEWAY_TIMEOUT) {
					receiver.onTransportFailure(new ServerFailure("Server Failure: Gateway Timeout"));
					return;
				} else if (response.getStatusCode() != Response.SC_OK) {
					receiver.onTransportFailure(new ServerFailure("Server failure: " + response.getStatusCode()));
					return;
				}
				superCallback.onResponseReceived(request, response);
			}

			@Override
			public void onError(Request request, Throwable exception) {
				superCallback.onError(request, exception);
			}
		};
	}
}
