package com.seoultech.lesson.souffle.ui.viewing;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
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
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.Setting.SettingActivity;
import com.seoultech.lesson.souffle.ui.login.LoginActivity;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

import org.w3c.dom.Text;

import java.util.Locale;

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
            btn301Room, btn305Room, btn309Room, btn401Room, btn405Room, btn409Room, btn501Room, btn505Room, btn509Room,
            btnB101Room, btnB105Room, btnB109Room;

    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private TextView btnToMain;
    private TextView btnUserInfo;
    private TextView btnSettings;
    private TextView btnLogout;
    private User user;
    private AppController appController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        appController = AppController.getInstance();
        appController.init(this);

        if(appController.getLanguage().equals("ko")){
            Locale locale = new Locale("ko");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
        else{
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }

        setContentView(R.layout.activity_floor_plan);
        Intent FloorPlanIntent = new Intent(this.getIntent());
        user = (User) FloorPlanIntent.getSerializableExtra("user");
        btnUserInfo = (TextView)findViewById(R.id.btn_userInfo_in_floor_plan);
        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        backPressCloseHandler = new BackPressCloseHandler(this);

        btnToMain = (TextView)findViewById(R.id.btn_to_main_in_floor_plan);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_floor_plan);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_floor_plan);
        frameSelectMenu = (FrameLayout)findViewById(R.id.floor_plan_frame);
        frameSelectMenu.bringChildToFront(slideLayout);
        btnSettings = findViewById(R.id.btn_setting_in_floor_plan);
        btnLogout = findViewById(R.id.btn_logout_in_floor_plan);

//  해당 층의 도면 보여주는 액티비티

        gridMiraeFirstFloor = (GridLayout)findViewById(R.id.grid_mirae_1st_floor);
        gridMiraeSecondFloor = (GridLayout)findViewById(R.id.grid_mirae_2nd_floor);
        gridMiraeThirdFloor = (GridLayout)findViewById(R.id.grid_mirae_3rd_floor);
        gridMiraeFourthFloor = (GridLayout)findViewById(R.id.grid_mirae_4th_floor);
        gridMiraeFifthFloor = (GridLayout)findViewById(R.id.grid_mirae_5th_floor);
        gridMiraeB1thFloor = (GridLayout)findViewById(R.id.grid_mirae_b1th_floor);

        guide = (TextView) findViewById(R.id.txt_guide);

        ImageView image_floor_plan = (ImageView) findViewById(R.id.image_floor_plan);
        boolFloorPlan = FloorPlanIntent.getExtras().getInt("floor_plan");
        boolRoomPlan = FloorPlanIntent.getExtras().getInt("room_plan");
        floorNumber = FloorPlanIntent.getExtras().getInt("floor_number");
        int room_num_int = FloorPlanIntent.getExtras().getInt("room_num_int");

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
        btn405Room = (Button)findViewById(R.id.btn_405_plan);
        btn409Room = (Button)findViewById(R.id.btn_409_plan);

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

        btn401Room.setOnClickListener(this);        btn405Room.setOnClickListener(this);
        btn409Room.setOnClickListener(this);

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

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder logoutDlg = new AlertDialog.Builder(FloorPlanActivity.this);
                logoutDlg.setTitle("로그아웃");
                logoutDlg.setMessage("정말 로그아웃 하시겠습니까?");
                logoutDlg.setNegativeButton("취소",null);
                logoutDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent toLoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(toLoginIntent);
                        finishActivity(0);
                    }
                });
                logoutDlg.show();
            }
        });
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSettingIntent = new Intent(getApplicationContext(), SettingActivity.class);
                toSettingIntent.putExtra("user",user);
                startActivity(toSettingIntent);
            }
        });

        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                toMainMenuIntent.putExtra("user",user);
                startActivity(toMainMenuIntent);
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

    @Override
    protected void onResume() {
        super.onResume();
        btn109Room.setText(R.string.room_109);
        btn111Room.setText(R.string.room_111);
        btn113Room.setText(R.string.room_113);
        btn115Room.setText(R.string.room_115);

        btn209Room.setText(R.string.room_209);
        btn211Room.setText(R.string.room_211);
        btn213Room.setText(R.string.room_213);
        btn215Room.setText(R.string.room_215);

        btn301Room.setText(R.string.room_301);
        btn305Room.setText(R.string.room_305);
        btn309Room.setText(R.string.room_309);

        btn401Room.setText(R.string.room_401);
        btn405Room.setText(R.string.room_405);
        btn409Room.setText(R.string.room_409);

        btn501Room.setText(R.string.room_501);
        btn505Room.setText(R.string.room_505);
        btn509Room.setText(R.string.room_509);

        btnB101Room.setText(R.string.room_b101);
        btnB105Room.setText(R.string.room_b105);
        btnB109Room.setText(R.string.room_b109);
    }

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
        Intent toRoomPlanIntent = new Intent(getApplicationContext(),RoomPlanActivity.class);
        switch(v.getId()) {
            case R.id.btn_109_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","109");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_111_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","111");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_113_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","113");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_115_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","115");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;

            case R.id.btn_209_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","209");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_211_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","211");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_213_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","213");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_215_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","215");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;

            case R.id.btn_301_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","301");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_305_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","305");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_309_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","309");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;

            case R.id.btn_401_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","401");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_405_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","405");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_409_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","409");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;

            case R.id.btn_501_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","501");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_505_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","505");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_509_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","509");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;

            case R.id.btn_b101_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","B101");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_b105_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","B105");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            case R.id.btn_b109_plan:
                toRoomPlanIntent.putExtra("room_number_to_plan","B109");
                toRoomPlanIntent.putExtra("user",user);
                startActivity(toRoomPlanIntent);
                break;
            }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

}
