package com.seoultech.lesson.souffle.ui.adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.seoultech.lesson.souffle.R;

public class ModifyInsertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_add_option);
    }


}
