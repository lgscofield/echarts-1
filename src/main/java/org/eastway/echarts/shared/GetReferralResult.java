package org.eastway.echarts.shared;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class GetReferralResult implements Result {

	private Referral referral;

	GetReferralResult() { }

	public GetReferralResult(Referral referral) {
		this.referral = referral;
	}

	public Referral getReferral() {
		return referral;
	}
}
