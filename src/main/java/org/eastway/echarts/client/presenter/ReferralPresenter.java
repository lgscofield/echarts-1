package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.ReferralServicesAsync;
import org.eastway.echarts.client.UserImpl;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Referral;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;

public class ReferralPresenter extends Presenter<ReferralPresenter.Display> {

	public interface Display extends EchartsDisplay, Referral { }

	private ReferralServicesAsync rpcServices;
	private EHR ehr;

	public ReferralPresenter(Display display, HandlerManager eventBus, ReferralServicesAsync rpcServices, EHR ehr) {
		super(display, eventBus);
		this.rpcServices = rpcServices;
		this.ehr = ehr;
	}

	@Override
	public void go(HasWidgets container) {
		container.clear();
		container.add(display.asWidget());
		fetchData();
	}

	private void fetchData() {
		AsyncCallback<Referral> callback = new AsyncCallback<Referral>() {
			@Override
			public void onFailure(Throwable caught) {
				new HandleRpcException(caught);
			}

			@Override
			public void onSuccess(Referral referral) {
				display.setUCI(referral.getUCI());
			}
		};
		rpcServices.findByEhr(ehr, UserImpl.getSessionId(), callback);
	}
}
