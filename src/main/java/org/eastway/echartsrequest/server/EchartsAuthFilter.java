package org.eastway.echartsrequest.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

public class EchartsAuthFilter extends AbstractAuthenticationProcessingFilter {
	private String sessionIdCookieName = "session_id";

	protected EchartsAuthFilter() {
		super("/echarts_security_check");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		String sessionId = getSessionId(request);

		if (!ServiceUtil.isSessionValid(sessionId)) {
			response.sendRedirect("/echarts/logout.aspx?continue=https://" + request.getServerName() + "/echarts/");
			return null;
		}

		EchartsAuthenticationToken token = new EchartsAuthenticationToken(
				sessionId, EchartsAuthenticationStatus.SUCCESS);

		token.setDetails(authenticationDetailsSource.buildDetails(request));

		Authentication authentication = this.getAuthenticationManager()
				.authenticate(token);
		if (authentication.isAuthenticated()) {
			setLastUsername(token.getIdentifier(), request);
		}
		return authentication;
	}

	private void setLastUsername(String username, HttpServletRequest request) {
		HttpSession session = request.getSession(false);

		if (session != null || getAllowSessionCreation()) {
			request.getSession()
					.setAttribute(
							UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY,
							username);
		}
	}

	private String getSessionId(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0)
			return null;
		for (Cookie cookie : cookies)
			if (cookie.getName().equals(sessionIdCookieName))
				return StringUtils.hasText(cookie.getValue()) ? cookie
						.getValue() : null;
		return null;
	}
}
