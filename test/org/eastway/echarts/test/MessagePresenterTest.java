package org.eastway.echarts.test;

import java.util.ArrayList;

import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;
import org.junit.Test;

import com.google.gwt.event.shared.HandlerManager;

import static org.easymock.EasyMock.createStrictMock;
import static org.junit.Assert.*;

public class MessagePresenterTest {
	private MessagesPresenter messagesPresenter;
	private RpcServicesAsync mockRpcService;
	private HandlerManager eventBus;
	private MessagesPresenter.Display mockMessagesDisplay;

	@Test public void testAddMessage() {
		long timestamp = 1270717380123L;
		UserImpl.setSessionId("12345");
		mockRpcService = createStrictMock(RpcServicesAsync.class);
		eventBus = new HandlerManager(null);
		mockMessagesDisplay = createStrictMock(MessagesPresenter.Display.class);
		messagesPresenter = new MessagesPresenter(mockMessagesDisplay, eventBus, mockRpcService, "00000001");
		Message m1 = new Message();
		Message m2 = new Message();
		Message m3 = new Message();

		m1.setPatId("00000001");
		m1.setMessageType("Referral Message");
		m1.setMessage("This is test 1");
		m1.setCreationDate(timestamp);
		m1.setId(1);
		m1.setLastModified("2010-03-24");
		m1.setLastModifiedBy("johndoe");
		m1.setParentId(2);

		m2.setPatId("00000002");
		m2.setMessageType("Referral Message");
		m2.setMessage("This is test 2");
		m2.setCreationDate(timestamp);
		m2.setId(2);
		m2.setLastModified("2010-03-24");
		m2.setLastModifiedBy("johndoe");
		m2.setParentId(3);

		m3.setPatId("00000003");
		m3.setMessageType("Referral Message");
		m3.setMessage("This is test 3");
		m3.setCreationDate(timestamp);
		m3.setId(3);
		m3.setLastModified("2010-03-24");
		m3.setLastModifiedBy("johndoe");
		m3.setParentId(1);

		Messages msgs = new Messages();
		msgs.add(m1);
		msgs.add(m2);
		msgs.add(m3);
		messagesPresenter.setData(msgs);

		assertTrue(messagesPresenter.getMessage(0).getCreationDate() == timestamp);
		assertTrue(messagesPresenter.getMessage(0).getId().equals(1));
		assertTrue(messagesPresenter.getMessage(0).getLastModified().equals("2010-03-24"));
		assertTrue(messagesPresenter.getMessage(0).getLastModifiedBy().equals("johndoe"));
		assertTrue(messagesPresenter.getMessage(0).getMessage().equals("This is test 1"));
		assertTrue(messagesPresenter.getMessage(0).getMessageType().equals("Referral Message"));
		assertTrue(messagesPresenter.getMessage(0).getParentId().equals(2));
		assertTrue(messagesPresenter.getMessage(0).getPatId().equals("00000001"));

		assertTrue(messagesPresenter.getMessage(1).getCreationDate() == timestamp);
		assertTrue(messagesPresenter.getMessage(1).getId().equals(2));
		assertTrue(messagesPresenter.getMessage(1).getLastModified().equals("2010-03-24"));
		assertTrue(messagesPresenter.getMessage(1).getLastModifiedBy().equals("johndoe"));
		assertTrue(messagesPresenter.getMessage(1).getMessage().equals("This is test 2"));
		assertTrue(messagesPresenter.getMessage(1).getMessageType().equals("Referral Message"));
		assertTrue(messagesPresenter.getMessage(1).getParentId().equals(3));
		assertTrue(messagesPresenter.getMessage(1).getPatId().equals("00000002"));

		assertTrue(messagesPresenter.getMessage(2).getCreationDate() == timestamp);
		assertTrue(messagesPresenter.getMessage(2).getId().equals(3));
		assertTrue(messagesPresenter.getMessage(2).getLastModified().equals("2010-03-24"));
		assertTrue(messagesPresenter.getMessage(2).getLastModifiedBy().equals("johndoe"));
		assertTrue(messagesPresenter.getMessage(2).getMessage().equals("This is test 3"));
		assertTrue(messagesPresenter.getMessage(2).getMessageType().equals("Referral Message"));
		assertTrue(messagesPresenter.getMessage(2).getParentId().equals(1));
		assertTrue(messagesPresenter.getMessage(2).getPatId().equals("00000003"));

		ArrayList<String[]> data = messagesPresenter.getData();

		assertTrue(new Long(data.get(0)[0]).equals(timestamp));
		assertTrue(data.get(0)[1].equals("Referral Message"));
		assertTrue(data.get(0)[2].equals("johndoe"));
		assertTrue(data.get(0)[3].equals("This is test 1"));
	}
}
