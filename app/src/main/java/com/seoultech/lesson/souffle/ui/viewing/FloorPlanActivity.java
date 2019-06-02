package com.seoultech.lesson.souffle.ui.viewing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

public class FloorPlanActivity extends AppCompatActivity implements  View.OnClickListener{

    private ImageView imageFloorPlan;
    private BackPressCloseHandler backPressCloseHandler;
    private int floorNumber = -1;
    private int boolFloorPlan = -1;
    private int boolRoomPlan = -1;
    private TextView guide;
    private Button btnBack;
    private GridLayout gridMiraeFirstFloor, gridMiraeSecondFloor, gridMiraeThirdFloor, gridMiraeFourthFloor,
            gridMiraeFifthFloor, gridMiraeB1thFloor;
    private Button btn109Room, btn111Room, btn113Room, btn115Room, btn209Room, btn211Room, btn213Room, btn215Room,
            btn301Room, btn305Room, btn309Room, btn401Room, btn403Room, btn405Room, btn501Room, btn505Room, btn509Room,
            btnB101Room, btnB105Room, btnB109Room;

    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private Button btnToMain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_floor_plan);

        backPressCloseHandler = new BackPressCloseHandler(this);

        btnToMain = (Button)findViewById(R.id.btn_to_main_in_floor_plan);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_floor_plan);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_floor_plan);
        frameSelectMenu = (FrameLayout)findViewById(R.id.floor_plan_frame);
        frameSelectMenu.bringChildToFront(slideLayout);

//  해당 층의 도면 보여주는 액티비티

        gridMiraeFirstFloor = (GridLayout)findViewById(R.id.grid_mirae_1st_floor);
        gridMiraeSecondFloor = (GridLayout)findViewById(R.id.grid_mirae_2nd_floor);
        gridMiraeThirdFloor = (GridLayout)findViewById(R.id.grid_mirae_3rd_floor);
        gridMiraeFourthFloor = (GridLayout)findViewById(R.id.grid_mirae_4th_floor);
        gridMiraeFifthFloor = (GridLayout)findViewById(R.id.grid_mirae_5th_floor);
        gridMiraeB1thFloor = (GridLayout)findViewById(R.id.grid_mirae_b1th_floor);

        btnBack = (Button) findViewById(R.id.btn_back);
        guide = (TextView) findViewById(R.id.txt_guide);
        Intent Reserve_intent = new Intent(this.getIntent());
        ImageView image_floor_plan = (ImageView) findViewById(R.id.image_floor_plan);
        boolFloorPlan = Reserve_intent.getExtras().getInt("floor_plan");
        boolRoomPlan = Reserve_intent.getExtras().getInt("room_plan");
        floorNumber = Reserve_intent.getExtras().getInt("floorNumber");
        int room_num_int = Reserve_intent.getExtras().getInt("room_num_int");

        btn109Room = (Button)findViewById(R.id.btn_109_plan);
        btn111Room = (Button)findViewById(R.id.btn_111_plan);
        btn113Room = (Button)findViewById(R.id.btn_113_plan);
        btn115Room = (Button)findViewById(R.id.btn_115_plan);

        btn209Room = (Button)findViewById(R.id.btn_209_plan);
        btn211Room = (Button)findViewById(R.id.btn_211_plan);
        btn213Room = (Button)findViewById(R.id.btn_213_plan);
        btn215Room = (Button)findViewById(R.id.btn_215_plan);

        btn301Room = (Button)findViewById(R.id.btn_301_plan);
        btn305Room = (Button)findViewById(R.id.btn_305_plan);
        btn309Room = (Button)findViewById(R.id.btn_309_plan);

        btn401Room = (Button)findViewById(R.id.btn_401_plan);
        btn403Room = (Button)findViewById(R.id.btn_403_plan);
        btn405Room = (Button)findViewById(R.id.btn_405_plan);

        btn501Room = (Button)findViewById(R.id.btn_501_plan);
        btn505Room = (Button)findViewById(R.id.btn_505_plan);
        btn509Room = (Button)findViewById(R.id.btn_509_plan);

        btnB101Room = (Button)findViewById(R.id.btn_b101_plan);
        btnB105Room = (Button)findViewById(R.id.btn_b105_plan);
        btnB109Room = (Button)findViewById(R.id.btn_b109_plan);


        btn109Room.setOnClickListener(this);        btn111Room.setOnClickListener(this);
        btn113Room.setOnClickListener(this);        btn115Room.setOnClickListener(this);

        btn209Room.setOnClickListener(this);        btn211Room.setOnClickListener(this);
        btn213Room.setOnClickListener(this);        btn215Room.setOnClickListener(this);

        btn301Room.setOnClickListener(this);        btn305Room.setOnClickListener(this);
        btn309Room.setOnClickListener(this);

        btn401Room.setOnClickListener(this);        btn403Room.setOnClickListener(this);
        btn405Room.setOnClickListener(this);

        btn501Room.setOnClickListener(this);        btn505Room.setOnClickListener(this);
        btn509Room.setOnClickListener(this);

        btnB101Room.setOnClickListener(this);       btnB105Room.setOnClickListener(this);
        btnB109Room.setOnClickListener(this);




        if(boolFloorPlan == 1) {
            switch (floorNumber) {         //각 층의 도면보여주기
                case 0:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_b1);     //b1층 도면
                    guide.setText("B1층 강의실 도면");
                    gridMiraeB1thFloor.setVisibility(View.VISIBLE);
                    gridMiraeFirstFloor.setVisibility(View.INVISIBLE);
                    gridMiraeSecondFloor.setVisibility(View.INVISIBLE);
                    gridMiraeThirdFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFourthFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFifthFloor.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_1st);   //1층 도면
                    guide.setText(floorNumber + "층 강의실 도면");
                    gridMiraeB1thFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFirstFloor.setVisibility(View.VISIBLE);
                    gridMiraeSecondFloor.setVisibility(View.INVISIBLE);
                    gridMiraeThirdFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFourthFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFifthFloor.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_2nd);  //2층 도면
                    guide.setText(floorNumber + "층 강의실 도면");
                    gridMiraeB1thFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFirstFloor.setVisibility(View.INVISIBLE);
                    gridMiraeSecondFloor.setVisibility(View.VISIBLE);
                    gridMiraeThirdFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFourthFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFifthFloor.setVisibility(View.INVISIBLE);
                    break;
                case 3:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_3rd);  //3층 도면
                    guide.setText(floorNumber + "층 강의실 도면");
                    gridMiraeB1thFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFirstFloor.setVisibility(View.INVISIBLE);
                    gridMiraeSecondFloor.setVisibility(View.INVISIBLE);
                    gridMiraeThirdFloor.setVisibility(View.VISIBLE);
                    gridMiraeFourthFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFifthFloor.setVisibility(View.INVISIBLE);
                    break;
                case 4:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_4th);  //4층 도면
                    guide.setText(floorNumber + "층 강의실 도면");
                    gridMiraeB1thFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFirstFloor.setVisibility(View.INVISIBLE);
                    gridMiraeSecondFloor.setVisibility(View.INVISIBLE);
                    gridMiraeThirdFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFourthFloor.setVisibility(View.VISIBLE);
                    gridMiraeFifthFloor.setVisibility(View.INVISIBLE);
                    break;
                case 5:
                    image_floor_plan.setImageResource(R.drawable.floor_mirae_5th);  //5층 도면
                    guide.setText(floorNumber + "층 강의실 도면");
                    gridMiraeB1thFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFirstFloor.setVisibility(View.INVISIBLE);
                    gridMiraeSecondFloor.setVisibility(View.INVISIBLE);
                    gridMiraeThirdFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFourthFloor.setVisibility(View.INVISIBLE);
                    gridMiraeFifthFloor.setVisibility(View.VISIBLE);
                    break;
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent To_Floor_Select_intent = new Intent(getApplicationContext(), SelectRoomActivity.class);
                //startActivity(To_Floor_Select_intent);
                onBackPressed();
            }
        });

        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_main_intent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                startActivity(to_main_intent);
            }
        });

        fabMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anim();
            }
        });
    }
    //onCreate End

    public void anim() {
        if (isFabOpen) {
            slideLayout.startAnimation(pushToRight);
            slideLayout.setVisibility(View.INVISIBLE);
            isFabOpen = false;
        } else {
            slideLayout.startAnimation(pullFromRight);
            slideLayout.setVisibility(View.VISIBLE);
            isFabOpen = true;
        }
    }

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
