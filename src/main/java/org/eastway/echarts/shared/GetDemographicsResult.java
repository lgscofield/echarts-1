package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetDemographicsResult implements Result {

	private Demographics demographics;

	GetDemographicsResult() { }

	public GetDemographicsResult(Demographics demographics) {
		this.demographics = demographics;
	}

	public Demographics getDemographics() {
		return demographics;
	}
}
