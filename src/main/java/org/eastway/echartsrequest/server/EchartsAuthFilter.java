package org.eastway.echartsrequest.server;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eastway.echarts.domain.DbServerConfig;

public class EchartsAuthFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		String sessionId = null;

		try {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals("session_id")) {
					sessionId = cookie.getValue();
					break;
				}
			}
		} catch (NullPointerException e) {
		}

		if (!ServiceUtil.isUserLoggedIn(sessionId)) {
			String echartsUrl = "http://" + request.getServerName() + "/echarts/echarts.jsp";
			String loginServerUrl = "http://" + DbServerConfig.findDbServerConfig("dbServerUrl").getConfigValue() + "/echarts/login.aspx?continue=" + echartsUrl;
			response.setHeader("login", loginServerUrl);
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
