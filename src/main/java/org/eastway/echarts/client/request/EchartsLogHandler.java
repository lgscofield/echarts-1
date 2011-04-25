package org.eastway.echarts.client.request;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.place.PlaceLogRecordBuilder;

import com.google.gwt.logging.client.JsonLogRecordClientUtil;
import com.google.gwt.logging.client.RemoteLogHandlerBase;
import com.google.gwt.requestfactory.shared.Receiver;

public class EchartsLogHandler extends RemoteLogHandlerBase {

	private LoggingRequestProvider requestProvider;

	public static interface LoggingRequestProvider {
		/**
		 * Returns the logging request.
		 * 
		 * @return a {@link LoggingRequest} instance
		 */
		LoggingRequest getLoggingRequest();
	}

	public EchartsLogHandler(LoggingRequestProvider requestProvider,
			Level level, List<String> ignoredLoggerNames) {
		super(ignoredLoggerNames);
		this.requestProvider = requestProvider;
		setLevel(level);
	}

	@Override
	public void publish(LogRecord record) {
		// if (!isLoggable(record)) {
		// return;
		// }
		String msg = new PlaceLogRecordBuilder(EchartsUser.userName,
				record.getMessage(),
				record.getThrown() != null ? record.getThrown().getMessage() : "",
				record.getLevel() != null ? record.getLevel().getName() : "")
				.toString();
		record.setMessage(msg);

		String json = JsonLogRecordClientUtil.logRecordAsJson(record);
		requestProvider.getLoggingRequest().logMessage(json)
				.fire(new Receiver<Void>() {
					@Override
					public void onSuccess(Void response) {
						// Do nothing
					}
				});
	}
}
