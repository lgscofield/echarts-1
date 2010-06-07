package org.eastway.echarts.client.view;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import org.eastway.echarts.client.presenter.ReferralPresenter;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.ReferralDTO;

public class ReferralView extends Composite implements ReferralPresenter.Display {

	private static ReferralViewUiBinder uiBinder = GWT
			.create(ReferralViewUiBinder.class);

	interface ReferralViewUiBinder extends UiBinder<Widget, ReferralView> { }

	@UiField SpanElement referralDate;
	@UiField SpanElement UCI;

	public ReferralView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public Date getAdmissionDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDischargeDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisposition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EHR getEhr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
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
	public Date getLastService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLevelOfCare() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPlanType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProgram() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getReferralDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReferralSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReferralType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTakenByStaff() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUCI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUPId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAdmissionDate(Date admissionDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDischargeDate(Date dischargeDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDisposition(String disposition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEhr(EHR ehr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setId(long id) {
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
	public void setLastService(Date lastService) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLevelOfCare(String levelOfCare) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPlanType(String planType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setProgram(String program) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setReferralDate(Date referralDate) {
		if (referralDate != null)
			this.referralDate.setInnerText(referralDate.toString());
	}

	@Override
	public void setReferralSource(String referralSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setReferralType(String referralType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTakenByStaff(String takenByStaff) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUCI(String UCI) {
		if (UCI != null)
			this.UCI.setInnerText(UCI);
	}

	@Override
	public void setUPId(String UPId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReferralDTO toDto() {
		// TODO Auto-generated method stub
		return null;
	}
}
