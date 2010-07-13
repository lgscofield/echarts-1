package org.eastway.echarts.server;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EchartsEntityManagerFactory {

	private static EntityManagerFactory emf = null;

	private EchartsEntityManagerFactory() {
		EchartsEntityManagerFactory.emf = Persistence.createEntityManagerFactory("EchartsPersistence");
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		if (emf == null) {
			new EchartsEntityManagerFactory();
		}
		return emf;
	}
}
