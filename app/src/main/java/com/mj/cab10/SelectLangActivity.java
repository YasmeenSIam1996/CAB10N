package com.mj.cab10;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;

import com.mj.cab10.util.Util;
import com.mj.cab10.adapter.LanguageAdapter;
import com.mj.cab10.model.Language;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SelectLangActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvLang;
    ImageView imgLang;

    View layoutChooseLang;

    Button btnLetsGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_lang);

        tvLang = findViewById(R.id.tvLang);
        imgLang = findViewById(R.id.imgLang);
        layoutChooseLang = findViewById(R.id.layoutChooseLang);
        btnLetsGo = findViewById(R.id.btnLetsGo);

        btnLetsGo.setOnClickListener(this);
        layoutChooseLang.setOnClickListener(this);

        if (getResources().getString(R.string.tvLanguage).equals("العربية")) {
            imgLang.setImageResource(R.drawable.flag_egp);
        } else {
            imgLang.setImageResource(R.drawable.flag_us);
        }
        tvLang.setText(getResources().getString(R.string.tvLanguage));

        //hide status bar
        Util.hideStatusBar(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.layoutChooseLang:
                showLangMenu(v);
                break;
            case R.id.btnLetsGo:
                startActivity(new Intent(this, SignInActivity.class));
                break;
        }
    }

    public void showLangMenu(View view) {

        List<Language> languageList = new ArrayList<>();

        languageList.add(new Language(getResources().getString(R.string.ar), R.drawable.flag_egp));
        languageList.add(new Language(getResources().getString(R.string.english), R.drawable.flag_us));

        LanguageAdapter languageAdapter = new LanguageAdapter(this, languageList);

        final ListPopupWindow listPopupWindow = new ListPopupWindow(SelectLangActivity.this);
        listPopupWindow.setAnchorView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            listPopupWindow.setDropDownGravity(Gravity.END);
        }

        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
        listPopupWindow.setWidth(600);
        listPopupWindow.setAdapter(languageAdapter);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    ((ApplicationController) getApplicationContext()).changeLang("ar");

                    listPopupWindow.dismiss();
                    startActivity(getIntent());
                    finish();
                } else {
                    ((ApplicationController) getApplicationContext()).changeLang("en");

                    listPopupWindow.dismiss();
                    startActivity(getIntent());
                    finish();

                }
            }
        });
        listPopupWindow.show();
    }
}
