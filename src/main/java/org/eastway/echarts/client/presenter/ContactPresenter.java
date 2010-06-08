package org.eastway.echarts.client.presenter;

import org.eastway.echarts.shared.Contact;
import org.eastway.echarts.shared.EHR;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.HasWidgets;

public class ContactPresenter extends Presenter<ContactPresenter.Display> {

	public interface Display extends EchartsDisplay, Contact {
		public void nextRecord();
	}

	private EHR ehr;

	public ContactPresenter(Display display, HandlerManager eventBus, EHR ehr) {
		super(display, eventBus);
		this.ehr = ehr;
	}


	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		setData();
	}

	public void setData() {
		for (Contact contact : ehr.getContacts()) {
			display.setFirstName(contact.getFirstName());
			display.nextRecord();
		}
	}
}
