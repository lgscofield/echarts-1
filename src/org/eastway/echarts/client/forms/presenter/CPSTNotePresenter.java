package org.eastway.echarts.client.forms.presenter;

import org.eastway.echarts.client.presenter.EchartsDisplay;
import org.eastway.echarts.client.presenter.EchartsPresenter;

import com.google.gwt.event.shared.HandlerManager;

public class CPSTNotePresenter extends EchartsPresenter<CPSTNotePresenter.Display> {

	public interface Display extends EchartsDisplay {
		void setNonBillable();
	}

	public CPSTNotePresenter(Display display, HandlerManager eventBus) {
		super(display, eventBus);
		display.setNonBillable();
	}
}
