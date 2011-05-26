package org.eastway.echarts.domain;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;

@Controller
public class PlaceLogRecord extends Base {
	private UUID id;
	private String username;
	private String url;
	private String logLevel;
	private String appVersion;
	private String userAgent;
	private Long timestamp;
	private Integer version;

	static PlaceLogRecordService service;

	public void setId(String id) {
		this.id = UUID.fromString(id);
	}

	public String getId() {
		return id.toString();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public void persist() {
		UUID placeLogRecordId = this.id == null ? UUID.randomUUID() : this.id;
		this.id = placeLogRecordId;
		this.version = this.version != null ? ++this.version : 0;
		Mutator<String> m1 = HFactory.createMutator(KeyspaceFactory.get(), as);
		m1.addInsertion(this.username, PLACE_LOG_RECORD_TIMELINE,
				HFactory.createColumn(this.timestamp, placeLogRecordId, ls, us));
		m1.execute();
		Mutator<UUID> m2 = HFactory.createMutator(KeyspaceFactory.get(), us);
		m2.addInsertion(placeLogRecordId,
						PLACE_LOG_RECORD,
						HFactory.createStringColumn("url", this.url))
				.addInsertion(
						placeLogRecordId,
						PLACE_LOG_RECORD,
						HFactory.createStringColumn("app_version", this.appVersion))
				.addInsertion(
						placeLogRecordId,
						PLACE_LOG_RECORD,
						HFactory.createStringColumn("user_agent",
								this.userAgent))
				.addInsertion(
						placeLogRecordId,
						PLACE_LOG_RECORD,
						HFactory.createStringColumn("username", this.username))
				.addInsertion(
						placeLogRecordId,
						PLACE_LOG_RECORD,
						HFactory.createColumn("log_level", this.logLevel, ss, ss));
		m2.execute();
	}

	@Autowired
	public void init(PlaceLogRecordService service) {
		PlaceLogRecord.service = service;
	}

	public static final PlaceLogRecord findPlaceLogRecord(String id) {
		return service.findPlaceLogRecord(id);
	}

	public static final List<PlaceLogRecord> findPlaceLogRecordsByUsername(String username, Long startKey, Integer count) {
		return service.findPlaceLogRecordsByUsername(username, startKey, count);
	}

}
