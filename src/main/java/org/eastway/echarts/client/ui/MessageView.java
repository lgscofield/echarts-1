package org.eastway.echarts.client.ui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.ui.IsWidget;

public interface MessageView<T> extends IsWidget {

	public interface Presenter<T> {
		String getId();

		void save(String messageType, String message);
	}

	public interface Style extends CssResource {
		String hide();
		String show();
	}

	void setData(ArrayList<String[]> data);

	void setText(String patientId);

	HasClickHandlers getSaveButton();

	HasClickHandlers getCloseButton();

	String getMessage();

	String getMessageType();

	void saved();

	void close();

	void show();

	void setMessageTypes(ArrayList<String> types);

	void setPresenter(Presenter<T> presenter);

	void setError(String message);

	void reset();

	boolean isDisplayVisible();
}
