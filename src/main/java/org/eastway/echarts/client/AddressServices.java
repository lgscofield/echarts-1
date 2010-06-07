package org.eastway.echarts.client;

import java.util.List;

import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.SessionExpiredException;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("addressServices")
public interface AddressServices extends RemoteService {
	public List<Address> findByEhr(EHR ehr, String sessionId) throws SessionExpiredException, DbException;
}
