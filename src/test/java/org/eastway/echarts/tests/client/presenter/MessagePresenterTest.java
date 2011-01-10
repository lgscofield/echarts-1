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

import com.google.gwt.requestfactory.shared.InstanceRequest;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.Request;

import org.eastway.echarts.client.activity.MessageActivity;
import org.eastway.echarts.client.request.CodeProxy;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.client.request.MessageProxy;
import org.eastway.echarts.client.request.MessageRequest;
import org.easymock.IAnswer;
import org.junit.Before;
import org.junit.Test;

import static org.easymock.EasyMock.*;

public class MessagePresenterTest extends TestCase {
	private MessageActivity messagesPresenter;
	private EchartsRequestFactory requestFactory;
	private String caseNumber = "0000008";
	private String descriptor = "Referral Message";
	private Date timestamp = new Date(1270717380123L);
	private String message = "message presenter test message";
	private Date lastEdit = timestamp;
	private String lastEditBy = "johndoe";
	private MessageRequest messageRequest;
	private MessageProxy messageProxy;
	private List<MessageProxy> messages = new ArrayList<MessageProxy>();
	private CodeProxy codeProxy;
	private InstanceRequest<MessageProxy, Void> instanceRequest;
	private Request<Void> createReq;

	@SuppressWarnings("unchecked")
	@Before
	@Override
	public void setUp() {
		requestFactory = createStrictMock(EchartsRequestFactory.class);
		codeProxy = createStrictMock(CodeProxy.class);
		messageProxy = createStrictMock(MessageProxy.class);
		messageRequest = createStrictMock(MessageRequest.class);
		createReq = createStrictMock(Request.class);
		instanceRequest = createStrictMock(InstanceRequest.class);

		messagesPresenter = createStrictMock(MessageActivity.class);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddMessage() {
		messageProxy.setCaseNumber(caseNumber);
		messageProxy.setCreationTimestamp(timestamp);
		messageProxy.setLastEdit(lastEdit);
		messageProxy.setLastEditBy(lastEditBy);
		messageProxy.setMessage(message);
		messageProxy.setMessageType(codeProxy);

		messages.add(messageProxy);
		messagesPresenter.setMessages(messages);
		expect(messagesPresenter.getMessage(0).getCreationTimestamp()).andReturn(timestamp).anyTimes();
		expect(messagesPresenter.getMessage(0).getLastEdit()).andReturn(timestamp).anyTimes();
		expect(messagesPresenter.getMessage(0).getLastEditBy()).andReturn(lastEditBy).anyTimes();
		expect(messagesPresenter.getMessage(0).getCaseNumber()).andReturn(caseNumber).anyTimes();
		expect(messagesPresenter.getMessage(0).getMessage()).andReturn(message).anyTimes();
		expect(messagesPresenter.getMessage(0).getMessageType()).andReturn(codeProxy).anyTimes();
		expect(codeProxy.getCodeDescriptor()).andReturn(descriptor).anyTimes();
		expect(messagesPresenter.getMessage(0).getParent()).andReturn(messageProxy).anyTimes();
		expect(messagesPresenter.getMessage(0).getCaseNumber()).andReturn(caseNumber).anyTimes();

		expect(requestFactory.messageRequest()).andReturn(messageRequest);
		expect(messageRequest.create(MessageProxy.class)).andReturn(messageProxy);
		expect(messageRequest.persist()).andReturn(instanceRequest);
		expect(instanceRequest.using(messageProxy)).andReturn(createReq);

		createReq.fire(isA(Receiver.class));

		expectLastCall().andAnswer(new IAnswer<Object>() {
			@Override
			public Object answer() throws Throwable {
				final Object[] arguments = getCurrentArguments();
				Receiver<Void> callback = (Receiver<Void>) arguments[arguments.length - 1];
				callback.onSuccess(null);
				return null;
			}
		});
		replay(codeProxy, messageProxy, instanceRequest, messageRequest, requestFactory);
		messagesPresenter.save(descriptor, message);
		verify(codeProxy, messageProxy, instanceRequest, messageRequest, requestFactory);

		assertEquals(messagesPresenter.getMessage(0).getCreationTimestamp(), timestamp);
		assertEquals(messagesPresenter.getMessage(0).getLastEdit(), timestamp);
		assertEquals(messagesPresenter.getMessage(0).getLastEditBy(), lastEditBy);
		assertEquals(messagesPresenter.getMessage(0).getMessage(), message);
		assertEquals(messagesPresenter.getMessage(0).getMessageType().getCodeDescriptor(), descriptor);
		assertEquals(messagesPresenter.getMessage(0).getParent(), messageProxy);
		assertEquals(messagesPresenter.getMessage(0).getCaseNumber(), caseNumber);

		
	}

	@Test
	public void testGetData() {
		messages.add(messageProxy);
		messagesPresenter.setMessages(messages);

		expect(messagesPresenter.getMessage(0).getCreationTimestamp()).andReturn(timestamp);
		expect(messagesPresenter.getMessage(0).getMessageType()).andReturn(codeProxy).anyTimes();
		expect(codeProxy.getCodeDescriptor()).andReturn(descriptor).anyTimes();
		expect(messagesPresenter.getMessage(0).getLastEditBy()).andReturn(lastEditBy).anyTimes();
		expect(messagesPresenter.getMessage(0).getMessage()).andReturn(message).anyTimes();

		replay(codeProxy, messageProxy);
		messagesPresenter.setData(messages);
		verify(codeProxy, messageProxy);

		List<String[]> data = messagesPresenter.getData();

		assertEquals(new Date(new Long(data.get(0)[0])), timestamp);
		assertEquals(data.get(0)[1], descriptor);
		assertEquals(data.get(0)[2], lastEditBy);
		assertEquals(data.get(0)[3], message);
	}
}
