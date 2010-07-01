package org.eastway.echarts.shared;

import java.util.LinkedHashMap;
import java.util.Set;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetAssignmentsResult implements Result {
	private LinkedHashMap<String, Long> list = new LinkedHashMap<String, Long>();

	GetAssignmentsResult() { }

	public GetAssignmentsResult(LinkedHashMap<String, Long> list) {
		this.list = list;
	}

	public Set<String> keySet() {
		return list.keySet();
	}

	public LinkedHashMap<String, Long> getList() {
		return list;
	}
}
