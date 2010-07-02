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
package org.eastway.echarts.client.presenter;

import net.customware.gwt.presenter.client.EventBus;

import org.eastway.echarts.client.CachingDispatchAsync;
import org.eastway.echarts.client.EchartsUser;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.GetDemographics;
import org.eastway.echarts.shared.GetDemographicsResult;

import com.google.gwt.requestfactory.shared.RequestEvent;
import com.google.gwt.requestfactory.shared.RequestEvent.State;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class DemographicsPresenter implements Presenter {

	public interface Display extends EchartsDisplay, Demographics { }

	private Display display;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetDemographics action;
	private long ehrId;

	public DemographicsPresenter(Display display,
			EventBus eventBus, CachingDispatchAsync dispatch, long ehrId) {
		this.ehrId = ehrId;
		this.display = display;
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		action = new GetDemographics(EchartsUser.sessionId, new Long(ehrId).toString());
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchData();
	}

	private void fetchData() {
		eventBus.fireEvent(new RequestEvent(State.SENT));
		dispatch.executeWithCache(action, new AsyncCallback<GetDemographicsResult>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(GetDemographicsResult result) {
				eventBus.fireEvent(new RequestEvent(State.RECEIVED));
				setData(result.getDemographics());
			}
			
		});
	}

	public void setData(Demographics demographics) {
		display.setAllergies(demographics.getAllergies());
		display.setReligion(demographics.getReligion());
		display.setAlcoholDrug(demographics.isAlcoholDrug());
		display.setAllergies(demographics.getAllergies());
		display.setBlind(demographics.isBlind());
		display.setChildAlcDrug(demographics.isAlcoholDrug());
		display.setDd(demographics.isDd());
		display.setDeaf(demographics.isDeaf());
		display.setDob(demographics.getDob());
		display.setDomesticViolence(demographics.isDomesticViolence());
		display.setDuidwi(demographics.isDuidwi());
		display.setEducationLevel(demographics.getEducationLevel());
		display.setEducationType(demographics.getEducationType());
		display.setEmployment(demographics.getEmployment());
		display.setEthnicity(demographics.getEthnicity());
		display.setForensic(demographics.isForensic());
		display.setGender(demographics.getGender());
		display.setGeneralPopulation(demographics.isGeneralPopulation());
		display.setHearingImpaired(demographics.isHearingImpaired());
		display.setHivAids(demographics.isHivAids());
		display.setIncomeSources(demographics.getIncomeSources());
		display.setInsuranceType(demographics.getInsuranceType());
		display.setLastEdit(demographics.getLastEdit());
		display.setLastEditBy(demographics.getLastEditBy());
		display.setLivingArrangement(demographics.getLivingArrangement());
		display.setMaritalStatus(demographics.getMaritalStatus());
		display.setMimr(demographics.isMimr());
		display.setPhyDisabled(demographics.isPhyDisabled());
		display.setPhysicalAbuse(demographics.isPhysicalAbuse());
		display.setPreferredLanguage(demographics.getPreferredLanguage());
		display.setProbationParole(demographics.isProbationParole());
		display.setRace(demographics.getRace());
		display.setReligion(demographics.getReligion());
		display.setSchoolDropout(demographics.isSchoolDropout());
		display.setSexualAbuse(demographics.isSexualAbuse());
		display.setSmd(demographics.isSmd());
		display.setSpeechImpaired(demographics.isSpeechImpaired());
		display.setSuicidal(demographics.isSuicidal());
		display.setVeteran(demographics.isVeteran());
		display.setVisuallyImpaired(demographics.isVisuallyImpaired());
	}
}
