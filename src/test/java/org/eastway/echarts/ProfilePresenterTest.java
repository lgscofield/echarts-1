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

import static org.easymock.EasyMock.*;

import java.util.Date;

import junit.framework.TestCase;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.common.ProfileColumnDefinitionsImpl;
import org.eastway.echarts.client.presenter.ProfilePresenter;
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.client.view.ProfileView;
import org.eastway.echarts.shared.RoleDTO;
import org.eastway.echarts.shared.SaveProfile;
import org.eastway.echarts.shared.SaveProfileResult;
import org.eastway.echarts.shared.User;
import org.eastway.echarts.shared.UserDTO;
import org.easymock.*;
import org.junit.Before;
import org.junit.Test;

public class ProfilePresenterTest extends TestCase {
	private ProfilePresenter profilePresenter;
	private ProfileView<User> profileView;
	private ProfileColumnDefinitionsImpl columnDefinitions;
	private CachingDispatchAsync dispatch;
	private EventBus eventBus;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		profileView = createStrictMock(ProfileView.class);
		dispatch = createStrictMock(CachingDispatchAsync.class);
		eventBus = createStrictMock(EventBus.class);
		profilePresenter = new ProfilePresenter(profileView, columnDefinitions,
				eventBus, dispatch);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSaveProfile() {
		User user = new UserDTO();
		user.setCred1("B.S");
		user.setCred2("M.S");
		user.setHireDate(new Date());
		user.setProgram("010");
		user.setRole(new RoleDTO());
		dispatch.executeWithCache(isA(SaveProfile.class),
				isA(EchartsCallback.class));
		expectLastCall().andAnswer(new IAnswer<Object>() {
			@Override
			public Object answer() throws Throwable {
				final Object[] arguments = getCurrentArguments();
				EchartsCallback<SaveProfileResult> callback = (EchartsCallback<SaveProfileResult>) arguments[arguments.length - 1];
				callback.onSuccess(new SaveProfileResult());
				return null;
			}
		});
		replay(dispatch);
		profilePresenter.save(user);
		verify(dispatch);
		assertEquals("B.S", user.getCred1());
		assertEquals("M.S", user.getCred2());
		assertEquals("010", user.getProgram());
	}
}
