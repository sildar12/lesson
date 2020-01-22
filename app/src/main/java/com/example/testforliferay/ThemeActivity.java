package com.example.testforliferay;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import com.liferay.mobile.screens.util.LiferayLogger;

/**
 * @author Javier Gamarra
 */
public abstract class ThemeActivity extends AppCompatActivity {

    private final int[] themes =
            { R.style.lexicon_theme, R.style.default_theme, R.style.material_theme, R.style.westeros_theme };
    private final int[] colors = {
            R.color.colorPrimary_lexicon, R.color.colorPrimary_default, R.color.colorPrimary_material,
            R.color.colorPrimary_westeros
    };
    private Integer currentThemePosition;
    private View content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentThemePosition = getIntent().getIntExtra("currentThemePosition", 0);
        setTheme(getCurrentTheme());
    }

    @Override
    protected void onResume() {
        super.onResume();

        content = findViewById(android.R.id.content);
    }

    protected void changeToNextTheme() {
        currentThemePosition = (currentThemePosition + 1) % themes.length;
    }

    protected void error(String message, Exception e) {
        showSnackbarWithColor(message, ContextCompat.getColor(this, R.color.red_default));
        LiferayLogger.e("Error ", e);
    }

    protected void info(String message) {
        int color = colors[currentThemePosition];
        showSnackbarWithColor(message, ContextCompat.getColor(this, color));
    }

    protected Intent getIntentWithTheme(Class destinationClass) {
        Intent intent = new Intent(this, destinationClass);
        intent.putExtra("currentThemePosition", currentThemePosition);
        return intent;
    }

    private int getCurrentTheme() {
        return themes[currentThemePosition];
    }

    private void showSnackbarWithColor(String message, int color) {
        Snackbar snackbar = Snackbar.make(content, message, Snackbar.LENGTH_SHORT);
        ViewGroup group = (ViewGroup) snackbar.getView();
        group.setBackgroundColor(color);
        snackbar.show();
    }
}
