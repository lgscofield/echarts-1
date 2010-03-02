package org.eastway.echarts.client.forms.presenter;

import org.eastway.echarts.client.presenter.EchartsDisplay;
import org.eastway.echarts.client.presenter.EchartsPresenter;

import com.google.gwt.event.shared.HandlerManager;

public class NursingNotePresenter extends EchartsPresenter<NursingNotePresenter.Display> {

	public interface Display extends EchartsDisplay {
		
	}

	public NursingNotePresenter(Display display, HandlerManager eventBus) {
		super(display, eventBus);
	}

}
