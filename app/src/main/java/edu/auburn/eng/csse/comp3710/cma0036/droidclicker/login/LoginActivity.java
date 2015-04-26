package edu.auburn.eng.csse.comp3710.cma0036.droidclicker.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.Arrays;

import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.R;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.RegisterAccountActivity;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.drawer.navigation.NavigationMain;
import edu.auburn.eng.csse.comp3710.cma0036.droidclicker.models.user.User;

public class LoginActivity extends FragmentActivity {

	private UserLoginTask authTask = null;

	private String login;
	private String password;

	// UI references.
	private EditText edtLogin;
	private EditText edtPassword;
	private View loginFormView;
	private View loginStatusView;
	private BootstrapButton btnLogin;
	private  BootstrapButton btnRegister;

	Button.OnClickListener lstnLogin = new Button.OnClickListener() {
		@Override
		public void onClick(View view) {
			attemptLogin();
		}
	};

    Button.OnClickListener lstnRegister = new Button.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(),RegisterAccountActivity.class);
            startActivity(intent);
        }
    };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		edtLogin = (EditText) findViewById(R.id.login);
		edtPassword = (EditText) findViewById(R.id.password);

		loginFormView = findViewById(R.id.login_form);
		loginStatusView = findViewById(R.id.login_status);

		btnLogin = (BootstrapButton) findViewById(R.id.btnLogin);
		btnRegister = (BootstrapButton) findViewById(R.id.btnRegister);
		btnLogin.setOnClickListener(lstnLogin);
		btnRegister.setOnClickListener(lstnRegister);


	}

	public void attemptLogin() {
		if (authTask != null) {
			return;
		}

		// Reset errors.
		edtLogin.setError(null);
		edtPassword.setError(null);

		// Store values at the time of the login attempt.
		login = edtLogin.getText().toString();
		password = edtPassword.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(password)) {
			edtPassword.setError(getString(R.string.error_field_required));
			focusView = edtPassword;
			cancel = true;
		} else if (password.length() < 8) {
			edtPassword.setError(getString(R.string.error_invalid_password));
			focusView = edtPassword;
			cancel = true;
		}

		// Check for a valid login.
		if (TextUtils.isEmpty(login)) {
			edtLogin.setError(getString(R.string.error_field_required));
			focusView = edtLogin;
			cancel = true;
		} else if (login.length() < 6) {
			edtLogin.setError(getString(R.string.error_invalid_login));
			focusView = edtLogin;
			cancel = true;
		}
		
		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			showProgress(true);
			authTask = new UserLoginTask();
			authTask.execute((Void) null);
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

			loginStatusView.setVisibility(View.VISIBLE);
			loginStatusView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							loginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			loginFormView.setVisibility(View.VISIBLE);
			loginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							loginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			loginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_settings:
			// openSettings();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				User.getUserByLoginPassword(login,password);
			} catch (Exception e) {
				return false;
			}

			if (User.getId()== null || User.getId().isEmpty())return false;
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			authTask = null;
			showProgress(false);

			if (success) {
				Intent intent = new Intent(getApplicationContext(),NavigationMain.class);
				startActivity(intent);
				finish();
			} else {
				edtPassword.setText("");
				edtPassword.setError(getString(R.string.error_incorrect_combination));
				edtPassword.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			authTask = null;
			showProgress(false);
		}
	}
}
