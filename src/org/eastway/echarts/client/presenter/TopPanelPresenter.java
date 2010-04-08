package org.eastway.echarts.client.presenter;

import java.util.LinkedHashMap;

import org.eastway.echarts.client.Rpc;
import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.client.events.LogoutEvent;
import org.eastway.echarts.client.events.OpenEhrEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;

public class TopPanelPresenter extends Presenter<TopPanelPresenter.Display> {
	public interface Display extends EchartsDisplay {
		void setData(LinkedHashMap<String, Long> data);

		HasClickHandlers getSearchButton();

		HasText getSuggestBox();

		HasClickHandlers getLogoutButton();
	}

	private LinkedHashMap<String, Long> data;

	public TopPanelPresenter(Display display, HandlerManager eventBus) {
		super(display, eventBus);
		fetchData();
		bind();
	}

	private void bind() {
		display.getSearchButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new OpenEhrEvent(data.get(display.getSuggestBox().getText())));
				display.getSuggestBox().setText("");
			}
		});
		display.getLogoutButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				eventBus.fireEvent(new LogoutEvent());
			}
		});
	}

	public void fetchData() {
		AsyncCallback<LinkedHashMap<String, Long>> callback = new AsyncCallback<LinkedHashMap<String, Long>>() {

			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(LinkedHashMap<String, Long> data) {
				display.setData(data);
				setData(data);
			}
		};
		Rpc.singleton().getPatientList(UserImpl.getSessionId(), callback);
	}

	protected void setData(LinkedHashMap<String, Long> data) {
		this.data = data;
	}

	@Override
	public void go(HasWidgets container) {
		// TODO Auto-generated method stub
		
	}
}
