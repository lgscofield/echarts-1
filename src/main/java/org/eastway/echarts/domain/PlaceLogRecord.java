package org.eastway.echarts.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
public class PlaceLogRecord {
	@Id
	@TableGenerator(name = "tg", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tg")
	private Long id;
	private String message;
	private Long timestamp;
	@Version
	private Integer version;

	@PersistenceContext
	transient EntityManager entityManager;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new PlaceLogRecord().entityManager;
		if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.persist(this);
	}

	public static final PlaceLogRecord findPlaceLogRecord(Long id) {
		if (id == null)
			return null;
		List<PlaceLogRecord> results = entityManager()
				.createQuery("select o from PlaceLogRecord o where o.id = :id", PlaceLogRecord.class)
				.setParameter("id",id)
				.getResultList();
		if (results.size() > 0) {
			return results.get(0);
		}
		return null;
	}
}
