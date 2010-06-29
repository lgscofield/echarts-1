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

import org.eastway.echarts.client.EHRServicesAsync;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Demographics;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class DemographicsPresenter implements Presenter {

	public interface Display extends EchartsDisplay, Demographics { }

	private EHR ehr;
	private Display display;

	public DemographicsPresenter(Display display,
			HandlerManager eventBus, EHRServicesAsync rpcServices, EHR ehr) {
		this.ehr = ehr;
		this.display = display;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		setData();
	}

	public void setData() {
		Demographics demographics = ehr.getDemographics();
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
