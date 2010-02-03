package org.eastway.echarts.client.presenter;

import org.eastway.echarts.client.HandleRpcException;
import org.eastway.echarts.client.PatientServicesAsync;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LoginPresenter extends EchartsPresenter<LoginPresenter.Display> {

	public interface Display extends EchartsDisplay {
		TextBox getUsernameTextBox();

		PasswordTextBox getPasswordTextBox();

		HasClickHandlers getLoginButton();

		boolean validateUsername();

		boolean validatePassword();
	}

	private PatientServicesAsync patientSvc;

	public LoginPresenter(Display display, HandlerManager eventBus, PatientServicesAsync patientSvc) {
		super(display, eventBus);
		this.patientSvc = patientSvc;
		bind();
	}

	private void bind() {
		display.getUsernameTextBox().addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {

			}
		});
		display.getUsernameTextBox().addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				display.validateUsername();
			}
		});
		display.getPasswordTextBox().addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {

			}
		});
		display.getPasswordTextBox().addBlurHandler(new BlurHandler() {
			@Override
			public void onBlur(BlurEvent event) {
				display.validatePassword();
			}
		});
		display.getLoginButton().addClickHandler(new ClickHandler() {
			// TODO: make this a separate function
			@Override
			public void onClick(ClickEvent event) {
				if (display.validateUsername() && display.validatePassword()) {
					AsyncCallback<String> callback = new AsyncCallback<String>() {
						@Override
						public void onFailure(Throwable caught) {
							new HandleRpcException(caught);
						}

						@Override
						public void onSuccess(String sessionId) {
							if (sessionId == null || sessionId == "null") {
								final DialogBox invalid = new DialogBox();
								VerticalPanel vPanel = new VerticalPanel();
								invalid.setText("ERROR");
								Button closeButton = new Button("Close",
										new ClickHandler() {
									public void onClick(ClickEvent event) {
										invalid.hide();
									}
								});
								vPanel.add(new HTML("Invalid username or password"));
								vPanel.add(closeButton);
								vPanel.setCellHorizontalAlignment(closeButton,
										HasHorizontalAlignment.ALIGN_RIGHT);
								vPanel.setSpacing(4);
								invalid.add(vPanel);
								// Show first,
								// then center,
								// Daniel son
								invalid.show();
								invalid.center();
								return;
							}
							// TODO: fireEvent here
							// to do this in
							// EchartsController
							// final long DURATION =
							// 3600000; // one hour
							// Date expires = new
							// Date(System.currentTimeMillis()
							// + DURATION);
							Cookies.setCookie("sessionId", sessionId, null, null,
									"/", false);
							Window.Location.reload();
						}
					};
					patientSvc.validateUser(display.getUsernameTextBox().getText(), display.getPasswordTextBox().getText(), callback);
				}
			}
		});
	}
}
