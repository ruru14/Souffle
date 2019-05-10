package com.seoultech.lesson.souffle.ui.add_Plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.add_Plan.TimeReserveActivity;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;

import java.sql.Time;

public class ReserveContentsActivity extends AppCompatActivity {

    public int rHour;
    public int rMinute;
    public int rYear;
    public int rMonth;
    public int rDay;
    Button btn_back_to_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_contents);

        Intent intent = new Intent(this.getIntent());

        btn_back_to_main = (Button)findViewById(R.id.btn_back_main);


        btn_back_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_main_menu = new Intent(getApplicationContext(), SelectMenuActivity.class);
                startActivity(to_main_menu);
            }
        });


    }

}
