package org.eastway.echarts.client.forms.view;

import org.eastway.echarts.client.forms.presenter.NursingNotePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class NursingNoteView extends Composite implements NursingNotePresenter.Display {

	private static NursingNoteViewUiBinder uiBinder = GWT
			.create(NursingNoteViewUiBinder.class);

	interface NursingNoteViewUiBinder extends
			UiBinder<Widget, NursingNoteView> {
	}

	public NursingNoteView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

}
