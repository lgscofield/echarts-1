package org.eastway.echarts.activity;

import java.util.List;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.activity.ProfileActivity;
import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.common.ProfileColumnDefinitionsImpl;
import org.eastway.echarts.client.place.ProfilePlace;
import org.eastway.echarts.client.request.CodeProxy;
import org.eastway.echarts.client.request.CodeRequest;
import org.eastway.echarts.client.request.UserProxy;
import org.eastway.echarts.client.request.UserRequest;
import org.eastway.echarts.client.ui.ProfileView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ProfileActivityTest extends ActivityTestBase {

	private static class MyView implements ProfileView<UserProxy> {

		@Override
		public Widget asWidget() {
			return null;
		}

		@Override
		public void setColumnDefinitions(List<ColumnDefinition<UserProxy>> columnDefinitions) {
		}

		@Override
		public void setPresenter(Presenter<UserProxy> presenter) {
		}

		@Override
		public void setRowData(UserProxy t) {
		}

		@Override
		public void setStatus(String string) {
		}

		@Override
		public void clearFirstLogin() {
		}
	};



	private static class MyDisplay implements AcceptsOneWidget {
		@Override
		public void setWidget(IsWidget w) {
		}
	}

	ProfileActivity activity;
	CodeProxy codeProxy;
	UserProxy userProxy;

	@Before
	public void setData() {
		CodeRequest codeContext = requestFactory.codeRequest();
		codeProxy = codeContext.create(CodeProxy.class);
		codeProxy.setCodeDescriptor("Active");
		codeProxy.setColumnName("CaseStatus");
		codeProxy.setCodeValue("A");
		codeProxy.setVersion(0);
		codeContext.persist().using(codeProxy).fire();

		UserRequest userContext = requestFactory.userRequest();
		userProxy = userContext.create(UserProxy.class);
		userProxy.setId(EchartsUser.userName);
		userProxy.setStaffId(EchartsUser.staffId);
		userProxy.setVersion(0);
		userContext.persist().using(userProxy).fire();

		activity = new ProfileActivity(new ProfilePlace(), new ProfileColumnDefinitionsImpl(requestFactory), requestFactory, new MyView());
	}

	@After
	@Override
	public void tearDown() {
		requestFactory.codeRequest().remove().using(codeProxy).fire();
		requestFactory.userRequest().remove().using(userProxy).fire();
	}

	@Test
	public void testStart() {
		activity.start(new MyDisplay(), eventBus);
	}

	@Test
	public void testGetData() {
		activity.start(new MyDisplay(), eventBus);
		UserProxy data = activity.getData();
		assertEquals(userProxy.getId(), data.getId());
		assertEquals(userProxy.getStaffId(), data.getStaffId());
		assertEquals(userProxy.getVersion(), data.getVersion());
	}

	@Test
	public void testEnableEdit() {
		activity.start(new MyDisplay(), eventBus);
		UserProxy data = activity.getData();
		UserProxy mutable = activity.enableEdit(data);
		mutable.setCred1("cred1");
		assertEquals("cred1", mutable.getCred1());
	}

	@Test
	public void testSave() {
		activity.start(new MyDisplay(), eventBus);
		UserProxy data = activity.getData();
		final UserProxy mutable = activity.enableEdit(data);
		mutable.setId(EchartsUser.userName);
		mutable.setCred1("cred1");
		mutable.setCred2("cred2");
		mutable.setProgram("program");

		activity.save(mutable);

		requestFactory.userRequest().findUser(EchartsUser.userName).fire(new Receiver<UserProxy>() {
			@Override
			public void onSuccess(UserProxy response) {
				assertEquals(mutable.getId(), response.getId());
				assertEquals(mutable.getStaffId(), response.getStaffId());
				assertEquals(mutable.getCred1(), response.getCred1());
				assertEquals(mutable.getCred2(), response.getCred2());
				assertEquals(mutable.getProgram(), response.getProgram());
				assertNotSame(mutable.getVersion(), response.getVersion());
			}
		});
	}
}
