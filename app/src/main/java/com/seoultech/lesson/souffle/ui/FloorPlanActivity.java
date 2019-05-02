package com.seoultech.lesson.souffle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;

//  해당 층의 도면 보여주는 액티비티

public class FloorPlanActivity extends AppCompatActivity {

    ImageView image_floor_plan;
    int room_number = 0;
    int floor_number = 0;
    TextView guide;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);


        btn_back = (Button)findViewById(R.id.btn_back);
        guide = (TextView)findViewById(R.id.txt_guide);
        Intent Reserve_intent = new Intent(this.getIntent());
        ImageView image_floor_plan = (ImageView)findViewById(R.id.image_floor_plan);

        floor_number = Reserve_intent.getExtras().getInt("floor_number");
        room_number = Reserve_intent.getExtras().getInt("room_number");

        if(floor_number == 0) {
            switch (room_number) {
                case 111:
                    guide.setText(room_number + "호 강의실 도면");
                    break;
                case 113:
                    guide.setText(room_number + "호 강의실 도면");
                    break;
                case 115:
                    guide.setText(room_number + "호 강의실 도면");
                    break;
            }
        }


        else {
            switch (floor_number) {                                       //층의 도면보여주기
                case 1:
                    image_floor_plan.setImageResource(R.drawable.test_plan);   //1층 도면
                    guide.setText(floor_number + "층 강의실 도면");
                    break;
                case 2:
                    image_floor_plan.setImageResource(R.drawable.kko);  //2층 도면
                    guide.setText(floor_number + "층 강의실 도면");
                    break;
                case 3:
                    image_floor_plan.setImageResource(R.drawable.lee);  //3층 도면
                    guide.setText(floor_number + "층 강의실 도면");
                    break;

            }
        }



        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent To_Floor_Select_intent = new Intent(getApplicationContext(), SelectRoomActivity.class);
                startActivity(To_Floor_Select_intent);
            }
        });
/*
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent To_Plan_Time_intent = new Intent(getApplicationContext(),TimeReserveActivity.class);
                startActivity(To_Plan_Time_intent);
            }
        });*/

    }
}
