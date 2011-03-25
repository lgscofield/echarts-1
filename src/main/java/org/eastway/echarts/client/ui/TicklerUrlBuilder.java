package org.eastway.echarts.client.ui;

import org.eastway.echarts.client.EchartsUser;

import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;

public class TicklerUrlBuilder {

	public static String createIspUrl(String caseNumber) {
		return new UrlBuilder().setProtocol(Window.Location.getProtocol())
					.setHost(EchartsUser.dbServerUrl)
					.setPath("/echarts-asp/forms/GandO.asp")
					.setParameter("staffid", EchartsUser.staffId)
					.setParameter("PATID", caseNumber)
					.buildString();
	}

	public static String createIspReviewUrl(String caseNumber) {
		return new UrlBuilder().setProtocol(Window.Location.getProtocol())
					.setHost(EchartsUser.dbServerUrl)
					.setPath("/echarts-asp/forms/GandOReview.asp")
					.setParameter("staffid", EchartsUser.staffId)
					.setParameter("PATID", caseNumber)
					.buildString();
	}

	public static String createHealthHistoryUrl(String caseNumber) {
		return new UrlBuilder()
					.setProtocol(Window.Location.getProtocol())
					.setHost(EchartsUser.dbServerUrl)
					.setPath("/echarts-asp/forms/107HealthHxEdit.asp")
					.setParameter("staffid", EchartsUser.staffId)
					.setParameter("PATID", caseNumber)
					.buildString();
	}

	public static String createDAUpdateUrl(String caseNumber) {
		return new UrlBuilder().setProtocol(Window.Location.getProtocol())
					.setHost(EchartsUser.dbServerUrl)
					.setPath("/echarts-asp/forms/110ADAUpdateEdit.asp")
					.setParameter("staffid", EchartsUser.staffId)
					.setParameter("PATID", caseNumber)
					.buildString();
	}

	public static String createFinancialUpdateUrl(String caseNumber) {
		return new UrlBuilder().setProtocol(Window.Location.getProtocol())
					.setHost(EchartsUser.dbServerUrl)
					.setPath("/echarts-asp/forms/109FinancialAuthEdit.asp")
					.setParameter("staffid", EchartsUser.staffId)
					.setParameter("PATID", caseNumber)
					.buildString();
	}

	public static String createOocUrl() {
		return "https://home.eastway.local/outcomes/";
	}
}
