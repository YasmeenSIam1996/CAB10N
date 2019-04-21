package com.mj.cab10;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.view.View;

import java.util.Locale;

public class App extends Application {

    private Locale locale = null;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        locale = new Locale(preferences.getString("lang", ""));

        if (locale != null) {
            Locale.setDefault(locale);
            Configuration config = new Configuration(newConfig);
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String lang = settings.getString("lang", "");
        changeLang(this, lang);

    }

    public void changeLang(Context context, String lang) {
        Configuration config = context.getResources().getConfiguration();
        if (!"".equals(lang) && !config.locale.getLanguage().equals(lang)) {

            SharedPreferences.Editor ed = PreferenceManager.getDefaultSharedPreferences(this).edit();
            ed.putString("lang", lang);
            ed.apply();

            locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration conf = new Configuration(config);
            conf.locale = locale;
            context.getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());

        }
    }

    public String getLang() {
        return PreferenceManager.getDefaultSharedPreferences(this).getString("lang", "");
    }
}