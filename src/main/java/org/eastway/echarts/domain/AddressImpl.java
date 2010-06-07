package org.eastway.echarts.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.eastway.echarts.shared.Address;
import org.eastway.echarts.shared.AddressDTO;

@Entity
@Table(name = "Address")
public class AddressImpl implements Address {
	@Id
	@TableGenerator(name="tg", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tg")
	private long id;
	private String caseNumber;
	private String descriptor;
	private String title;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private String zip;
	private String county;
	private String phone1;
	private String phone1Desc;
	private String phone2;
	private String phone2Desc;
	private Date lastEdit;
	private String lastEditBy;

	public AddressImpl() { }

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getStreet2() {
		return street2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getZip() {
		return zip;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCounty() {
		return county;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1Desc(String phone1Desc) {
		this.phone1Desc = phone1Desc;
	}

	public String getPhone1Desc() {
		return phone1Desc;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2Desc(String phone2Desc) {
		this.phone2Desc = phone2Desc;
	}

	public String getPhone2Desc() {
		return phone2Desc;
	}

	public void setLastEdit(Date lastEdit) {
		this.lastEdit = lastEdit;
	}

	public Date getLastEdit() {
		return lastEdit;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}

	@Override
	public AddressDTO toDto() {
		AddressDTO dto = new AddressDTO();
		dto.setCaseNumber(caseNumber);
		dto.setCity(city);
		dto.setCounty(county);
		dto.setDescriptor(descriptor);
		dto.setId(id);
		dto.setLastEdit(lastEdit);
		dto.setLastEditBy(lastEditBy);
		dto.setPhone1(phone1);
		dto.setPhone1Desc(phone1Desc);
		dto.setPhone2(phone2);
		dto.setPhone2Desc(phone2Desc);
		dto.setState(state);
		dto.setStreet1(street1);
		dto.setStreet2(street2);
		dto.setTitle(title);
		dto.setZip(zip);
		return dto;
	}
}
