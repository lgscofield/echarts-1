package org.eastway.echarts.domain;

import javax.persistence.Column;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.entity.RooEntity;

@RooJavaBean
@RooToString
@RooEntity(table = "Codes", identifierField = "id", identifierColumn = "code_id")
public class Code {
    @Column(name = "value")
    private String codeValue;

    @Column(name = "descriptor")
    private String codeDescriptor;

    private String columnName;
}
