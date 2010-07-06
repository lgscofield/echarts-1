package org.eastway.echarts.shared;

import java.util.LinkedHashSet;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetLinksResult implements Result {

	private LinkedHashSet<String[]> links;

	GetLinksResult() { }

	public GetLinksResult(LinkedHashSet<String[]> links) {
		this.links = links;
	}

	public LinkedHashSet<String[]> getLinks() {
		return links;
	}
}
