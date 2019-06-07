package com.seoultech.lesson.souffle.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Option;
import com.seoultech.lesson.souffle.data.model.User;

import butterknife.OnItemClick;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerLanguage;
    private Switch switchAutoLogin;
    private String[] languegeLayer;
    private User user;
    private AppController appController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_setting);

        Intent autoLoginIntent = new Intent(this.getIntent());
        user = (User) autoLoginIntent.getSerializableExtra("user");

        appController = AppController.getInstance();

        spinnerLanguage = (Spinner)findViewById(R.id.spinner_language);
        switchAutoLogin = (Switch)findViewById(R.id.switch_autologin);

        languegeLayer = new String[] {"선택하세요","한국어","English"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languegeLayer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);

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
            appController.setLanguage("한국어");
        }
        else if(i == 2){
            appController.setLanguage("english");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
