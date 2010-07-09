package org.eastway.echarts.server;


import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

public class ServerModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetAssignmentsHandler.class);
		bindHandler(GetDemographicsHandler.class);
		bindHandler(GetAppointmentsHandler.class);
		bindHandler(GetPatientSummaryHandler.class);
		bindHandler(GetReferralHandler.class);
		bindHandler(GetDiagnosesHandler.class);
		bindHandler(GetLinksHandler.class);
		bindHandler(GetMessagesHandler.class);
		bindHandler(SaveMessageHandler.class);
		bindHandler(GetAddressesHandler.class);
		bindHandler(GetContactsHandler.class);
		bindHandler(GetMedicationsHandler.class);
		bindHandler(GetProductivityHandler.class);
	}

}
