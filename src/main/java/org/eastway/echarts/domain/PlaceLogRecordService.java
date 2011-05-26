package org.eastway.echarts.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import me.prettyprint.hector.api.beans.ColumnSlice;
import me.prettyprint.hector.api.beans.HColumn;
import me.prettyprint.hector.api.factory.HFactory;
import me.prettyprint.hector.api.query.QueryResult;
import me.prettyprint.hector.api.query.SliceQuery;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class PlaceLogRecordService extends Base {

	@PreAuthorize("hasRole('ROLE_ADMINISTRATOR')")
	public List<PlaceLogRecord> findPlaceLogRecordsByUsername(String username, Long startKey, Integer count) {
		if (username == null || username.isEmpty())
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

	public PlaceLogRecord findPlaceLogRecord(String id) {
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

	private String getString(HColumn<String, String> column) {
		return column != null ? column.getValue() : "";
	}
}
