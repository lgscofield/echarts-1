/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.springframework.beans.factory.annotation.Configurable;

@Configurable
@Entity
public class Link {
	@Id
	private String name;
	private String url;
	private String header;
	private Integer sortOrder;
	private String place;
	@Version
	@Column(name = "version")
	private Integer version;

	@PersistenceContext
	transient EntityManager entityManager;

	public void setId(String id) {
		this.name = id;
	}

	public String getId() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getHeader() {
		return header;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

    public static final EntityManager entityManager() {
        EntityManager em = new Link().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

    public static Link findLink(String id) {
    	if (id == null)
    		return null;
    	return entityManager().find(Link.class, id);
    }

	public static List<Link> findAllLinks() {
		return entityManager().createQuery("SELECT link FROM Link link ORDER BY link.header, link.sortOrder", Link.class)
			.getResultList();
	}

	public static List<Link> findLinksByPlace(String place) {
		if (place == null)
			return null;
		return entityManager().createQuery("SELECT o FROM Link o WHERE o.place = :place ORDER BY o.header, o.sortOrder", Link.class)
			.setParameter("place", place)
			.getResultList();
	}
}
