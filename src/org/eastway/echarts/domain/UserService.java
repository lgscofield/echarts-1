package org.eastway.echarts.domain;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class UserService {
	@PersistenceContext(unitName="UserService")
	protected EntityManager em;

	public UserService(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public User create() {
		User user = new User();
		return user;
	}

	public User find(long userId) {
		return getEntityManager().find(User.class, userId);
	}

	public User find(String username) {
		TypedQuery<User> query = getEntityManager().createQuery(
				"SELECT u FROM User u WHERE u.username = '" + username + "'", User.class);
		return query.getSingleResult();
	}
}
