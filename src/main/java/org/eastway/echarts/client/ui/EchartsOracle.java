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

import com.google.gwt.event.shared.EventBus;

import org.eastway.echarts.client.request.EchartsRequestFactory;
import org.eastway.echarts.shared.PatientListSuggestion;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.inject.Inject;

public class EchartsOracle extends MultiWordSuggestOracle {

	private EchartsRequestFactory requestFactory;

	@Inject
	public EchartsOracle(EchartsRequestFactory requestFactory, EventBus eventBus) {
		this.requestFactory = requestFactory;
	}

	@Override
	public void requestSuggestions(final Request request, final Callback callback) {
		requestFactory.patientRequest().findPatientsLike(request.getQuery()).fire(new Receiver<List<String>>() {
			@Override
			public void onSuccess(List<String> response) {
				Response searchResponse = new Response();
				List<Suggestion> suggestions = new ArrayList<Suggestion>();
				for (String s : response)
					suggestions.add(new PatientListSuggestion(s));
				searchResponse.setSuggestions(suggestions);
				callback.onSuggestionsReady(request, searchResponse);
			}
		});
	}

}
