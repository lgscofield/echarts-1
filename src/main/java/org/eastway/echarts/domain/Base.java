package org.eastway.echarts.domain;

import me.prettyprint.cassandra.serializers.AsciiSerializer;
import me.prettyprint.cassandra.serializers.LongSerializer;
import me.prettyprint.cassandra.serializers.StringSerializer;
import me.prettyprint.cassandra.serializers.UUIDSerializer;

public class Base {
	final static StringSerializer ss = StringSerializer.get();
	final static LongSerializer ls = LongSerializer.get();
	final static UUIDSerializer us = UUIDSerializer.get();
	final static AsciiSerializer as = AsciiSerializer.get();

	final static String PLACE_LOG_RECORD = "PlaceLogRecord";
	final static String PLACE_LOG_RECORD_TIMELINE = "PlaceLogRecordTimeline";
}
