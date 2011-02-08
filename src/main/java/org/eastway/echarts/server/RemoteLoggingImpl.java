package org.eastway.echarts.server;

import java.util.logging.LogRecord;

import org.eastway.echarts.domain.PlaceLogRecord;

import com.google.gwt.logging.shared.RemoteLoggingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RemoteLoggingImpl extends RemoteServiceServlet implements RemoteLoggingService {
	@Override
	public String logOnServer(LogRecord record) {
		PlaceLogRecord placeRecord = new PlaceLogRecord();
		placeRecord.setMessage(record.getMessage());
		placeRecord.setTimestamp(record.getMillis());
		placeRecord.persist();
		return null;
	}
}
