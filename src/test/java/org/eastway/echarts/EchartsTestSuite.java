package org.eastway.echarts;

import org.eastway.echarts.activity.ProfileActivityTest;
import org.eastway.echarts.activity.TicklerActivityTest;
import org.eastway.echarts.client.ui.GwtTestTicklerView;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.google.gwt.junit.tools.GWTTestSuite;

@RunWith(Suite.class)
@SuiteClasses({GwtTestTicklerView.class,
	TicklerActivityTest.class,
	ProfileActivityTest.class})
public class EchartsTestSuite extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("echarts test suite");
		suite.addTestSuite(GwtTestTicklerView.class);
		suite.addTestSuite(TicklerActivityTest.class);
		suite.addTestSuite(ProfileActivityTest.class);
		return suite;
	}
}