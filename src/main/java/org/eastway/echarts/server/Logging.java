package org.eastway.echarts.server;

import java.util.logging.LogRecord;

import org.eastway.echarts.domain.PlaceLogRecord;

import com.google.gwt.logging.server.JsonLogRecordServerUtil;
import com.google.gwt.logging.server.RemoteLoggingServiceUtil.RemoteLoggingException;

public class Logging {
	public static void logMessage(String logRecordJson) throws RemoteLoggingException {
		LogRecord lr;
		try {
			lr = JsonLogRecordServerUtil
					.logRecordFromJson(logRecordJson);
			logOnServer(lr);
		} catch (Exception e) {
			// We don't want to import the JsonException, which will require the
			// json
			// jar when this class loads, so we just catch all exceptions here
			throw new RemoteLoggingException("Failed to deserialize JSON", e);
		}
	}

	public static void logOnServer(LogRecord record) {
		PlaceLogRecord placeRecord = new PlaceLogRecord();
		placeRecord.setMessage(record.getMessage());
		placeRecord.setTimestamp(record.getMillis());
		placeRecord.persist();
	}
}
