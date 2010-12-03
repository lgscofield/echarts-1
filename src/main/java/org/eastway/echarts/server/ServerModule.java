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
package org.eastway.echarts.server;

import org.eastway.echarts.shared.GetARInfo;
import org.eastway.echarts.shared.GetAddresses;
import org.eastway.echarts.shared.GetContacts;
import org.eastway.echarts.shared.GetDbServerConfig;
import org.eastway.echarts.shared.GetDiagnoses;
import org.eastway.echarts.shared.GetLinks;
import org.eastway.echarts.shared.GetMedications;
import org.eastway.echarts.shared.GetProductivity;
import org.eastway.echarts.shared.GetProfile;
import org.eastway.echarts.shared.GetProfileViewData;
import org.eastway.echarts.shared.GetReferral;
import org.eastway.echarts.shared.GetTickler;
import org.eastway.echarts.shared.GetTicklerLite;
import org.eastway.echarts.shared.SaveProfile;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

public class ServerModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetTickler.class, GetTicklerHandler.class);
		bindHandler(GetReferral.class, GetReferralHandler.class);
		bindHandler(GetDiagnoses.class, GetDiagnosesHandler.class);
		bindHandler(GetLinks.class, GetLinksHandler.class);
		bindHandler(GetAddresses.class, GetAddressesHandler.class);
		bindHandler(GetContacts.class, GetContactsHandler.class);
		bindHandler(GetMedications.class, GetMedicationsHandler.class);
		bindHandler(GetProductivity.class, GetProductivityHandler.class);
		bindHandler(GetProfile.class, GetProfileHandler.class);
		bindHandler(SaveProfile.class, SaveProfileHandler.class);
		bindHandler(GetProfileViewData.class, GetProfileViewDataHandler.class);
		bindHandler(GetTicklerLite.class, GetTicklerLiteHandler.class);
		bindHandler(GetARInfo.class, GetARInfoHandler.class);
		bindHandler(GetDbServerConfig.class, GetDbServerConfigHandler.class);
	}

}
