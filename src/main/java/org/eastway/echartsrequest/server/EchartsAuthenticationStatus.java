package org.eastway.echartsrequest.server;

public class EchartsAuthenticationStatus {

	private String name;

	private EchartsAuthenticationStatus(String name) {
		this.name = name;
	}

	public static final EchartsAuthenticationStatus SUCCESS = new EchartsAuthenticationStatus("success");

	public String toString() {
		return name;
	}
}
