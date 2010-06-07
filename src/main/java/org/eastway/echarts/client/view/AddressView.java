package org.eastway.echarts.client.view;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Widget;

import org.eastway.echarts.client.presenter.AddressPresenter;
import org.eastway.echarts.shared.AddressDTO;

public class AddressView extends Composite implements AddressPresenter.Display {

	private static AddressViewUiBinder uiBinder = GWT
			.create(AddressViewUiBinder.class);

	interface AddressViewUiBinder extends UiBinder<Widget, AddressView> { }

	private int record = 0;
	@UiField FlexTable addresses;

	public AddressView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public String getCaseNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCounty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescriptor() {
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
	public String getPhone1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPhone1Desc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPhone2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPhone2Desc() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStreet1() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStreet2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getZip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCaseNumber(String caseNumber) {
		this.addresses.setText(record, 0, caseNumber);
	}

	@Override
	public void setCity(String city) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCounty(String county) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescriptor(String descriptor) {
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
	public void setPhone1(String phone1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPhone1Desc(String phone1desc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPhone2(String phone2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPhone2Desc(String phone2desc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setState(String state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStreet1(String street1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStreet2(String street2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setZip(String zip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AddressDTO toDto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nextRecord() {
		record++;
	}
}
