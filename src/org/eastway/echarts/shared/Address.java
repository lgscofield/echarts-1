package org.eastway.echarts.shared;

public class Address {
	private String title;
	private String[] street;
	private String city;
	private String state;
	private String zip;
	private String county;
	private String[] phone;
	private String[] phoneDescription;
	private long lastEdit;
	private String lastEditBy;

	public Address(String title,
			String[] street,
			String city,
			String state,
			String zip,
			String county,
			String[] phone,
			String[] phoneDescription,
			long lastEdit,
			String lastEditBy) {
		setTitle(title);
		setStreet(street);
		setCity(city);
		setState(state);
		setZip(zip);
		setCounty(county);
		setPhone(phone);
		setPhoneDescription(phoneDescription);
		setLastEdit(lastEdit);
		setLastEditBy(lastEditBy);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setStreet(String[] street) {
		this.street = street;
	}

	public String[] getStreet() {
		return street;
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

	public void setPhone(String[] phone) {
		this.phone = phone;
	}

	public String[] getPhone() {
		return phone;
	}

	public void setPhoneDescription(String[] phoneDescription) {
		this.phoneDescription = phoneDescription;
	}

	public String[] getPhoneDescription() {
		return phoneDescription;
	}

	public void setLastEdit(long lastEdit) {
		this.lastEdit = lastEdit;
	}

	public long getLastEdit() {
		return lastEdit;
	}

	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}

	public String getLastEditBy() {
		return lastEditBy;
	}
}
