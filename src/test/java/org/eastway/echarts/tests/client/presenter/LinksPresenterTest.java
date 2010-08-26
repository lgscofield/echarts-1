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

import java.util.LinkedHashSet;

import junit.framework.TestCase;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.presenter.LinkPresenter;
import org.eastway.echarts.client.rpc.CachingDispatchAsync;
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.shared.GetLinks;
import org.eastway.echarts.shared.GetLinksResult;
import org.easymock.IAnswer;
import org.junit.Before;
import org.junit.Test;

public class LinksPresenterTest extends TestCase {
	private LinkPresenter linkPresenter;
	private CachingDispatchAsync dispatch;
	private LinkPresenter.Display view;
	private EventBus eventBus;
	private LinkedHashSet<String[]> data;
	private String patientid;
	private GetLinks action = null;

	@Before
	@Override
	public void setUp() {
		data = new LinkedHashSet<String[]>();
		patientid = "000000008";
		view = createStrictMock(LinkPresenter.Display.class);
		dispatch = createStrictMock(CachingDispatchAsync.class);
		eventBus = createStrictMock(EventBus.class);
		action = new GetLinks("12345", patientid);
		linkPresenter = new LinkPresenter(view, eventBus, dispatch, action);
		data.add(new String[] { "title1","content1" } );
		data.add(new String[] { "title2","content2" } );
		data.add(new String[] { "title3","content3" } );
		EchartsUser.staffId = "12345";
	}

	@SuppressWarnings("unchecked")
	@Test public void testSetData() {
		dispatch.execute(isA(GetLinks.class), isA(EchartsCallback.class));
		expectLastCall().andAnswer(new IAnswer<Object>() {
			@Override
			public Object answer() throws Throwable {
				final Object[] arguments = getCurrentArguments();
				EchartsCallback<GetLinksResult> callback = (EchartsCallback<GetLinksResult>) arguments[arguments.length - 1];
				callback.onSuccess(new GetLinksResult(data));
				return null;
			}
		});
		replay(dispatch);
		linkPresenter.fetchData();
		verify(dispatch);

		int i = 1;
		LinkedHashSet<String[]> d = linkPresenter.getData();
		for (String [] s : d) {
			assertEquals(s[0], "title" + i);
			assertEquals(s[1], "content" + i + "?staffid=" + EchartsUser.staffId + "&PATID=" + patientid);
			i++;
		}
	}
}
