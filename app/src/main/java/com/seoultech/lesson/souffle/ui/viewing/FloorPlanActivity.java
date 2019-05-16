package com.seoultech.lesson.souffle.ui.viewing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.add_Plan.SelectRoomActivity;

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


//  해당 층의 도면 보여주는 액티비티

        btn_back = (Button) findViewById(R.id.btn_back);
        guide = (TextView) findViewById(R.id.txt_guide);
        Intent Reserve_intent = new Intent(this.getIntent());
        ImageView image_floor_plan = (ImageView) findViewById(R.id.image_floor_plan);

        floor_number = Reserve_intent.getExtras().getInt("floor_number");
        room_number = Reserve_intent.getExtras().getInt("room_number");


        switch (floor_number) {         //각 층의 도면보여주기
            case 0:
                image_floor_plan.setImageResource(R.drawable.floor_b1);
                guide.setText("B1층 강의실 도면");
                break;
            case 1:
                image_floor_plan.setImageResource(R.drawable.floor_1st);   //1층 도면
                guide.setText(floor_number + "층 강의실 도면");
                break;
            case 2:
                image_floor_plan.setImageResource(R.drawable.floor_2nd);  //2층 도면
                guide.setText(floor_number + "층 강의실 도면");
                break;
            case 3:
                image_floor_plan.setImageResource(R.drawable.floor_3rd);  //3층 도면
                guide.setText(floor_number + "층 강의실 도면");
                break;
            case 4:
                image_floor_plan.setImageResource(R.drawable.floor_4th);  //4층 도면
                guide.setText(floor_number + "층 강의실 도면");
                break;
            case 5:
                image_floor_plan.setImageResource(R.drawable.floor_5th);  //5층 도면
                guide.setText(floor_number + "층 강의실 도면");
                break;
        }


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent To_Floor_Select_intent = new Intent(getApplicationContext(), SelectRoomActivity.class);
                //startActivity(To_Floor_Select_intent);
                onBackPressed();
            }
        });
    };
}
