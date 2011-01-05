package org.eastway.echartsrequest.server;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

public class EchartsAuthenticationProvider implements AuthenticationProvider, InitializingBean {

	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		if (!supports(authentication.getClass()))
			return null;
		if (authentication instanceof EchartsAuthenticationToken) {
			EchartsAuthenticationToken response = (EchartsAuthenticationToken) authentication;
			EchartsAuthenticationStatus status = response.getStatus();

			if (status == EchartsAuthenticationStatus.SUCCESS) {
				UserDetails userDetails = userDetailsService.loadUserByUsername(response.getIdentifier());
				return createSuccessfulAuthentication(userDetails, response);
			} else {
				throw new AuthenticationServiceException("Authentication Failure");
			}
		}
		return null;
	}

	private Authentication createSuccessfulAuthentication(
			UserDetails userDetails, EchartsAuthenticationToken auth) {
		return new EchartsAuthenticationToken(userDetails, userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return EchartsAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsService, "The userDetailsService must be set");
	}

}
