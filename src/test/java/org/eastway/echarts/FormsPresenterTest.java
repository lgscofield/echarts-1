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

import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.presenter.LinkPresenter;
import org.junit.Test;

import com.google.gwt.event.shared.HandlerManager;

public class FormsPresenterTest {
	private LinkPresenter fp;
	private RpcServicesAsync mockRpc;
	private LinkPresenter.Display mockView;
	private HandlerManager eventBus;
	private LinkedHashSet<String[]> data = new LinkedHashSet<String[]>();
	private String patientid = "000000008";

	public FormsPresenterTest() {
		mockView = createStrictMock(LinkPresenter.Display.class);
		mockRpc = createStrictMock(RpcServicesAsync.class);
		eventBus = new HandlerManager(null);
		fp = new LinkPresenter(mockView, eventBus, mockRpc, patientid);
	}

	@Test public void testSetData() {
		UserImpl.setSessionId("12345");
		data.add(new String[] { "title1","content1" } );
		data.add(new String[] { "title2","content2" } );
		data.add(new String[] { "title3","content3" } );
		fp.setData(data);

		int i = 1;
		LinkedHashSet<String[]> d = fp.getData();
		for (String [] s : d) {
			assertTrue(s[0].equals("title" + i));
			assertTrue(s[1].equals("content" + i + "?staffid=" + UserImpl.getStaffId() + "&PATID=" + patientid));
			i++;
		}
	}
}
