package org.eastway.echarts.client.view;

import org.eastway.echarts.client.forms.view.IspView;
import org.eastway.echarts.client.forms.view.ProgressNoteView;
import org.eastway.echarts.client.presenter.PatientTabPresenter;
import org.eastway.echarts.client.view.AddMessageView;
import org.eastway.echarts.client.view.MessagesView;
import org.eastway.echarts.client.view.ServiceHistoryView;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;

public class PatientTabView extends Composite implements PatientTabPresenter.Display {
	private static PatientTabViewUiBinder uiBinder = GWT.create(PatientTabViewUiBinder.class);

	interface PatientTabViewUiBinder extends
			UiBinder<Widget, PatientTabView> {}

	@UiField Tree menu;
	@UiField DockLayoutPanel displayArea;

	private TreeItem personal, demographics, progressNote, messages,
			addMessage, treatmentPlan, serviceHistory;

	public PatientTabView() {
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
		personal.setUserObject(new PersonalView());

		demographics = menu.addItem("Demographics");
		demographics.setUserObject(new DemographicsView());

		progressNote = menu.addItem("Progress Note");
		progressNote.setUserObject(new ProgressNoteView());

		messages = menu.addItem("Messages");
		messages.setUserObject(new MessagesView());

		addMessage = messages.addItem("Add");
		addMessage.setUserObject(new AddMessageView());

		treatmentPlan = menu.addItem("Treatment Plan");
		treatmentPlan.setUserObject(new IspView());

		serviceHistory = menu.addItem("Service History");
		serviceHistory.setUserObject(new ServiceHistoryView());
	}

	@Override
	public Object getView(TreeItem selectedItem) {
		return selectedItem.getUserObject();
	}
}
