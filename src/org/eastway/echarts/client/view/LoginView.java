package org.eastway.echarts.client.view;

import org.eastway.echarts.client.presenter.LoginPresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.Timer;

public class LoginView extends Composite implements LoginPresenter.Display {
	private static LoginViewUiBinder uiBinder = GWT.create(LoginViewUiBinder.class);

	interface LoginViewUiBinder extends UiBinder<Widget, LoginView> {}

	interface LoginStyle extends CssResource {
		String inputerror();

		String error();
	}

	@UiField
	LoginStyle style;
	@UiField
	TextBox usernameTxtBox;
	@UiField
	Label username_err;
	@UiField
	PasswordTextBox passwordTxtBox;
	@UiField
	Label password_err;
	@UiField
	Button loginBtn;

	public LoginView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public boolean validateUsername() {
		// Check user name text field for user name
		if (usernameTxtBox.getText().isEmpty()
				|| usernameTxtBox.getText() == "null") {
			username_err.setText("You must enter a username");
			usernameTxtBox.addStyleName(style.inputerror());
			return false;
		} else {
			username_err.setText("");
			usernameTxtBox.removeStyleName(style.inputerror());
			return true;
		}
	}

	@Override
	public boolean validatePassword() {
		// Check password text field for password
		if (passwordTxtBox.getText().isEmpty()
				|| passwordTxtBox.getText() == "null") {
			password_err.setText("You must enter a password");
			passwordTxtBox.addStyleName(style.inputerror());
			return false;
		} else {
			password_err.setText("");
			passwordTxtBox.removeStyleName(style.inputerror());
			return true;
		}
	}

	@Override
	public void onLoad() {
		Timer focusTimer = new Timer() {
			@Override
			public void run() {
				usernameTxtBox.setFocus(true);
			}
		};
		focusTimer.schedule(1000);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public HasClickHandlers getLoginButton() {
		return loginBtn;
	}

	@Override
	public PasswordTextBox getPasswordTextBox() {
		return passwordTxtBox;
	}

	@Override
	public TextBox getUsernameTextBox() {
		return usernameTxtBox;
	}
}
