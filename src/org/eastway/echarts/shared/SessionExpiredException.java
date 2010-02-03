package org.eastway.echarts.shared;

@SuppressWarnings("serial")
public class SessionExpiredException extends Exception {
	public SessionExpiredException() {
		super("Session Expired");
	}
}
