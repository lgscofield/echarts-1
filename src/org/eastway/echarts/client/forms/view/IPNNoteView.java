package org.eastway.echarts.client.forms.view;

import org.eastway.echarts.client.forms.presenter.IPNNotePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class IPNNoteView extends Composite implements IPNNotePresenter.Display {

	private static IPNNoteUiBinder uiBinder = GWT
			.create(IPNNoteUiBinder.class);

	interface IPNNoteUiBinder extends UiBinder<Widget, IPNNoteView> {
	}

	public IPNNoteView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
