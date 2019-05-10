package com.seoultech.lesson.souffle.ui.add_Plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.seoultech.lesson.souffle.R;

public class AddOptionActivity extends AppCompatActivity {

    EditText edit_name, edit_number, edit_time, edit_objective, edit_people_number;
    Button btn_back_to_time_reserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_option);

        Intent intent = new Intent(this.getIntent());

        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_number = (EditText)findViewById(R.id.edit_number);
        edit_time = (EditText)findViewById(R.id.edit_time);
        edit_objective = (EditText)findViewById(R.id.edit_object);
        edit_people_number = (EditText)findViewById(R.id.edit_people_number);
        btn_back_to_time_reserve = (Button)findViewById(R.id.btn_back_to_time_reserve);

        int year = intent.getExtras().getInt("reserve_year");
        int month = intent.getExtras().getInt("reserve_month");
        int day = intent.getExtras().getInt("reserve_day");
        int hour = intent.getExtras().getInt("reserve_hour");
        int minute = intent.getExtras().getInt("reserve_minute");

        edit_time.setText(year + "년 " + month + "월 " +
                day + "일\n" + hour + "시 " + minute + "분");

        btn_back_to_time_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOptionActivity.super.onBackPressed();
            }
        });


    }
}
