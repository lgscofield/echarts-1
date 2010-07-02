package org.eastway.echarts.client.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.app.client.NotificationMole;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class DashboardViewImpl<T> extends Composite implements DashboardView<T> {
	@SuppressWarnings("unchecked")
	@UiTemplate("Dashboard.ui.xml")
	interface DashboardViewUiBinder extends UiBinder<Widget, DashboardViewImpl> { }

	private static DashboardViewUiBinder uiBinder = GWT.create(DashboardViewUiBinder.class);

	@UiField Anchor logoutButton;
	@UiField FlexTable table;
	@UiField Style style;
	@UiField FlowPanel currentPatientData;
	@UiField Button patientSearchButton;
	@UiField SuggestBox patientIdBox;
	@UiField NotificationMole mole;
	@UiField DisclosurePanel patientListPanel;

	private MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
	private int record = 0;

	public DashboardViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		logoutButton.setHref("http://ewsql.eastway.local/echarts/logout.aspx?continue=" + Window.Location.getHref());
		bind();
	}

	private Presenter<T> presenter;

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void setPresenter(Presenter<T> presenter) {
		this.presenter = presenter;
	}

	@UiField TabLayoutPanel tabLayoutPanel;

	@Override
	public void addTab(final Widget widget, String string) {
		Label closeTab = new Label();
		HorizontalPanel tabHeader = new HorizontalPanel();
		closeTab.setTitle("Close");
		closeTab.addStyleName("close-Tab");
		closeTab.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Integer idx = tabLayoutPanel.getWidgetIndex(widget);
				if (tabLayoutPanel.remove(idx))
					tabLayoutPanel.selectTab(idx - 1);
			}
		});
		tabHeader.add(new Label(string));
		tabHeader.add(closeTab);
		tabLayoutPanel.add(widget, tabHeader);
		setSelectedTab(tabLayoutPanel.getWidgetIndex(widget));
	}

	@Override
	public void setSelectedTab(int idx) {
		tabLayoutPanel.selectTab(idx);
	}

	@UiFactory SuggestBox initSuggestBox() {
		return new SuggestBox(oracle);
	}

	@Override
	public void setPatientSearchData(List<String> list) {
		for (String s : list) {
			oracle.add(s);
			Label lbl = new Label(s);
			table.setWidget(record++, 0, lbl);
			lbl.addStyleName(style.label());
		}
	}

	@Override
	public void addPatientSearchData(String str) {
		oracle.add(str);
		Label lbl = new Label(str);
		table.setWidget(record++, 0, lbl);
		lbl.addStyleName(style.label());
	}

	@Override
	public void setCurrentEhrData(ArrayList<String[]> data) {
		if (data == null) {
			currentPatientData.clear();
			return;
		}
		currentPatientData.clear();
		int i = 0;
		FlexTable cpd = new FlexTable();
		cpd.setText(0,0,data.get(i)[0] + ":");
		cpd.setText(0,1,data.get(i++)[1]);
		cpd.setText(0,2,data.get(i)[0] + ":");
		String dob = DateTimeFormat.getFormat("M/d/y").format(new Date(new Long(data.get(i++)[1]))).toString();
		String age = "(" + data.get(i++)[1] + ")";
		cpd.setText(0,3,dob + " " + age);

		cpd.setText(1,0,data.get(i)[0] + ":");
		cpd.setText(1,1,data.get(i++)[1]);
		cpd.setText(1,2,data.get(i)[0] + ":");
		cpd.setText(1,3,data.get(i++)[1]);

		cpd.setText(2,0,data.get(i)[0] + ":");
		cpd.setText(2,1,data.get(i++)[1]);
		currentPatientData.add(cpd);
	}

	@UiHandler("table")
	void handleItemSelected(ClickEvent event) {
		int selected = -1;

		FlexTable.Cell cell = table.getCellForEvent(event);

		if (cell != null) {
			if (cell.getCellIndex() >= 0) {
				selected = cell.getRowIndex();
			}
		}
		String row = table.getText(selected, 0);
		if (row != null)
			presenter.openEhr(row);
	}

	private void bind() {
		tabLayoutPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				Widget w = tabLayoutPanel.getWidget(event.getSelectedItem());
				if (w instanceof EHRViewImpl<?>) {
					presenter.changeCurrentEhr(((EHRViewImpl<?>) w).getPresenter().getEhr());
				} else {
					presenter.changeCurrentEhr(null);
				}
			}
		});
		patientListPanel.addOpenHandler(new OpenHandler<DisclosurePanel> () {

			@Override
			public void onOpen(OpenEvent<DisclosurePanel> event) {
				presenter.patientListOpen();
			}
			
		});
	}

	@UiHandler("patientSearchButton")
	void handleSearchButtonClicked(ClickEvent event) {
		String str = patientIdBox.getText();
		if (str.isEmpty())
			return;
		presenter.openEhr(str);
		patientIdBox.setText("");
	}

	@Override
	public NotificationMole getMole() {
		return mole;
	}

	@Override
	public void reset() {
		record = 0;
		table.clear();
		oracle.clear();
	}
}
