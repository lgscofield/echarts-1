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

import java.util.Date;

import org.eastway.echarts.client.presenter.DemographicsPresenter;
import org.eastway.echarts.shared.DemographicsDTO;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DemographicsView extends Composite implements DemographicsPresenter.Display {

	private static DemographicsViewUiBinder uiBinder = GWT
			.create(DemographicsViewUiBinder.class);

	interface DemographicsViewUiBinder extends UiBinder<Widget, DemographicsView> { }

	@UiField SpanElement isAlcoholDrug;
	@UiField SpanElement isBlind;
	@UiField SpanElement gender;
	@UiField SpanElement educationLevel;
	@UiField SpanElement educationType;
	@UiField SpanElement race;

	public DemographicsView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public String[] getAllergies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDob() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEducationLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEducationType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EHR getEhr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmployment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEthnicity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getIncomeSources() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getInsuranceType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getLastEdit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLastEditBy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLivingArrangement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMaritalStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPatientId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPreferredLanguage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRace() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReligion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAlcoholDrug() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isBlind() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildAlcDrug() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDeaf() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDomesticViolence() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDuidwi() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isForensic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isGeneralPopulation() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHearingImpaired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isHivAids() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMimr() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPhyDisabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPhysicalAbuse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isProbationParole() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSchoolDropout() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSexualAbuse() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSmd() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSpeechImpaired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSuicidal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVeteran() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisuallyImpaired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAlcoholDrug(boolean isAlcoholDrug) {
		this.isAlcoholDrug.setInnerText(new Boolean(isAlcoholDrug).toString());
	}

	@Override
	public void setAllergies(String[] allergies) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlind(boolean isBlind) {
		this.isBlind.setInnerText(new Boolean(isBlind).toString());
	}

	@Override
	public void setChildAlcDrug(boolean isChildAlcDrug) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDd(boolean isDd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDeaf(boolean isDeaf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDob(Date dob) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDomesticViolence(boolean isDomesticViolence) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDuidwi(boolean isDuidwi) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEducationLevel(String educationLevel) {
		this.educationLevel.setInnerText(educationLevel);
	}

	@Override
	public void setEducationType(String educationType) {
		this.educationType.setInnerText(educationType);
	}

	@Override
	public void setEhr(EHR ehr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEmployment(String employment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEthnicity(String ethnicity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setForensic(boolean isForensic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGender(String gender) {
		this.gender.setInnerText(gender);
	}

	@Override
	public void setGeneralPopulation(boolean isGeneralPopulation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHearingImpaired(boolean isHearingImpaired) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHivAids(boolean isHivAids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIncomeSources(String[] incomeSources) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInsuranceType(String insuranceType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastEdit(Date lastEdit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLastEditBy(String lastEditBy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLivingArrangement(String livingArrangement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMaritalStatus(String maritalStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMimr(boolean isMimr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPatientId(long patientId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPhyDisabled(boolean isPhyDisabled) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPhysicalAbuse(boolean isPhysicalAbuse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPreferredLanguage(String preferredLanguage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProbationParole(boolean isProbationParole) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRace(String race) {
		this.race.setInnerText(race);
	}

	@Override
	public void setReligion(String religion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSchoolDropout(boolean isSchoolDropout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSexualAbuse(boolean isSexualAbuse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSmd(boolean isSmd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpeechImpaired(boolean isSpeechImpaired) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSuicidal(boolean isSuicidal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVeteran(boolean veteran) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisuallyImpaired(boolean isVisuallyImpaired) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DemographicsDTO toDto() {
		return null;
	}
}
