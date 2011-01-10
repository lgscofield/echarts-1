package org.eastway.echartsrequest.server;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EchartsAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -4417494640072126783L;
	private Object principal;
	private EchartsAuthenticationStatus status;
	private String identifier;

	public EchartsAuthenticationToken(String identifier,
			EchartsAuthenticationStatus status) {
		super(new ArrayList<GrantedAuthority>(0));
		this.principal = identifier;
		this.status = status;
		this.identifier = identifier;
		setAuthenticated(false);
	}

	public EchartsAuthenticationToken(UserDetails userDetails,
			Collection<GrantedAuthority> authorities) {
		super(authorities);
		this.principal = userDetails;
		this.status = EchartsAuthenticationStatus.SUCCESS;
		setAuthenticated(true);
	}

	/**
	 * 
	 * @return the session id associated with this token
	 */
	public String getIdentifier() {
		return identifier;
	}

    /**
     * Returns 'null' always, as {@link #getIdentifier()} provides the means of authenticating this token
     * @see org.springframework.security.core.Authentication#getCredentials()
     */
	@Override
	public Object getCredentials() {
		return null;
	}

    /**
     * Returns the <tt>principal</tt> value.
     *
     * @see org.springframework.security.core.Authentication#getPrincipal()
     */
	@Override
	public Object getPrincipal() {
		return principal;
	}

	public EchartsAuthenticationStatus getStatus() {
		return status;
	}

}
