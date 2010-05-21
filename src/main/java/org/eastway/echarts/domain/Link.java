package org.eastway.echarts.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Link {
	@Id
	private String name;
	private String url;
	private String header;
	private int sortOrder;

	public Link() { }

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

	public void setSortOrder(int sortOrder) {
		this.sortOrder = sortOrder;
	}

	public int getSortOrder() {
		return sortOrder;
	}
}
