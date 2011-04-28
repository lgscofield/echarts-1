package org.eastway.echarts.client.request;

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Request;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.requestfactory.shared.ServiceName;

@ServiceName("org.eastway.echarts.domain.PlaceLogRecord")
public interface PlaceLogRecordRequest extends RequestContext {
	InstanceRequest<PlaceLogRecordProxy, Void> persist();
	Request<PlaceLogRecordProxy> findPlaceLogRecord(Long id);
}
