package org.eastway.echarts.tab.client.view;

import org.eastway.echarts.client.view.FormsView;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.PatientView;
import org.eastway.echarts.client.view.ServiceHistoryView;
import org.eastway.echarts.tab.client.presenter.TabPresenter;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class TabView extends Composite implements TabPresenter.Display {
	private static PatientTabViewUiBinder uiBinder = GWT.create(PatientTabViewUiBinder.class);

	interface PatientTabViewUiBinder extends
			UiBinder<Widget, TabView> {}

	@UiField Tree menu;
	@UiField DockLayoutPanel displayArea;

	private TreeItem forms, personal, messages, serviceHistory;

	public TabView() {
		initWidget(uiBinder.createAndBindUi(this));
		buildMenu();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasSelectionHandlers<TreeItem> getMenu() {
		return menu;
	}

	@Override
	public void setDisplay(Object view) {
		displayArea.clear();
		displayArea.add((Widget)view);
	}

	private void buildMenu() {
		personal = menu.addItem("Personal");
		personal.setUserObject(new PatientView());

		messages = menu.addItem("Messages");
		messages.setUserObject(new MessagesView());

		serviceHistory = menu.addItem("Service History");
		serviceHistory.setUserObject(new ServiceHistoryView());

		forms = menu.addItem("Forms");
		forms.setUserObject(new FormsView());
	}

	@Override
	public Object getView(TreeItem selectedItem) {
		return selectedItem.getUserObject();
	}
}
