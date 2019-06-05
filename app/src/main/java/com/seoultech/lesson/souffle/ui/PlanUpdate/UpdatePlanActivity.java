package com.seoultech.lesson.souffle.ui.PlanUpdate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import com.seoultech.lesson.souffle.ui.adapter.ReservationListAdapter;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.adapter.ItemData;

import java.util.ArrayList;
import java.util.List;

public class UpdatePlanActivity extends AppCompatActivity implements View.OnClickListener{
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
    private int phoneNumber, peopleNumber, fromTime, toTime,  roomNumber;
    private String buildingName, objective, etc;
    private AppController appController;
    private ListView listViewReservation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        appController = AppController.getInstance();
        setContentView(R.layout.activity_update_plan);
        Intent updatePlanIntent = new Intent(this.getIntent());
        user = (User) updatePlanIntent.getSerializableExtra("user");
        ArrayList<ItemData> reservationList = new ArrayList<>();
        listViewReservation = (ListView)findViewById(R.id.listView_reservation);

        ProgressDialog progressDialogInAO = new ProgressDialog(UpdatePlanActivity.this);

        new AsyncTask<Integer, Integer, List<Reservation>>() {
            @Override
            protected void onPreExecute() {
                // Loading
                progressDialogInAO.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialogInAO.setMessage("Logining");
                progressDialogInAO.show();
                super.onPreExecute();
            }

            @Override
            protected List<Reservation> doInBackground(Integer... integers) {
                // 예약목록 불러옴
                System.out.println(integers[0]);
                return appController.readReservationByStudentNumber(integers[0]);
            }

            @Override
            protected void onPostExecute(List<Reservation> reservations) {
                // 예약목록 뿌리기
                System.out.println(reservations.get(0).toString());
                final ReservationListAdapter reservationListAdapter = new ReservationListAdapter(reservations);
                progressDialogInAO.dismiss();
                listViewReservation.setAdapter(reservationListAdapter);
            }
        }.execute(user.getStudentNumber());

        String reserve_date = rYear + "-" + rMonth + "-" + rDay;
        String fromTimeString = fromTime + ":00";
        String toTimeString = toTime + ":00";

        Reservation reservationObject = new Reservation(roomNumber, reserve_date, user.getStudentNumber(),
                user.getName(), objective, peopleNumber, fromTimeString, toTimeString, buildingName);

        btnInIt = (Button)findViewById(R.id.btnInIt);
        btnCheckTime = (Button)findViewById(R.id.btn_check_time);

        btnToMain = (Button)findViewById(R.id.btn_to_main_in_update_plan);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_update_plan);

        btnUserInfo = (Button)findViewById(R.id.btn_userInfo_in_update_plan);
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
