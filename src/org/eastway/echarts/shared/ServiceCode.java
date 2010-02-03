package org.eastway.echarts.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ServiceCode implements Serializable {
	private Integer service;
	private String description;
	private String templateId;

	public ServiceCode() {}

	public void add(Integer serviceCode, String description, String templateId) {
		this.setService(serviceCode);
		this.setDescription(description);
		this.setTemplateId(templateId);
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setService(Integer service) {
		this.service = service;
	}

	public Integer getService() {
		return service;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
