package org.eastway.echartsrequest.server;

@SuppressWarnings("serial")
public class EchartsAuthenticationStatus implements java.io.Serializable {

	private String name;

	private EchartsAuthenticationStatus(String name) {
		this.name = name;
	}

	public static final EchartsAuthenticationStatus SUCCESS = new EchartsAuthenticationStatus("success");

	public String toString() {
		return name;
	}
}
