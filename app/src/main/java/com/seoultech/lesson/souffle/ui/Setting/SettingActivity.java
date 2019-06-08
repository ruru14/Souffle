package com.seoultech.lesson.souffle.ui.Setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerLanguage;
    private Switch switchAutoLogin;
    private String[] languegeLayer;
    private User user;
    private AppController appController;
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        backPressCloseHandler = new BackPressCloseHandler(this);
        setContentView(R.layout.activity_setting);

        Intent autoLoginIntent = new Intent(this.getIntent());
        user = (User) autoLoginIntent.getSerializableExtra("user");

        appController = AppController.getInstance();

        spinnerLanguage = (Spinner)findViewById(R.id.spinner_language);
        switchAutoLogin = (Switch)findViewById(R.id.switch_autologin);
        switchAutoLogin.setChecked(appController.isAutoLogin());

        languegeLayer = new String[] {" ","한국어","English"};

        ArrayAdapter<String> adaptetLanguage = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languegeLayer);
        adaptetLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adaptetLanguage);

        spinnerLanguage.setOnItemSelectedListener((AdapterView.OnItemSelectedListener)this);

        switchAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    appController.setAutoLogin(true, user);
                else
                    appController.setAutoLogin(false, user);
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View v, int i, long l) {
        if(i == 1){
            appController.setLanguage("ko");
            Configuration config = new Configuration();
            String getLanguage = appController.getLanguage();
            config.locale = new Locale("ko");
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            Toast.makeText(getApplicationContext(),"한국어로 변경되었습니다",Toast.LENGTH_SHORT).show();
            Intent resetIntent = getIntent();
            finish();
            startActivity(resetIntent);
        }
        else if(i == 2){
            appController.setLanguage("en");
            Configuration config = new Configuration();
            String getLanguage = appController.getLanguage();
            config.locale = new Locale("en");
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            Toast.makeText(getApplicationContext(),"Translate to English",Toast.LENGTH_SHORT).show();
            Intent resetIntent = getIntent();
            finish();
            startActivity(resetIntent);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        TextView txt_setting_title = findViewById(R.id.txt_setting_title);
        txt_setting_title.setText(R.string.setting);
        TextView txt_language = findViewById(R.id.txt_language);
        txt_language.setText(R.string.language);
        TextView txt_autoLogin_in_setting = findViewById(R.id.txt_autologin_in_setting);
        txt_autoLogin_in_setting.setText(R.string.isAutoLogin);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();

    }

}
