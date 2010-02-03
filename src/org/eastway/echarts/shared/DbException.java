package org.eastway.echarts.shared;

@SuppressWarnings("serial")
public class DbException extends Exception {
	final static String message = "Database Error";

	public DbException() {
		super(message);
	}

	public DbException(String message) {
		super(message);
	}

	public DbException(Exception cause) {
		super(cause);
	}

	public DbException(String message, Exception cause) {
		super(message, cause);
	}
}
