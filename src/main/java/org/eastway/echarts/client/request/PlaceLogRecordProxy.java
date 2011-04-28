package org.eastway.echarts.client.request;

import com.google.gwt.requestfactory.shared.EntityProxy;
import com.google.gwt.requestfactory.shared.ProxyForName;

@ProxyForName("org.eastway.echarts.domain.PlaceLogRecord")
public interface PlaceLogRecordProxy extends EntityProxy {
	public void setId(Long id);

	public Long getId();

	public void setMessage(String message);

	public String getMessage();

	public void setTimestamp(Long timestamp);

	public Long getTimestamp();

	public Integer getVersion();
}
