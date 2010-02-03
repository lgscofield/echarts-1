package org.eastway.echarts.shared;

import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class ServiceCodes implements Serializable {
	private Vector<ServiceCode> serviceCodes = new Vector<ServiceCode>();

	public ServiceCodes() {}

	public void add(ServiceCode serviceCode) {
		this.serviceCodes.add(serviceCode);
	}

	public ServiceCode get(int idx) {
		if (idx >= this.serviceCodes.size())
			return null;
		else
			return this.serviceCodes.get(idx);
	}

	public int count() {
		return this.serviceCodes.size();
	}
}
