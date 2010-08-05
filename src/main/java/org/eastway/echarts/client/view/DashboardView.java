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
package org.eastway.echarts.client.view;

import java.util.List;

import org.eastway.echarts.shared.EHR;

import com.google.gwt.app.client.NotificationMole;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.Widget;

public interface DashboardView<T> {

	interface Style extends CssResource {
		String alerts();
		String green();
		String yellow();
		String red();
		String button();
		String searchbox();
		String label();
	}

	public interface Presenter<T> {
		void onItemSelected(String row);

		void changeCurrentEhr(EHR ehr);

		void openEhr(String text);

		void openTickler();
	}

	void addTab(Widget widget, String string);
	void setSelectedTab(int idx);
	Widget asWidget();
	void setPresenter(Presenter<T> presenter);
	void setPatientSearchData(List<String> list);
	void addPatientSearchData(String str);
	void showEhrStub(boolean visible);
	NotificationMole getMole();
	void reset();
	void setProductivity(String productivity, String color);
	void setBonusProjection(String bonusProjection);
	void setName(String name);
	void setCaseStatus(String descriptor);
	void setAge(String string);
	void setDob(String string);
	void setProvider(String provider);
	void setSsn(String ssn);
}
