package org.eastway.echarts.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.mutation.Mutator;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SliceQuery;

public class PlaceLogRecord extends Base {
	private UUID id;
	private String username;
	private String url;
	private String logLevel;
	private String appVersion;
	private String userAgent;
	private Long timestamp;
	private Integer version;

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

	public static final PlaceLogRecord findPlaceLogRecord(String id) {
		if (id == null)
			return null;
		SliceQuery<UUID, String, String> q = HFactory.createSliceQuery(
				KeyspaceFactory.get(), us, ss, ss);
		q.setColumnFamily(PLACE_LOG_RECORD).setKey(UUID.fromString(id))
			.setColumnNames("url", "app_version", "user_agent", "log_level", "username");
		QueryResult<ColumnSlice<String, String>> r = q.execute();
		PlaceLogRecord record = new PlaceLogRecord();
		record.setId(id);
		record.setVersion(0);
		record.setAppVersion(getString(r.get().getColumnByName("app_version")));
		record.setLogLevel(getString(r.get().getColumnByName("log_level")));
		record.setUrl(getString(r.get().getColumnByName("url")));
		record.setUserAgent(getString(r.get().getColumnByName("user_agent")));
		record.setUsername(getString(r.get().getColumnByName("username")));
		return record;
	}

	public static final List<PlaceLogRecord> findPlaceLogRecordsByUsername(String username, Long startKey, Integer count) {
		if (username == null)
			return null;
		SliceQuery<String, Long, UUID> q1 = HFactory.createSliceQuery(KeyspaceFactory.get(), ss, ls, us);
		q1.setColumnFamily(PLACE_LOG_RECORD_TIMELINE).setKey(username).setRange(startKey, null, true, count);
		QueryResult<ColumnSlice<Long, UUID>> r1 = q1.execute();
		List<PlaceLogRecord> records = new ArrayList<PlaceLogRecord>();

		for (HColumn<Long, UUID> column : r1.get().getColumns()) {
			PlaceLogRecord record = new PlaceLogRecord();
			record = findPlaceLogRecord(column.getValue().toString());
			record.setTimestamp(column.getName());
			records.add(record);
		}

		return records;
	}

	private static String getString(HColumn<String, String> column) {
		return column != null ? column.getValue() : "";
	}
}
