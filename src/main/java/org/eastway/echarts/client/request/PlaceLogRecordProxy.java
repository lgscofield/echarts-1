package org.eastway.echarts.client.request;

import org.eastway.echarts.domain.PlaceLogRecord;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.EntityProxyId;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(PlaceLogRecord.class)
public interface PlaceLogRecordProxy extends EntityProxy {
	public void setUsername(String username);

	public String getUsername();

	public void setUrl(String url);

	public String getUrl();

	public void setLogLevel(String logLevel);

	public String getLogLevel();

	public void setAppVersion(String appVersion);

	public String getAppVersion();

	public void setUserAgent(String userAgent);

	public String getUserAgent();

	public void setTimestamp(Long timestamp);

	public Long getTimestamp();

	public Integer getVersion();

	public EntityProxyId<PlaceLogRecordProxy> stableId();
}
