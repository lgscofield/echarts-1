package org.eastway.echarts.client.request;

import java.util.List;

import org.eastway.echarts.domain.AppointmentReport;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(AppointmentReport.class)
public interface AppointmentReportRequest extends RequestContext {
	Request<Long> findAppointmentReportsWithApptDateCount();
	Request<List<AppointmentReportProxy>> findAppointmentReportsWithApptDate(Integer startPosition, Integer maxResult, Boolean isAscending, String columnName);
}
