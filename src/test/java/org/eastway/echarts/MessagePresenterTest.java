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
package org.eastway.echarts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.shared.CodeDTO;
import org.eastway.echarts.shared.GetMessages;
import org.eastway.echarts.shared.MessageDTO;
import org.eastway.echarts.shared.PatientDTO;
import org.junit.Test;


import static org.easymock.EasyMock.createStrictMock;
import static org.junit.Assert.*;

public class MessagePresenterTest {
	private MessagesPresenter messagesPresenter;
	private CachingDispatchAsync mockRpcService;
	private EventBus eventBus;
	private MessagesPresenter.Display mockMessagesDisplay;
	private GetMessages action = null;
	private String caseNumber = "0000008";

	@Test public void testAddMessage() {
		action = new GetMessages("12345", caseNumber);
		Date timestamp = new Date(1270717380123L);
		EchartsUser.sessionId = "12345";
		mockRpcService = createStrictMock(CachingDispatchAsync.class);
		eventBus = new DefaultEventBus();
		mockMessagesDisplay = createStrictMock(MessagesPresenter.Display.class);
		PatientDTO patient = new PatientDTO();
		patient.setCaseNumber("00000001");
		messagesPresenter = new MessagesPresenter(mockMessagesDisplay, eventBus, mockRpcService, action);
		MessageDTO m1 = new MessageDTO();
		MessageDTO m2 = new MessageDTO();
		MessageDTO m3 = new MessageDTO();

		m1.setCaseNumber(caseNumber);

		CodeDTO mtDto = new CodeDTO();
		mtDto.setDescriptor("Referral Message");
		m1.setMessageType(mtDto);
		m1.setMessage("This is test 1");
		m1.setCreationTimestamp(timestamp);
		m1.setId(1);
		m1.setLastEdit(timestamp);
		m1.setLastEditBy("johndoe");
		m1.setParent(null);

		m2.setCaseNumber(caseNumber);
		m2.setMessageType(mtDto);
		m2.setMessage("This is test 2");
		m2.setCreationTimestamp(timestamp);
		m2.setId(2);
		m2.setLastEdit(timestamp);
		m2.setLastEditBy("johndoe");
		m2.setParent(m1);

		m3.setCaseNumber(caseNumber);
		m3.setMessageType(mtDto);
		m3.setMessage("This is test 3");
		m3.setCreationTimestamp(timestamp);
		m3.setId(3);
		m3.setLastEdit(timestamp);
		m3.setLastEditBy("johndoe");
		m3.setParent(m2);

		List<MessageDTO> msgs = new ArrayList<MessageDTO>();
		msgs.add(m1);
		msgs.add(m2);
		msgs.add(m3);
		messagesPresenter.setData(msgs);

		assertTrue(messagesPresenter.getMessage(0).getCreationTimestamp() == timestamp);
		assertTrue(messagesPresenter.getMessage(0).getId() == 1);
		assertTrue(messagesPresenter.getMessage(0).getLastEdit().equals(timestamp));
		assertTrue(messagesPresenter.getMessage(0).getLastEditBy().equals("johndoe"));
		assertTrue(messagesPresenter.getMessage(0).getMessage().equals("This is test 1"));
		assertTrue(messagesPresenter.getMessage(0).getMessageType().equals(mtDto));
		assertTrue(messagesPresenter.getMessage(0).getParent() == null);
		assertTrue(messagesPresenter.getMessage(0).getCaseNumber().equals(caseNumber));

		assertTrue(messagesPresenter.getMessage(1).getCreationTimestamp() == timestamp);
		assertTrue(messagesPresenter.getMessage(1).getId() == 2);
		assertTrue(messagesPresenter.getMessage(1).getLastEdit().equals(timestamp));
		assertTrue(messagesPresenter.getMessage(1).getLastEditBy().equals("johndoe"));
		assertTrue(messagesPresenter.getMessage(1).getMessage().equals("This is test 2"));
		assertTrue(messagesPresenter.getMessage(1).getMessageType().equals(mtDto));
		assertTrue(messagesPresenter.getMessage(1).getParent().equals(m1));
		assertTrue(messagesPresenter.getMessage(1).getCaseNumber().equals(caseNumber));

		assertTrue(messagesPresenter.getMessage(2).getCreationTimestamp() == timestamp);
		assertTrue(messagesPresenter.getMessage(2).getId() == 3);
		assertTrue(messagesPresenter.getMessage(2).getLastEdit().equals(timestamp));
		assertTrue(messagesPresenter.getMessage(2).getLastEditBy().equals("johndoe"));
		assertTrue(messagesPresenter.getMessage(2).getMessage().equals("This is test 3"));
		assertTrue(messagesPresenter.getMessage(2).getMessageType().equals(mtDto));
		assertTrue(messagesPresenter.getMessage(2).getParent().equals(m2));
		assertTrue(messagesPresenter.getMessage(2).getCaseNumber().equals(caseNumber));

		ArrayList<String[]> data = messagesPresenter.getData();

		assertTrue(new Date(new Long(data.get(0)[0])).equals(timestamp));
		assertTrue(data.get(0)[1].equals(mtDto.getDescriptor()));
		assertTrue(data.get(0)[2].equals("johndoe"));
		assertTrue(data.get(0)[3].equals("This is test 1"));
	}
}
