package org.eastway.echarts.client.activity;

import org.eastway.echarts.client.place.ARInfoPlace;
import org.eastway.echarts.client.place.AddressPlace;
import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.place.AppointmentReportListPlace;
import org.eastway.echarts.client.place.DashboardFramePlace;
import org.eastway.echarts.client.place.DashboardPlace;
import org.eastway.echarts.client.place.DemographicsPlace;
import org.eastway.echarts.client.place.DiagnosisPlace;
import org.eastway.echarts.client.place.LabPlace;
import org.eastway.echarts.client.place.LinkPlace;
import org.eastway.echarts.client.place.MedicationPlace;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.place.EhrQueryListPlace;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.PhysicianOrderPlace;
import org.eastway.echarts.client.place.PlaceLogRecordListPlace;
import org.eastway.echarts.client.place.PrimaryCarePlace;
import org.eastway.echarts.client.place.ProfilePlace;
import org.eastway.echarts.client.place.ReferralPlace;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.place.StaffAnalysisPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.place.TreatmentPlanPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ TicklerPlace.Tokenizer.class,
				  PatientSummaryPlace.Tokenizer.class,
				  MessagePlace.Tokenizer.class,
				  DemographicsPlace.Tokenizer.class,
				  ReferralPlace.Tokenizer.class,
				  AppointmentPlace.Tokenizer.class,
				  DiagnosisPlace.Tokenizer.class,
				  LinkPlace.Tokenizer.class,
				  AddressPlace.Tokenizer.class,
				  MedicationPlace.Tokenizer.class,
				  TreatmentPlanPlace.Tokenizer.class,
				  ServiceHistoryPlace.Tokenizer.class,
				  ARInfoPlace.Tokenizer.class,
				  DashboardPlace.Tokenizer.class,
				  ProfilePlace.Tokenizer.class,
				  LabPlace.Tokenizer.class,
				  PhysicianOrderPlace.Tokenizer.class,
				  StaffAnalysisPlace.Tokenizer.class,
				  DashboardFramePlace.Tokenizer.class,
				  AppointmentReportListPlace.Tokenizer.class,
				  EhrQueryListPlace.Tokenizer.class,
				  PlaceLogRecordListPlace.Tokenizer.class,
				  PrimaryCarePlace.Tokenizer.class})
public interface EchartsPlaceHistoryMapper extends PlaceHistoryMapper {
}
