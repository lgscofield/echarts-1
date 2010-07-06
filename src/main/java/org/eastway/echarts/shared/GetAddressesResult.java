package org.eastway.echarts.shared;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetAddressesResult implements Result {

	private List<Address> addresses;

	GetAddressesResult() { }

	public GetAddressesResult(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Address> getAddresses() {
		return addresses;
	}
}
