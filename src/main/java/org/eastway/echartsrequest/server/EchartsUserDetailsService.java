package org.eastway.echartsrequest.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eastway.echarts.domain.SessionIdLog;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

public class EchartsUserDetailsService implements UserDetailsService {

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * @param token
	 *            this is the session id
	 */
	@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException, DataAccessException {
		Assert.notNull(token);
		UserDetails ud = createUserDetails(token);
		return ud;
	}

	private UserDetails createUserDetails(String token) {
		List<SessionIdLog> session = SessionIdLog.findUserBySessionId(token);
		if (session.size() == 0) {
			logger.debug("Query returned no results for session id '" + token + "'");
			throw new UsernameNotFoundException(
                    messages.getMessage("EchartsUserDetailsService.notFound", new Object[]{token}, "Session id {0} not found"), token);
		}
		org.eastway.echarts.domain.User user = session.get(0).getUser();

		if (user.getRole() == null) {
			logger.debug("User '" + user.getUsername() + "' has no authorities and will be treated as 'not found'");

            throw new UsernameNotFoundException(
                    messages.getMessage("EchartsUserDetailsService.noAuthority",
                            new Object[] {user.getUsername()}, "User {0} has no GrantedAuthority"), user.getUsername());
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("ROLE_" + user.getRole().getRoleName().toUpperCase()));
		return new User(user.getUsername(), "N/A", true, true, true, true, authorities);
	}
}
