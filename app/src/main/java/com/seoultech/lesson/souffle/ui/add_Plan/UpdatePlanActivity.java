package com.seoultech.lesson.souffle.ui.add_Plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;

import java.util.List;

public class UpdatePlanActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView listView = null;
    public int rYear;
    public int rMonth;
    public int rDay;
    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private Button btnToMain;
    private User user;
    private Button btnUserInfo, btnInIt, btnCheckTime;
    Button btnBackToMain;
    private BackPressCloseHandler backPressCloseHandler;
    private ListView listViewTime;
    private int phoneNumber, peopleNumber, fromTime, toTime,  roomNumber;
    private String buildingName, objective, etc;
    private List<Reservation> reservationsList;
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
        reservationsList = appController.readReservationByStudentNumber(1234);
        appController.deleteReservation(reservationsList.get(1));
        setContentView(R.layout.activity_update_plan);
        Intent updatePlanIntent = new Intent(this.getIntent());
        user = (User) updatePlanIntent.getSerializableExtra("user");

        rYear = updatePlanIntent.getExtras().getInt("reserve_year");
        rMonth = updatePlanIntent.getExtras().getInt("reserve_month");
        rDay = updatePlanIntent.getExtras().getInt("reserve_day");
        phoneNumber = updatePlanIntent.getExtras().getInt("phone_number");
        peopleNumber = updatePlanIntent.getExtras().getInt("people_number");
        fromTime = updatePlanIntent.getExtras().getInt("fromTime");
        toTime = updatePlanIntent.getExtras().getInt("toTime");
        roomNumber = updatePlanIntent.getExtras().getInt("room_num");
        buildingName = updatePlanIntent.getExtras().getString("building_name");
        objective = updatePlanIntent.getExtras().getString("objective");
        etc = updatePlanIntent.getExtras().getString("etc");

        String reserve_date = rYear + "-" + rMonth + "-" + rDay;
        String fromTimeString = fromTime + ":00";
        String toTimeString = toTime + ":00";

        Reservation reservationObject = new Reservation(roomNumber, reserve_date, user.getStudentNumber(),
                user.getName(), objective, peopleNumber, fromTimeString, toTimeString, buildingName);

        System.out.println(reservationObject.toString());

        listViewTime = (ListView)findViewById(R.id.listView_time);
        btnInIt = (Button)findViewById(R.id.btnInIt);
        btnCheckTime = (Button)findViewById(R.id.btn_check_time);

        btnToMain = (Button)findViewById(R.id.btn_to_main_in_update_plan);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_update_plan);

        btnUserInfo = (Button)findViewById(R.id.btn_userInfo_in_updateplan);
        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_update_plan);
        frameSelectMenu = (FrameLayout)findViewById(R.id.update_plan_frame);
        frameSelectMenu.bringChildToFront(slideLayout);

        backPressCloseHandler = new BackPressCloseHandler(this);



        btnBackToMain = (Button)findViewById(R.id.btn_back_main);

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                toMainMenuIntent.putExtra("user",user);
                startActivity(toMainMenuIntent);
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

        fabMenu.setOnClickListener(this);


    }
    //OnCreate End
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_in_update_plan:
                anim();
                break;
        }
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
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }
}
