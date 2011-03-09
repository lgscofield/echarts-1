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
package org.eastway.echarts.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.eastway.echarts.client.common.ColumnDefinition;
import org.eastway.echarts.client.common.TicklerColumnDefinitionsImpl;
import org.eastway.echarts.client.ui.TicklerViewImpl;
import org.eastway.echarts.shared.Tickler;
import org.junit.Before;
import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

public class GwtTestTicklerView extends GWTTestCase {
	TicklerViewImpl<Tickler> ticklerView;
	List<ColumnDefinition<Tickler>> columnDefinitions = new TicklerColumnDefinitionsImpl();
	List<Tickler> ticklers;

	@Override
	public String getModuleName() {
		return "org.eastway.echarts.Echarts";
	}

	@Before
	public void gwtSetUp() {
		ticklers = new ArrayList<Tickler>();
		setTicklers();
		ticklerView = new TicklerViewImpl<Tickler>();
		ticklerView.setColumnDefinitions(columnDefinitions);
		ticklerView.setRowData(ticklers);
	}

	private void setTicklers() {
		for (int i = 0; i < 100; i++) {
			Tickler tickler = new Tickler();
			tickler.setCaseNumber("0" + i);
			tickler.setDiagnosticAssessmentUpdate("", 0);
			tickler.setFinancialDueDate("", 0);
			tickler.setHealthHistoryDueDate("", 0);
			tickler.setHipaaDateCompleted("");
			tickler.setIspDueDate("", 0);
			tickler.setIspReviewDueDate("", 0);
			tickler.setName("test, harold");
			tickler.setOoc("", 0);
			tickler.setService("");
			tickler.setStaffName("name, staff");
			ticklers.add(tickler);
		}
	}

	@Test public void testGetRow() {
		for (int row = 1, i = 0; row < ticklerView.table.getRowCount(); row++) {
			String header = ticklerView.table.getText(row, 1);
			if (header.matches("Case #"))
				continue;
			String caseNumber2 = ticklers.get(ticklerView.getRow(row)).getCaseNumber();
			String caseNumber1 = ticklers.get(i++).getCaseNumber();
			assertEquals(caseNumber1, caseNumber2);
		}
	}
}
