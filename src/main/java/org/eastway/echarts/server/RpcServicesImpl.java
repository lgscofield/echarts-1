/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.server;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eastway.echarts.client.RpcServices;
import org.eastway.echarts.domain.Alert;
import org.eastway.echarts.domain.AlertService;
import org.eastway.echarts.domain.CodeImpl;
import org.eastway.echarts.domain.CodeService;
import org.eastway.echarts.domain.Link;
import org.eastway.echarts.domain.Message;
import org.eastway.echarts.domain.MessageService;
import org.eastway.echarts.domain.SessionIdLog;
import org.eastway.echarts.domain.User;
import org.eastway.echarts.domain.UserService;
import org.eastway.echarts.shared.Code;
import org.eastway.echarts.shared.CodeDTO;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.MessageDTO;
import org.eastway.echarts.shared.SessionExpiredException;
import org.eastway.echarts.shared.UserDTO;
import org.springframework.dao.EmptyResultDataAccessException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RpcServicesImpl extends RemoteServiceServlet implements
		RpcServices, DbConstants {

	@Override
	public Vector<String> getAlerts(String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		AlertService service = new AlertService(em);
		List<Alert> alerts = service.findAll();
		Vector<String> alertsDto = new Vector<String>();
		for (Alert a : alerts)
			alertsDto.add(a.getPatientId() + " " + a.getItemName() + " " + a.getDate().toString());
		em.close();
		emf.close();
		return alertsDto;
	}

	@Override
	public List<MessageDTO> getMessages(long ehrId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		MessageService service = new MessageService(em);
		List<Message> messages = service.findList(ehrId);
		List<MessageDTO> messagesDto = new ArrayList<MessageDTO>();
		for (Message m : messages)
			messagesDto.add(m.toDto());
		em.close();
		emf.close();
		return messagesDto;
	}

	@Override
	public MessageDTO addMessage(MessageDTO msg, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		MessageService ms = new MessageService(em);
		CodeService cs = new CodeService(em);
		Code mType = cs.find(msg.getMessageType().getCodeId());
		Message parent = null;
		if (msg.getParent() != null)
			parent = ms.find(msg.getParent().getId());
		em.getTransaction().begin();
		Message newMessage = ms.create(msg.getCreationTimestamp(), msg.getEhrId(), msg.getLastEdit(),
				msg.getLastEditBy(), msg.getMessage(), mType, parent);
		em.getTransaction().commit();
		em.close();
		emf.close();
		return newMessage.toDto();
	}

	@Override
	public List<CodeDTO> getMessageTypes(String sessionId)
			throws SessionExpiredException, DbException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		CodeService cs = new CodeService(em);
		List<CodeImpl> mtList = cs.findColumnName("MessageType");
		List<CodeDTO> mtsl = new ArrayList<CodeDTO>();
		for (Code mt : mtList)
			mtsl.add(mt.toDto());
		em.close();
		emf.close();
		return mtsl;
	}


	protected void checkSessionExpire(String sessionId) throws SessionExpiredException, DbException {
		if (sessionId == null)
			throw new IllegalArgumentException("Please login");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();

		TypedQuery<SessionIdLog> query = em.createQuery(
				"SELECT s FROM SessionIdLog s WHERE s.sessionId = '" + sessionId + "'",
					SessionIdLog.class);
		SessionIdLog sil = null;
		try {
			sil = query.getSingleResult();
		} catch (EmptyResultDataAccessException e) {
			throw new SessionExpiredException("Please login");
		} catch (NoResultException e) {
			throw new SessionExpiredException("Please login");
		}
		Date now = new Date();
		Date expire = new Date(sil.getSessionIdExpire());

		if (now.after(expire))
			throw new SessionExpiredException();
		em.close();
		emf.close();
	}

	@Override
	public UserDTO getUser(String username, String sessionId) throws DbException, SessionExpiredException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		UserService userService = new UserService(em);
		User user = userService.find(username);
		em.close();
		emf.close();
		return user.toDto();
	}

	@Override
	public LinkedHashSet<String[]> getFormsList(String sessionId, String patientId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();

		TypedQuery<Link> query = em.createQuery(
				"SELECT link FROM Link link", Link.class);
		List<Link> linkList = query.getResultList();
		LinkedHashSet<String[]> linkDto = new LinkedHashSet<String[]>();
		for (Link link : linkList) {
			String[] s = new String[3];
			s[0] = link.getName();
			s[1] = link.getUrl();
			s[2] = link.getHeader();
			linkDto.add(s);
		}
		em.close();
		emf.close();
		return linkDto;
	}
}
