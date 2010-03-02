package org.eastway.echarts.client.forms.view;

import org.eastway.echarts.client.forms.presenter.CPSTNotePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

public class CPSTNoteView extends Composite implements CPSTNotePresenter.Display {

	private static CPSTNoteUiBinder uiBinder = GWT
			.create(CPSTNoteUiBinder.class);

	interface CPSTNoteUiBinder extends UiBinder<Widget, CPSTNoteView> { }

	@UiField ListBox nonBillable;

	public CPSTNoteView() {
		initWidget(uiBinder.createAndBindUi(this));
		setNonBillable();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setNonBillable() {
		nonBillable.addItem("");
		nonBillable.addItem("Cancelled appointment due to scheduling conflict");
		nonBillable.addItem("Attempted to contact client");
		nonBillable.addItem("Prompted client to attend scheduled appointment");
		nonBillable.addItem("Returned client's call and left message");
		nonBillable.addItem("Explained/obtained release/s of information");
	}
}
