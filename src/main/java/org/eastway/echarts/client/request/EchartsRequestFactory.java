/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.client.request;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface EchartsRequestFactory extends RequestFactory {
	MessageRequest messageRequest();
	CodeRequest codeRequest();
	EhrRequest ehrRequest();
	DemographicsRequest demographicsRequest();
	AppointmentRequest appointmentRequest();
	UserRequest userRequest();
	DbServerConfigRequest dbServerConfigRequest();
	LinkRequest linkRequest();
	ARInfoRequest arinfoRequest();
	MedicationRequest medicationRequest();
	ProductivityRequest productivityRequest();
	AssignmentRequest assignmentRequest();
	ReferralRequest referralRequest();
	AddressRequest addressRequest();
	DiagnosisRequest diagnosisRequest();
	PatientRequest patientRequest();
	PlaceLogRecordRequest placeLogRecordRequest();
	AppointmentReportRequest appointmentReportRequest();
}
