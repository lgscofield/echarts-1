package org.eastway.echarts.client.request;

import org.eastway.echarts.domain.PlaceLogRecord;

import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(PlaceLogRecord.class)
public interface PlaceLogRecordRequest extends RequestContext {
	InstanceRequest<PlaceLogRecordProxy, Void> persist();
	Request<PlaceLogRecordProxy> findPlaceLogRecord(String id);
}
