package org.eastway.echarts.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.entity.RooIdentifier;

@RooJavaBean
@RooToString
@RooEntity(identifierColumn = "name", identifierType = java.lang.String.class)
public class DbServerConfig {
	@Column(name = "value")
    private String configValue;
}
