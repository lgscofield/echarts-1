package org.eastway.echarts.client.forms.presenter;

import org.eastway.echarts.client.presenter.EchartsDisplay;
import org.eastway.echarts.client.presenter.EchartsPresenter;

import com.google.gwt.event.shared.HandlerManager;

public class IPNNotePresenter extends EchartsPresenter<IPNNotePresenter.Display> {

	public interface Display extends EchartsDisplay {
		
	}

	public IPNNotePresenter(Display display, HandlerManager eventBus) {
		super(display, eventBus);
	}

}
