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
package org.eastway.echarts.tests.client.presenter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.shared.CodeDTO;
import org.eastway.echarts.shared.GetMessages;
import org.eastway.echarts.shared.MessageDTO;
import org.eastway.echarts.shared.PatientDTO;
import org.eastway.echarts.shared.SaveMessage;
import org.eastway.echarts.shared.SaveMessageResult;
import org.easymock.IAnswer;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class MessagePresenterTest extends TestCase {
	private MessagesPresenter messagesPresenter;
	private CachingDispatchAsync dispatch;
	private EventBus eventBus;
	private MessagesPresenter.Display mockMessagesDisplay;
	private GetMessages action;
	private String caseNumber;
	private Date timestamp;
	private PatientDTO patient;
	private MessageDTO message;
	private List<MessageDTO> msgs;
	private CodeDTO mtDto;

	@Before
	@Override
	public void setUp() {
		timestamp = new Date(1270717380123L);
		caseNumber = "0000008";
		action = new GetMessages("12345", caseNumber);
		dispatch = createStrictMock(CachingDispatchAsync.class);
		mockMessagesDisplay = createStrictMock(MessagesPresenter.Display.class);
		eventBus = createStrictMock(EventBus.class);
		messagesPresenter = new MessagesPresenter(mockMessagesDisplay, eventBus, dispatch, action);
		patient = new PatientDTO();
		patient.setCaseNumber(caseNumber);

		message = new MessageDTO();

		message.setCaseNumber(caseNumber);

		mtDto = new CodeDTO();

		mtDto.setDescriptor("Referral Message");
		message.setMessageType(mtDto);
		message.setMessage("This is test 1");
		message.setCreationTimestamp(timestamp);
		message.setId(1);
		message.setLastEdit(timestamp);
		message.setLastEditBy("johndoe");
		message.setParent(null);

		msgs = new ArrayList<MessageDTO>();
		msgs.add(message);
	}

	@SuppressWarnings("unchecked")
	@Test public void testAddMessage() {
		dispatch.execute(isA(SaveMessage.class), isA(EchartsCallback.class));
		expectLastCall().andAnswer(new IAnswer<Object>() {
			@Override
			public Object answer() throws Throwable {
				final Object[] arguments = getCurrentArguments();
				EchartsCallback<SaveMessageResult> callback = (EchartsCallback<SaveMessageResult>) arguments[arguments.length - 1];
				callback.onSuccess(new SaveMessageResult(message));
				return null;
			}
		});
		replay(dispatch);
		messagesPresenter.save(message);
		verify(dispatch);

		assertEquals(messagesPresenter.getMessage(0).getCreationTimestamp(), timestamp);
		assertEquals(messagesPresenter.getMessage(0).getId(), 1);
		assertEquals(messagesPresenter.getMessage(0).getLastEdit(), timestamp);
		assertEquals(messagesPresenter.getMessage(0).getLastEditBy(), "johndoe");
		assertEquals(messagesPresenter.getMessage(0).getMessage(), "This is test 1");
		assertEquals(messagesPresenter.getMessage(0).getMessageType(), mtDto);
		assertEquals(messagesPresenter.getMessage(0).getParent(), null);
		assertEquals(messagesPresenter.getMessage(0).getCaseNumber(), caseNumber);

		ArrayList<String[]> data = messagesPresenter.getData();

		assertEquals(new Date(new Long(data.get(0)[0])), timestamp);
		assertEquals(data.get(0)[1], mtDto.getDescriptor());
		assertEquals(data.get(0)[2], "johndoe");
		assertEquals(data.get(0)[3], "This is test 1");
	}
}
