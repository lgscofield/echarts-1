package org.eastway.echarts.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class IPNNote extends Composite {

	private static IPNNoteUiBinder uiBinder = GWT
			.create(IPNNoteUiBinder.class);

	interface IPNNoteUiBinder extends UiBinder<Widget, IPNNote> {
	}

	public IPNNote() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
