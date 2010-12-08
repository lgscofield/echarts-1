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

import static org.easymock.EasyMock.*;

import junit.framework.TestCase;

import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.common.ProfileColumnDefinitionsImpl;
import org.eastway.echarts.client.presenter.ProfilePresenter;
import org.eastway.echarts.client.rpc.EchartsRequestFactory;
import org.eastway.echarts.client.rpc.UserProxy;
import org.eastway.echarts.client.view.ProfileView;
import org.junit.Before;
import org.junit.Test;

public class ProfilePresenterTest extends TestCase {
	private ProfilePresenter profilePresenter;
	private ProfileView<UserProxy> profileView;
	private ProfileColumnDefinitionsImpl columnDefinitions;
	private EchartsRequestFactory requestFactory;
	private EventBus eventBus;
	private UserProxy user;

	@SuppressWarnings("unchecked")
	@Before
	@Override
	public void setUp() {
		profileView = createStrictMock(ProfileView.class);
		requestFactory = createStrictMock(EchartsRequestFactory.class);
		eventBus = createStrictMock(EventBus.class);
		profilePresenter = new ProfilePresenter(profileView, columnDefinitions,
				eventBus, requestFactory);
//		user = new UserDTO();
//		user.setCred1("B.S");
//		user.setCred2("M.S");
//		user.setHireDate(new Date());
//		user.setProgram("010");
//		user.setRole(new RoleDTO());
	}

	@Test
	public void testSaveProfile() {

//		requestFactory.executeWithCache(isA(SaveProfile.class),
//				isA(EchartsCallback.class));
//		expectLastCall().andAnswer(new IAnswer<Object>() {
//			@Override
//			public Object answer() throws Throwable {
//				final Object[] arguments = getCurrentArguments();
//				EchartsCallback<SaveProfileResult> callback = (EchartsCallback<SaveProfileResult>) arguments[arguments.length - 1];
//				callback.onSuccess(new SaveProfileResult(user));
//				return null;
//			}
//		});
		replay(requestFactory);
		profilePresenter.save(user);
		verify(requestFactory);
		assertEquals("B.S", profilePresenter.getData().getCred1());
		assertEquals("M.S", profilePresenter.getData().getCred2());
		assertEquals("010", profilePresenter.getData().getProgram());
	}
}
