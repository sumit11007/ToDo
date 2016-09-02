package com.appsbee.www.todolist.Login;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.appsbee.www.todolist.R;

/**
 * Created by root on 9/2/16.
 */
public class LoginPresenter {

    private ILogin mILogin;
    private UserLoginTask mAuthTask = null;


    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "abc@abc.com:abc123", "abc@abc.com:abc123"
    };

    public LoginPresenter(ILogin mILogin) {
        this.mILogin = mILogin;
    }

    public void onCreate() {
    }

    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mILogin.setEmailError(null);
        mILogin.setPsdError(null);
        // Store values at the time of the login attempt.
        boolean cancel = false;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(mILogin.getEmail()) && !isPasswordValid(mILogin.getPassword())) {
            mILogin.setEmailError(mILogin.getContext().getString(R.string.error_invalid_password));
            mILogin.setFocusPsdView();
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(mILogin.getEmail())) {
            mILogin.setEmailError(mILogin.getContext().getString(R.string.error_field_required));
            mILogin.setFocusEmailView();
            cancel = true;
        } else if (!isEmailValid(mILogin.getEmail())) {
            mILogin.setEmailError(mILogin.getContext().getString(R.string.error_invalid_email));
            mILogin.setFocusEmailView();
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            mILogin.focusView();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            mILogin.showLoading("Loading..");
            mAuthTask = new UserLoginTask(mILogin.getEmail(), mILogin.getPassword());
            mAuthTask.execute((Void) null);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;
        private boolean flag = false;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return flag;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                System.out.println("!!!!!" + pieces[0] + "  " + pieces[1] + "===========" + mEmail + "   " + mPassword);
                if (pieces[0].equals(mEmail)) {
                    flag = pieces[1].equals(mPassword);
                    mILogin.startTodoActivity();
                    return flag;
                } else {
                    flag = false;
                    return flag;
                }
            }
            return flag;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            mILogin.showLoading("Loading..");

            if (success) {
                mILogin.hideLoading();
                mILogin.showToastMessage("Success");
            } else {
                mILogin.showToastMessage("failed");
                mILogin.hideLoading();
                mILogin.setEmailError(mILogin.getContext().getString(R.string.error_incorrect_password));
                mILogin.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            mILogin.hideLoading();
        }
    }


    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

}
