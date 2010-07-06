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

import static org.easymock.EasyMock.createStrictMock;
import static org.junit.Assert.*;

import java.util.LinkedHashSet;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.CachingDispatchAsync;
import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.presenter.LinkPresenter;
import org.eastway.echarts.shared.GetLinks;
import org.junit.Test;

public class LinksPresenterTest {
	private LinkPresenter fp;
	private CachingDispatchAsync mockRpc;
	private LinkPresenter.Display mockView;
	private EventBus eventBus;
	private LinkedHashSet<String[]> data = new LinkedHashSet<String[]>();
	private String patientid = "000000008";
	private GetLinks action = null;

	public LinksPresenterTest() {
		mockView = createStrictMock(LinkPresenter.Display.class);
		mockRpc = createStrictMock(CachingDispatchAsync.class);
		eventBus = createStrictMock(EventBus.class);
		action = new GetLinks("12345");
		fp = new LinkPresenter(mockView, eventBus, mockRpc, action, patientid);
	}

	@Test public void testSetData() {
		EchartsUser.staffId = "12345";
		data.add(new String[] { "title1","content1" } );
		data.add(new String[] { "title2","content2" } );
		data.add(new String[] { "title3","content3" } );
		fp.setData(data);

		int i = 1;
		LinkedHashSet<String[]> d = fp.getData();
		for (String [] s : d) {
			assertTrue(s[0].equals("title" + i));
			assertTrue(s[1].equals("content" + i + "?staffid=" + EchartsUser.staffId + "&PATID=" + patientid));
			i++;
		}
	}
}
