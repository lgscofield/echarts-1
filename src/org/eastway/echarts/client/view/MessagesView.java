package org.eastway.echarts.client.view;

import java.util.Date;

import org.eastway.echarts.client.presenter.MessagesPresenter;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;

public class MessagesView extends Composite implements MessagesPresenter.Display {
	private static MessagesViewUiBinder uiBinder = GWT.create(MessagesViewUiBinder.class);

	interface MessagesViewUiBinder extends
			UiBinder<Widget, MessagesView> {}

	@UiField FlowPanel messages;
	@UiField Button add;

	public MessagesView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setData(Messages data) {
		messages.clear();
		Message m;
		if (data.get(0) == null)
			messages.add(new HTML("No Messages"));
		else
			for (int i = 0; (m = data.get(i)) != null; i++)
				messages.add(formatMessage(m));
	}

	private String formatDate(Date date) {
		return DateTimeFormat.getShortDateTimeFormat().format(date);
	}

	private HTML formatMessage(Message m) {
		return new HTML("<strong>" + formatDate(m.getMessageDate())
			+ "</strong>&mdash;"
			+ m.getMessageType() + "&mdash;"
			+ m.getLastEditBy()
			+ "<div class='home-PatientMessage'>"
			+ m.getMessage() + "</div>");
	}

	@Override
	public HasClickHandlers getAddButton() {
		return add;
	}
}
