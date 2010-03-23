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
		m1.setCreationDate("2010-03-23");
		m1.setId(1);
		m1.setLastModified("2010-03-24");
		m1.setLastModifiedBy("johndoe");
		m1.setParentId(2);

		m2.setPatId("00000002");
		m2.setMessageType("Referral Message");
		m2.setMessage("This is test 2");
		m2.setCreationDate("2010-03-23");
		m2.setId(2);
		m2.setLastModified("2010-03-24");
		m2.setLastModifiedBy("johndoe");
		m2.setParentId(3);

		m3.setPatId("00000003");
		m3.setMessageType("Referral Message");
		m3.setMessage("This is test 3");
		m3.setCreationDate("2010-03-23");
		m3.setId(3);
		m3.setLastModified("2010-03-24");
		m3.setLastModifiedBy("johndoe");
		m3.setParentId(1);

		Messages msgs = new Messages();
		msgs.add(m1);
		msgs.add(m2);
		msgs.add(m3);
		messagesPresenter.setData(msgs);

		assertTrue(messagesPresenter.getMessage(0).getCreationDate().equals("2010-03-23"));
		assertTrue(messagesPresenter.getMessage(0).getId().equals(1));
		assertTrue(messagesPresenter.getMessage(0).getLastModified().equals("2010-03-24"));
		assertTrue(messagesPresenter.getMessage(0).getLastModifiedBy().equals("johndoe"));
		assertTrue(messagesPresenter.getMessage(0).getMessageType().equals("Referral Message"));
		assertTrue(messagesPresenter.getMessage(0).getParentId().equals(2));
		assertTrue(messagesPresenter.getMessage(0).getPatId().equals("00000001"));

		assertTrue(messagesPresenter.getMessage(1).getCreationDate().equals("2010-03-23"));
		assertTrue(messagesPresenter.getMessage(1).getId().equals(2));
		assertTrue(messagesPresenter.getMessage(1).getLastModified().equals("2010-03-24"));
		assertTrue(messagesPresenter.getMessage(1).getLastModifiedBy().equals("johndoe"));
		assertTrue(messagesPresenter.getMessage(1).getMessageType().equals("Referral Message"));
		assertTrue(messagesPresenter.getMessage(1).getParentId().equals(3));
		assertTrue(messagesPresenter.getMessage(1).getPatId().equals("00000002"));

		assertTrue(messagesPresenter.getMessage(2).getCreationDate().equals("2010-03-23"));
		assertTrue(messagesPresenter.getMessage(2).getId().equals(3));
		assertTrue(messagesPresenter.getMessage(2).getLastModified().equals("2010-03-24"));
		assertTrue(messagesPresenter.getMessage(2).getLastModifiedBy().equals("johndoe"));
		assertTrue(messagesPresenter.getMessage(2).getMessageType().equals("Referral Message"));
		assertTrue(messagesPresenter.getMessage(2).getParentId().equals(1));
		assertTrue(messagesPresenter.getMessage(2).getPatId().equals("00000003"));

		ArrayList<String> data = messagesPresenter.getData();

		assertTrue(data.get(0).equals(messagesPresenter.formatMessage(m1)));
		assertTrue(data.get(1).equals(messagesPresenter.formatMessage(m2)));
		assertTrue(data.get(2).equals(messagesPresenter.formatMessage(m3)));
	}
}
