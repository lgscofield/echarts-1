package org.eastway.echarts.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

@Configurable
@Entity
@Table(name = "Codes")
public class Code {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "code_id")
	private Long id;

	@Column(name = "value")
	private String codeValue;

	@Column(name = "descriptor")
	private String codeDescriptor;

	private String columnName;

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

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeDescriptor(String codeDescriptor) {
		this.codeDescriptor = codeDescriptor;
	}

	public String getCodeDescriptor() {
		return codeDescriptor;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public static final EntityManager entityManager() {
		EntityManager em = new Code().entityManager;
		if (em == null)
			throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
		return em;
	}

	public static List<Code> findAllCodes() {
		return entityManager().createQuery("select o from Code o", Code.class)
				.getResultList();
	}

	public static Code findCode(Long id) {
		if (id == null)
			return null;
		return entityManager().find(Code.class, id);
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null) this.entityManager = entityManager();
		this.entityManager.persist(this);
	}

	@Transactional
	public void remove() {
		if (this.entityManager == null) this.entityManager = entityManager();
		if (this.entityManager.contains(this)) {
			this.entityManager.remove(this);
		} else {
			Code attached = Code.findCode(this.id);
			this.entityManager.remove(attached);
		}
	}

	@Transactional
	public Code merge() {
		if (this.entityManager == null) this.entityManager = entityManager();
		Code merged = this.entityManager.merge(this);
		this.entityManager.flush();
		return merged;
    }
}
