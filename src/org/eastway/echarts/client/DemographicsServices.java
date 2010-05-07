package org.eastway.echarts.client;

import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.DemographicsDTO;
import org.eastway.echarts.shared.SessionExpiredException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("demographicsService")
public interface DemographicsServices extends RemoteService {
	public DemographicsDTO getDemographics(long id, String sessionId) throws SessionExpiredException, DbException;
}
