package com.seoultech.lesson.souffle.ui.viewing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

public class FloorPlanActivity extends AppCompatActivity implements  View.OnClickListener{

    ImageView image_floor_plan;
    private BackPressCloseHandler backPressCloseHandler;
    int floor_number = -1;
    int bool_floor_plan = -1;
    int bool_room_plan = -1;
    TextView guide;
    Button btn_back;
    GridLayout grid_mirae_1st_floor, grid_mirae_2nd_floor, grid_mirae_3rd_floor, grid_mirae_4th_floor,
            grid_mirae_5th_floor, grid_mirae_b1th_floor;
    Button btn_109_room, btn_111_room, btn_113_room, btn_115_room, btn_209_room, btn_211_room, btn_213_room, btn_215_room,
            btn_301_room, btn_305_room, btn_309_room, btn_401_room, btn_403_room, btn_405_room, btn_501_room, btn_505_room, btn_509_room,
            btn_b101_room, btn_b105_room, btn_b109_room;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);

        backPressCloseHandler = new BackPressCloseHandler(this);

//  해당 층의 도면 보여주는 액티비티

        grid_mirae_1st_floor = (GridLayout)findViewById(R.id.grid_mirae_1st_floor);
        grid_mirae_2nd_floor = (GridLayout)findViewById(R.id.grid_mirae_2nd_floor);
        grid_mirae_3rd_floor = (GridLayout)findViewById(R.id.grid_mirae_3rd_floor);
        grid_mirae_4th_floor = (GridLayout)findViewById(R.id.grid_mirae_4th_floor);
        grid_mirae_5th_floor = (GridLayout)findViewById(R.id.grid_mirae_5th_floor);
        grid_mirae_b1th_floor = (GridLayout)findViewById(R.id.grid_mirae_b1th_floor);

        btn_back = (Button) findViewById(R.id.btn_back);
        guide = (TextView) findViewById(R.id.txt_guide);
        Intent Reserve_intent = new Intent(this.getIntent());
        ImageView image_floor_plan = (ImageView) findViewById(R.id.image_floor_plan);
        bool_floor_plan = Reserve_intent.getExtras().getInt("floor_plan");
        bool_room_plan = Reserve_intent.getExtras().getInt("room_plan");
        floor_number = Reserve_intent.getExtras().getInt("floor_number");
        int room_num_int = Reserve_intent.getExtras().getInt("room_num_int");

        btn_109_room = (Button)findViewById(R.id.btn_109_plan);
        btn_111_room = (Button)findViewById(R.id.btn_111_plan);
        btn_113_room = (Button)findViewById(R.id.btn_113_plan);
        btn_115_room = (Button)findViewById(R.id.btn_115_plan);

        btn_209_room = (Button)findViewById(R.id.btn_209_plan);
        btn_211_room = (Button)findViewById(R.id.btn_211_plan);
        btn_213_room = (Button)findViewById(R.id.btn_213_plan);
        btn_215_room = (Button)findViewById(R.id.btn_215_plan);

        btn_301_room = (Button)findViewById(R.id.btn_301_plan);
        btn_305_room = (Button)findViewById(R.id.btn_305_plan);
        btn_309_room = (Button)findViewById(R.id.btn_309_plan);

        btn_401_room = (Button)findViewById(R.id.btn_401_plan);
        btn_403_room = (Button)findViewById(R.id.btn_403_plan);
        btn_405_room = (Button)findViewById(R.id.btn_405_plan);

        btn_501_room = (Button)findViewById(R.id.btn_501_plan);
        btn_505_room = (Button)findViewById(R.id.btn_505_plan);
        btn_509_room = (Button)findViewById(R.id.btn_509_plan);

        btn_b101_room = (Button)findViewById(R.id.btn_b101_plan);
        btn_b105_room = (Button)findViewById(R.id.btn_b105_plan);
        btn_b109_room = (Button)findViewById(R.id.btn_b109_plan);


        btn_109_room.setOnClickListener(this);        btn_111_room.setOnClickListener(this);
        btn_113_room.setOnClickListener(this);        btn_115_room.setOnClickListener(this);

        btn_209_room.setOnClickListener(this);        btn_211_room.setOnClickListener(this);
        btn_213_room.setOnClickListener(this);        btn_215_room.setOnClickListener(this);

        btn_301_room.setOnClickListener(this);        btn_305_room.setOnClickListener(this);
        btn_309_room.setOnClickListener(this);

        btn_401_room.setOnClickListener(this);        btn_403_room.setOnClickListener(this);
        btn_405_room.setOnClickListener(this);

        btn_501_room.setOnClickListener(this);        btn_505_room.setOnClickListener(this);
        btn_509_room.setOnClickListener(this);

        btn_b101_room.setOnClickListener(this);       btn_b105_room.setOnClickListener(this);
        btn_b109_room.setOnClickListener(this);




        if(bool_floor_plan == 1) {
            switch (floor_number) {         //각 층의 도면보여주기
                case 0:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_b1);     //b1층 도면
                    guide.setText("B1층 강의실 도면");
                    grid_mirae_b1th_floor.setVisibility(View.VISIBLE);
                    grid_mirae_1st_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_2nd_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_3rd_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_4th_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_5th_floor.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_1st);   //1층 도면
                    guide.setText(floor_number + "층 강의실 도면");
                    grid_mirae_b1th_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_1st_floor.setVisibility(View.VISIBLE);
                    grid_mirae_2nd_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_3rd_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_4th_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_5th_floor.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_2nd);  //2층 도면
                    guide.setText(floor_number + "층 강의실 도면");
                    grid_mirae_b1th_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_1st_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_2nd_floor.setVisibility(View.VISIBLE);
                    grid_mirae_3rd_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_4th_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_5th_floor.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_3rd);  //3층 도면
                    guide.setText(floor_number + "층 강의실 도면");
                    grid_mirae_b1th_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_1st_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_2nd_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_3rd_floor.setVisibility(View.VISIBLE);
                    grid_mirae_4th_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_5th_floor.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_4th);  //4층 도면
                    guide.setText(floor_number + "층 강의실 도면");
                    grid_mirae_b1th_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_1st_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_2nd_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_3rd_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_4th_floor.setVisibility(View.VISIBLE);
                    grid_mirae_5th_floor.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_5th);  //5층 도면
                    guide.setText(floor_number + "층 강의실 도면");
                    grid_mirae_b1th_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_1st_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_2nd_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_3rd_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_4th_floor.setVisibility(View.INVISIBLE);
                    grid_mirae_5th_floor.setVisibility(View.VISIBLE);
                    break;
            }
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

    @Override
    public void onClick(View v){
        Intent intent_to_room_plan = new Intent(getApplicationContext(),RoomPlanActivity.class);
        switch(v.getId()) {
            case R.id.btn_109_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","109");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_111_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","111");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_113_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","113");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_115_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","115");
                startActivity(intent_to_room_plan);
                break;

            case R.id.btn_209_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","209");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_211_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","211");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_213_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","213");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_215_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","215");
                startActivity(intent_to_room_plan);
                break;

            case R.id.btn_301_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","301");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_305_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","305");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_309_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","309");
                startActivity(intent_to_room_plan);
                break;

            case R.id.btn_401_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","401");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_403_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","403");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_405_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","405");
                startActivity(intent_to_room_plan);
                break;

            case R.id.btn_501_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","501");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_505_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","505");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_509_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","509");
                startActivity(intent_to_room_plan);
                break;

            case R.id.btn_b101_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","b101");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_b105_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","b105");
                startActivity(intent_to_room_plan);
                break;
            case R.id.btn_b109_plan:
                intent_to_room_plan.putExtra("room_number_to_plan","b109");
                startActivity(intent_to_room_plan);
                break;
            }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

}
