package org.eastway.echartsrequest.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eastway.echarts.domain.DbServerConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class EchartsLogoutSuccessHandler extends
		AbstractAuthenticationTargetUrlRequestHandler implements
		LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		setDefaultTargetUrl("http://" + DbServerConfig.findDbServerConfigsByConfigName("dbServerUrl").get(0).getConfigValue() + "/echarts/logout.aspx?continue=https://" + request.getServerName() + "/echarts/");
		super.handle(request, response, authentication);
	}

}
