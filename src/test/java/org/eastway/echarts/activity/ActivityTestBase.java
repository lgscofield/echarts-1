package org.eastway.echarts.activity;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.testing.CountingEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.requestfactory.server.ServiceLayer;
import com.google.web.bindery.requestfactory.server.SimpleRequestProcessor;
import com.google.web.bindery.requestfactory.server.testing.InProcessRequestTransport;
import com.google.web.bindery.requestfactory.vm.RequestFactorySource;

import com.google.gwt.user.client.Window.ClosingHandler;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/test/testContext.xml")
public class ActivityTestBase extends TestCase {

	private static class MyDelegate implements PlaceController.Delegate {
		@Override
		public HandlerRegistration addWindowClosingHandler(
				ClosingHandler handler) {
			return null;
		}

		@Override
		public boolean confirm(String message) {
			return false;
		}
	};

	EchartsRequestFactory requestFactory;
	EventBus eventBus;
	PlaceController placeController;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EchartsUser.staffId = "1234";
		EchartsUser.userName = "test";
		EchartsUser.sessionId = "ABCD12345";
		EchartsUser.dbServerUrl = "top.level.dom";
	}

	@Before
	@Override
	public void setUp() throws Exception {
		placeController = new PlaceController(eventBus, new MyDelegate());
		eventBus = new CountingEventBus();
		ServiceLayer serviceLayer = ServiceLayer.create();
		SimpleRequestProcessor processor = new SimpleRequestProcessor(serviceLayer);
		requestFactory = RequestFactorySource.create(EchartsRequestFactory.class);
		//java.lang.System.setProperty("gwt.rpc.dumpPayload", "true");
		requestFactory.initialize(eventBus, new InProcessRequestTransport(processor));
	}
}
