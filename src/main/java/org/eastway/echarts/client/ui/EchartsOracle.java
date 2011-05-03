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
import org.eastway.echarts.client.request.PatientRequest;
import org.eastway.echarts.shared.PatientListSuggestion;

import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.inject.Inject;

public class EchartsOracle extends SuggestOracle {

	private EchartsRequestFactory requestFactory;

	@Inject
	public EchartsOracle(EchartsRequestFactory requestFactory, EventBus eventBus) {
		this.requestFactory = requestFactory;
	}

	@Override
	public void requestSuggestions(final Request request, final Callback callback) {
		String query = request.getQuery().replaceAll("\\s+", " ");

		final int maxResult = request.getLimit();
		final int startPosition = 0;
		final Response searchResponse = new Response();
		PatientRequest context = requestFactory.patientRequest();
		context.findPatientsLikeCount(query).to(new Receiver<Long>() {
			@Override
			public void onSuccess(Long response) {
				searchResponse.setMoreSuggestionsCount(response.intValue() - maxResult);
			}
		});
		context.findPatientsEntriesLike(query, startPosition, maxResult).to(new Receiver<List<String>>() {
			@Override
			public void onSuccess(List<String> response) {
				List<Suggestion> suggestions = new ArrayList<Suggestion>();
				for (String s : response)
					suggestions.add(new PatientListSuggestion(s));
				searchResponse.setSuggestions(suggestions);
			}
		});
		context.fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				callback.onSuggestionsReady(request, searchResponse);
			}
		});
	}

}
