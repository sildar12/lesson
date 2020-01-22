package com.example.testforliferay;

import android.os.Bundle;
import com.liferay.mobile.screens.auth.signup.SignUpListener;
import com.liferay.mobile.screens.auth.signup.SignUpScreenlet;
import com.liferay.mobile.screens.context.User;

/**
 * @author Javier Gamarra
 */
public class SignUpActivity extends ThemeActivity implements SignUpListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        SignUpScreenlet screenlet = findViewById(R.id.signup_screenlet);
        screenlet.setListener(this);
    }

    @Override
    public void onSignUpFailure(Exception e) {
        error(getString(R.string.signup_error), null);
    }

    @Override
    public void onSignUpSuccess(User user) {
        info(getString(R.string.sign_up_success_info) + " " + user.getId());
    }
}
