package com.example.testforliferay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.liferay.mobile.screens.auth.login.LoginListener;
import com.liferay.mobile.screens.auth.login.LoginScreenlet;
import com.liferay.mobile.screens.context.SessionContext;
import com.liferay.mobile.screens.context.User;
import com.liferay.mobile.screens.context.storage.CredentialsStorageBuilder;
import com.liferay.mobile.screens.util.LiferayLogger;
import com.liferay.mobile.screens.viewsets.defaultviews.DefaultAnimation;

public class MainActivity extends ThemeActivity implements LoginListener, View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.DEBUG) {
            SessionContext.loadStoredCredentialsAndServer(CredentialsStorageBuilder.StorageType.SHARED_PREFERENCES);
            LiferayLogger.e("User already logged in: " + SessionContext.isLoggedIn());
        }

        //User
        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.sign_up).setOnClickListener(this);
        findViewById(R.id.forgot_password).setOnClickListener(this);
        findViewById(R.id.relogin).setOnClickListener(this);
        findViewById(R.id.user_portrait).setOnClickListener(this);
        findViewById(R.id.get_user).setOnClickListener(this);


        //LoginScreenlet loginScreenlet = (LoginScreenlet)findViewById(R.id.login);
        //loginScreenlet.setListener(this);
    }

    @Override
    public void onLoginSuccess(User user) {
        Toast.makeText(MainActivity.this,"Login URA!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure(Exception e) {

    }

    @Override
    public void onAuthenticationBrowserShown() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up:
                start(SignUpActivity.class);
                break;
            case R.id.login:
                start(LoginActivity.class);
                break;
        }

    }

    private void start(Class clasz) {
        DefaultAnimation.startActivityWithAnimation(this, getIntentWithTheme(clasz));
    }
}
