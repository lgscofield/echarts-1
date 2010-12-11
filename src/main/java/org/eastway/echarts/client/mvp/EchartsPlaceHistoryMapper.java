package org.eastway.echarts.client.mvp;

import org.eastway.echarts.client.place.ARInfoPlace;
import org.eastway.echarts.client.place.AddressPlace;
import org.eastway.echarts.client.place.AppointmentPlace;
import org.eastway.echarts.client.place.DemographicsPlace;
import org.eastway.echarts.client.place.DiagnosisPlace;
import org.eastway.echarts.client.place.EhrPlace;
import org.eastway.echarts.client.place.LinkPlace;
import org.eastway.echarts.client.place.MedicationPlace;
import org.eastway.echarts.client.place.MessagePlace;
import org.eastway.echarts.client.place.PatientSummaryPlace;
import org.eastway.echarts.client.place.ReferralPlace;
import org.eastway.echarts.client.place.ServiceHistoryPlace;
import org.eastway.echarts.client.place.TicklerPlace;
import org.eastway.echarts.client.place.TreatmentPlanPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ TicklerPlace.Tokenizer.class,
				  EhrPlace.Tokenizer.class,
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
				  ARInfoPlace.Tokenizer.class })
public interface EchartsPlaceHistoryMapper extends PlaceHistoryMapper {
}
