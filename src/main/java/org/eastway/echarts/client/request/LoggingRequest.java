package org.eastway.echarts.client.request;

import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.ServiceName;

@ServiceName("org.eastway.echarts.server.Logging")
public interface LoggingRequest extends RequestContext {
	Request<Void> logMessage(String serializedLogRecordString);
}
