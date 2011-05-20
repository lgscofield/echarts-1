package org.eastway.echarts.domain;

import me.prettyprint.cassandra.service.CassandraHostConfigurator;
import me.prettyprint.hector.api.Cluster;
import me.prettyprint.hector.api.Keyspace;
import me.prettyprint.hector.api.factory.HFactory;

public final class KeyspaceFactory {
	private static final Cluster cluster = HFactory.getOrCreateCluster("EchartsCluster", new CassandraHostConfigurator("localhost:9160"));
	private static Keyspace keyspace = HFactory.createKeyspace("Echarts", cluster);

	public static final Keyspace get() {
		return keyspace;
	}

	private KeyspaceFactory() {
		// nothing
	}
}