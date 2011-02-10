package org.eastway.echarts.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class DbServerConfig {
	@Id
	private Long id;

	@Column(name = "name")
	private String configName;

	@Column(name = "value")
	private String configValue;

	@Column(name = "serverMode")
	private String serverMode;

	@Column(name = "version")
	private Integer version;

	@PersistenceContext
	transient EntityManager entityManager;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	public String getConfigName() {
		return configName;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setServerMode(String serverMode) {
		this.serverMode = serverMode;
	}

	public String getServerMode() {
		return serverMode;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new DbServerConfig().entityManager;
		if (em == null)
			throw new IllegalStateException(
					"Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static DbServerConfig findDbServerConfig(Long id) {
		if (id == null) return null;
		return entityManager().find(DbServerConfig.class, id);
	}

	public static List<DbServerConfig> findDbServerConfigsByConfigName(String configName) {
		if (configName == null || configName.length() == 0)
			throw new IllegalArgumentException("The configName argument is required");
		EntityManager em = DbServerConfig.entityManager();
		return em.createQuery("SELECT DbServerConfig FROM DbServerConfig AS dbserverconfig WHERE dbserverconfig.configName = :configName", DbServerConfig.class)
				.setParameter("configName", configName)
				.getResultList();
	}
}
