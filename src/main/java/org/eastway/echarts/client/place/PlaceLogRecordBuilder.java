package org.eastway.echarts.client.place;

import java.util.logging.Level;

import org.eastway.echarts.client.EchartsUser;

import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window.Location;

public class PlaceLogRecordBuilder {

	private JSONObject record = new JSONObject();

	public final static String USERNAME_KEY = "username";
	public final static String URL_KEY = "url";
	public final static String MESSAGE_KEY = "message";
	public final static String LOG_LEVEL_KEY = "log_level";
	public final static String VERSION = "version";
	public final static String USER_AGENT = "user_agent";

	public PlaceLogRecordBuilder() {
		setUsername(EchartsUser.userName);
		setUrl(Location.createUrlBuilder().buildString());
		setMessage("");
		setLogLevel(Level.INFO);
	}

	public PlaceLogRecordBuilder(String username, String url, String message, String logLevel) {
		setUsername(username);
		setUrl(url);
		setMessage(message);
		setLogLevel(logLevel);
	}

	public PlaceLogRecordBuilder(String username, String url, Level logLevel, String version, String userAgent) {
		setUsername(username);
		setUrl(url);
		setLogLevel(logLevel);
		setVersion(version);
		setUserAgent(userAgent);
	}

	public PlaceLogRecordBuilder(String username, String url, String message, Level logLevel) {
		setUsername(username);
		setUrl(url);
		setMessage(message);
		setLogLevel(logLevel);
	}

	public void setUsername(String username) {
		record.put(USERNAME_KEY, new JSONString(username));
	}

	public void setUrl(String url) {
		record.put(URL_KEY, new JSONString(url));
	}

	public void setMessage(String message) {
		record.put(MESSAGE_KEY, new JSONString(message));
	}

	public void setLogLevel(String logLevel) {
		record.put(LOG_LEVEL_KEY, new JSONString(logLevel));
	}

	public void setLogLevel(Level logLevel) {
		record.put(LOG_LEVEL_KEY, new JSONString(logLevel.getName()));
	}

	public void setVersion(String version) {
		record.put(VERSION, new JSONString(version));
	}

	public void setUserAgent(String userAgent) {
		record.put(USER_AGENT, new JSONString(userAgent));
	}

	@Override
	public String toString() {
		return record.toString();
	}
}
