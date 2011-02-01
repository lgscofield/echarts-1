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

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.place.shared.Place;
import com.google.gwt.resources.client.CssResource;

public interface DashboardView<T> extends IsWidget {

	interface Style extends CssResource {
		String green();
		String yellow();
		String red();
		String button();
		String searchbox();
		String label();
	}

	public interface Presenter<T> {
		void openTickler();

		void goTo(Place place);
	}

	void setPresenter(Presenter<T> presenter);
	void setProductivity(String productivity, String color);
	void setBonusProjection(String bonusProjection);
	void isFirstLogin();
}
