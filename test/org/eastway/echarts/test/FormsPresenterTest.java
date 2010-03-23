package org.eastway.echarts.test;

import static org.easymock.EasyMock.createStrictMock;
import static org.junit.Assert.*;

import java.util.LinkedHashSet;

import org.eastway.echarts.client.RpcServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.presenter.FormsPresenter;
import org.junit.Test;

import com.google.gwt.event.shared.HandlerManager;

public class FormsPresenterTest {
	private FormsPresenter fp;
	private RpcServicesAsync mockRpc;
	private FormsPresenter.Display mockView;
	private HandlerManager eventBus;
	private LinkedHashSet<String[]> data = new LinkedHashSet<String[]>();
	private String patientid = "000000008";

	public FormsPresenterTest() {
		mockView = createStrictMock(FormsPresenter.Display.class);
		mockRpc = createStrictMock(RpcServicesAsync.class);
		eventBus = new HandlerManager(null);
		fp = new FormsPresenter(mockView, eventBus, mockRpc, patientid);
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
