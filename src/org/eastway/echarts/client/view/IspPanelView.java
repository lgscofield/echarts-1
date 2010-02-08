package org.eastway.echarts.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class IspPanelView extends Composite {

	private static IspPanelUiBinder uiBinder = GWT.create(IspPanelUiBinder.class);

	interface IspPanelUiBinder extends UiBinder<Widget, IspPanelView> {}

	@UiField
	SpanElement patientId;

	@UiField
	TextBox GATreatRecommendNo, GADated, GAStartDate,
			GATargetCompletionDate, GAAdjustedTargetDate,
			GAReasonforAdjustment,
			GAClientInitials, GAO1Duration, GAO1StartDate,
			AnticipatedDate;

	@UiField
	TextArea GAOutcomes, GAGoalCollaboration, GADesiredResults,
			GAStrengthsUsedtoMeetGoal, GASkillsKnowledgeNeeded,
			GANaturalCommunitySupports,
			GAO1Desc, GAO1ClientWill, GAO1OtherWill, GAO1TherInt1,
			GAO1Freq1, GAO1ProvType1, GAO1TherInt2, GAO1Freq2,
			GAO1ProvType2,
			GAO1TherInt3, GAO1Freq3, GAO1ProvType3, AgencyName1,
			ContactAndTitle1,
			ServicesProvided1, AgencyName2, ContactAndTitle2,
			ServicesProvided2,
			AgencyName3, ContactAndTitle3, ServicesProvided3,
			AgencyName4,
			ContactAndTitle4, ServicesProvided4, AgencyName5,
			ContactAndTitle5,
			ServicesProvided5, LevelofCare;

	@UiField
	RadioButton GAClientReviewedYes, GAClientReviewedNo,
			GAClientAgreesYes, GAClientAgreesNo, LevelIA, LevelIIA,
			LevelIIIB,
			LevelIB, LevelIIB, LevelIIIC, LevelIC, LevelIIIA,
			LevelIV, LevelIA2,
			LevelII, LevelIB2, LevelIII, LevelIC2;

	@UiField
	CheckBox GAO1NotClinIndicated, AoDAdultLevelofCareNA,
			AoDYouthLevelofCareNA;

	@UiField
	ListBox GAO1ServDesc1, GAO1ServDesc2, GAO1ServDesc3;

	@UiField
	Button G1add, add;

	public IspPanelView(String patientId) {
		initWidget(uiBinder.createAndBindUi(this));
		this.patientId.setInnerText(patientId);
	}
}
