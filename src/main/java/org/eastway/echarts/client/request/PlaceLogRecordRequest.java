package org.eastway.echarts.client.request;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.ServiceName;

@ServiceName("org.eastway.echarts.domain.PlaceLogRecord")
public interface PlaceLogRecordRequest extends RequestContext {
	InstanceRequest<PlaceLogRecordProxy, Void> persist();
	Request<PlaceLogRecordProxy> findPlaceLogRecord(Long id);
}
