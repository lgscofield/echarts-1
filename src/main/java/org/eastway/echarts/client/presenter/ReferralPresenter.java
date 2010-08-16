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
import org.eastway.echarts.client.rpc.EchartsCallback;
import org.eastway.echarts.shared.GetReferral;
import org.eastway.echarts.shared.GetReferralResult;
import org.eastway.echarts.shared.Referral;

import com.google.gwt.user.client.ui.HasWidgets;

public class ReferralPresenter implements Presenter {

	public interface Display extends EchartsDisplay, Referral {
		void nextRecord();
	}

	private Display display;
	private EventBus eventBus;
	private CachingDispatchAsync dispatch;
	private GetReferral action;

	public ReferralPresenter(Display display, EventBus eventBus, CachingDispatchAsync dispatch, GetReferral action) {
		this.display = display;
		this.eventBus = eventBus;
		this.dispatch = dispatch;
		this.action = action;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchData();
	}

	private void fetchData() {
		dispatch.executeWithCache(action, new EchartsCallback<GetReferralResult>(eventBus) {
			@Override
			protected void handleFailure(Throwable caught) {
			}

			@Override
			protected void handleSuccess(GetReferralResult result) {
				Referral referral = result.getReferral();
				display.setAdmissionDate(referral.getAdmissionDate());
				display.setDischargeDate(referral.getDischargeDate());
				display.setDisposition(referral.getDisposition());
				display.setLastEdit(referral.getLastEdit());
				display.setLastEditBy(referral.getLastEditBy());
				display.setLastService(referral.getLastService());
				display.setLevelOfCare(referral.getLevelOfCare());
				display.setPlanType(referral.getPlanType());
				display.setProgram(referral.getPlanType());
				display.setReferralDate(referral.getReferralDate());
				display.setReferralSource(referral.getReferralSource());
				display.setReferralType(referral.getReferralType());
				display.setTakenByStaff(referral.getTakenByStaff());
				display.setUCI(referral.getUCI());
				display.setUPId(referral.getUPId());
			}
		});
	}
}
