package org.eastway.echarts.client.common;

import org.eastway.echarts.client.request.AddressProxy;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;

public class AddressCellTable extends CellTable<AddressProxy> {
	public AddressCellTable() {
		super();
		this.addColumn(new TextColumn<AddressProxy>() {
			@Override
			public String getValue(AddressProxy object) {
				return object.getDescriptor();
			}
		}, "Description");
		this.addColumn(new TextColumn<AddressProxy>() {
			@Override
			public String getValue(AddressProxy object) {
				return object.getTitle();
			}
		}, "Name");
		this.addColumn(new TextColumn<AddressProxy>() {
			@Override
			public String getValue(AddressProxy object) {
				StringBuilder sb = new StringBuilder();
				sb.append(object.getStreet1() == null ? "" : object.getStreet1() + " ");
				sb.append(object.getStreet2() == null ? "" : object.getStreet2() + " ");
				return sb.toString();
			}
		}, "Address");
		this.addColumn(new TextColumn<AddressProxy>() {
			@Override
			public String getValue(AddressProxy object) {
				return object.getCity();
			}
		}, "City");
		this.addColumn(new TextColumn<AddressProxy>() {
			@Override
			public String getValue(AddressProxy object) {
				return object.getState();
			}
		}, "State");
		this.addColumn(new TextColumn<AddressProxy>() {
			@Override
			public String getValue(AddressProxy object) {
				return object.getZip();
			}
		}, "Zip");
		this.addColumn(new TextColumn<AddressProxy>() {
			@Override
			public String getValue(AddressProxy object) {
				StringBuilder sb = new StringBuilder();
				sb.append(object.getPhone1Desc() == null ? "" : object.getPhone1Desc() + " ");
				sb.append(object.getPhone1() == null ? "" : object.getPhone1());
				return sb.toString();
			}
		}, "Phone 1");
		this.addColumn(new TextColumn<AddressProxy>() {
			@Override
			public String getValue(AddressProxy object) {
				StringBuilder sb = new StringBuilder();
				sb.append(object.getPhone2Desc() == null ? "" : object.getPhone2Desc() + " ");
				sb.append(object.getPhone2() == null ? "" : object.getPhone2());
				return sb.toString();
			}
		}, "Phone 2");
	}
}
