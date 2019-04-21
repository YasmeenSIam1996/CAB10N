package com.mj.cab10;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Locale;

public class ApplicationController extends Application {

    private static ApplicationController mInstance;
    private static Context context;
    private Locale myLocale;
    public static int langNum = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        mInstance = this;

//       VolleySingleton.getInstance();
//       FirebaseApp.initializeApp(this);

        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String lang = prefs.getString("Language", "ar");
        changeLang(lang);

    }

    public static synchronized ApplicationController getInstance() {
        if (mInstance == null)
            return mInstance = new ApplicationController();
        else
            return mInstance;
    }


    public static Context getAppContext() {
        return ApplicationController.context;
    }


    public void loadLocale() {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        String language = prefs.getString(langPref, "ar");
        changeLang(language);
    }

    public void changeLang(String lang) {

        Log.e("changeLang","changeLang");

        if (lang.equalsIgnoreCase(""))
            return;

        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        if (lang.equals("ar")) {
            langNum = 1;
        } else {
            langNum = 0;
        }
    }

    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.apply();
    }
}
