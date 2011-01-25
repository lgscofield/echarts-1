package org.eastway.echarts.client.ui;

import org.eastway.echarts.client.request.DiagnosisProxy;
import org.eastway.echarts.client.style.GlobalResources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DiagnosisDetail extends Composite {

	private static DiagnosisDetailImplUiBinder uiBinder = GWT
			.create(DiagnosisDetailImplUiBinder.class);

	@SuppressWarnings("unchecked")
	@UiTemplate("DiagnosisDetail.ui.xml")
	interface DiagnosisDetailImplUiBinder extends
			UiBinder<Widget, DiagnosisDetail> {
	}

	@UiField TableElement table;
	@UiField SpanElement date;
	@UiField SpanElement axis1A;
	@UiField SpanElement axis1B;
	@UiField SpanElement axis1C;
	@UiField SpanElement axis1D;
	@UiField SpanElement axis1E;
	@UiField SpanElement axis2A;
	@UiField SpanElement axis2B;
	@UiField SpanElement axis2C;
	@UiField SpanElement axis3;
	@UiField SpanElement axis4;
	@UiField SpanElement currentGAF;
	@UiField SpanElement highestGAF;

	private DiagnosisProxy proxy;

	private DiagnosisDetail() {
		initWidget(uiBinder.createAndBindUi(this));
		setAlternateColors();
	}

	public void setValue(Object proxy) {
		this.proxy = (DiagnosisProxy)proxy;

		date.setInnerText(this.proxy.getDate() == null ? "" : GlobalResources.getDateFormat().format(this.proxy.getDate()));
		axis1A.setInnerText(this.proxy.getAxis1A() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(this.proxy.getAxis1A()));
		axis1B.setInnerText(this.proxy.getAxis1B() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(this.proxy.getAxis1B()));
		axis1C.setInnerText(this.proxy.getAxis1C() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(this.proxy.getAxis1C()));
		axis1D.setInnerText(this.proxy.getAxis1D() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(this.proxy.getAxis1D()));
		axis1E.setInnerText(this.proxy.getAxis1E() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(this.proxy.getAxis1E()));
		axis2A.setInnerText(this.proxy.getAxis2A() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(this.proxy.getAxis2A()));
		axis2B.setInnerText(this.proxy.getAxis2B() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(this.proxy.getAxis2B()));
		axis2C.setInnerText(this.proxy.getAxis2C() == null ? "" : DiagnosisCodeProxyRenderer.instance().render(this.proxy.getAxis2C()));
		axis3.setInnerText(this.proxy.getAxis3() == null ? "" : String.valueOf(this.proxy.getAxis3()));
		axis4.setInnerText(this.proxy.getAxis4() == null ? "" : String.valueOf(this.proxy.getAxis4()));
		currentGAF.setInnerText(this.proxy.getCurrentGAF() == null ? "" : String.valueOf(this.proxy.getCurrentGAF()));
		highestGAF.setInnerText(this.proxy.getHighestGAF() == null ? "" : String.valueOf(this.proxy.getHighestGAF()));
		
	}

	interface Style extends CssResource {
		String evenRow();
	}

	@UiField Style style;

	private void setAlternateColors() {
		NodeList<TableRowElement> nodeList = table.getRows();
		for (int i = 0; i < nodeList.getLength(); i++)
			if ((i % 2) == 0)
				nodeList.getItem(i).addClassName(style.evenRow());
	}

	private static DiagnosisDetail INSTANCE;

	public static DiagnosisDetail instance() {
		if (INSTANCE == null)
			INSTANCE = new DiagnosisDetail();
		return INSTANCE;
	}
}
