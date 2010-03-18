package org.eastway.echarts.test;

import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;

import com.google.gwt.event.shared.HandlerManager;

import junit.framework.TestCase;

import static org.easymock.EasyMock.createStrictMock;

public class MessagesJRETest extends TestCase {
	private MessagesPresenter messagesPresenter;
	private RpcServicesAsync mockRpcService;
	private HandlerManager eventBus;
	private MessagesPresenter.Display mockMessagesDisplay;

	protected void setUp() throws Exception {
		UserImpl.setSessionId("12345");
		mockRpcService = createStrictMock(RpcServicesAsync.class);
		eventBus = new HandlerManager(null);
		mockMessagesDisplay = createStrictMock(MessagesPresenter.Display.class);
		messagesPresenter = new MessagesPresenter(mockMessagesDisplay, eventBus, mockRpcService, "00000001");
	}

	public void testAddMessage() {
		Message m1 = new Message();
		Message m2 = new Message();
		Message m3 = new Message();
		m1.setPatId("00000001");
		m1.setMessageType("Referral Message");
		m1.setMessage("This is test 1");
		m2.setPatId("00000002");
		m2.setMessageType("Triage Message");
		m2.setMessage("This is test 2");
		m3.setPatId("00000003");
		m3.setMessageType("Medication");
		m3.setMessage("This is test 3");
		Messages msgs = new Messages();
		msgs.add(m1);
		msgs.add(m2);
		msgs.add(m3);
		messagesPresenter.setData(msgs);
		assertTrue(messagesPresenter.getMessage(0).getPatId().equals("00000001"));
		assertTrue(messagesPresenter.getMessage(1).getPatId().equals("00000002"));
		assertTrue(messagesPresenter.getMessage(2).getPatId().equals("00000003"));
	}
}
