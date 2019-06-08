package com.seoultech.lesson.souffle.ui.planUpdate;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.Setting.SettingActivity;
import com.seoultech.lesson.souffle.ui.adapter.ReservationListAdapter;
import com.seoultech.lesson.souffle.ui.login.LoginActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.adapter.ItemData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchPlanActivity extends AppCompatActivity implements View.OnClickListener{
    public int rYear;
    public int rMonth;
    public int rDay;
    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private TextView btnToMain;
    private User user;
    private TextView btnUserInfo;
    private Button btnInIt;
    private Button btnCheckTime;
    private Button btnBackToMain;
    private BackPressCloseHandler backPressCloseHandler;
    private int phoneNumber, peopleNumber, fromTime, toTime,  roomNumber;
    private String buildingName, objective, etc;
    private AppController appController;
    private TextView btnSettings, btnLogout;
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
        setContentView(R.layout.activity_search_plan);
        Intent updatePlanIntent = new Intent(this.getIntent());
        user = (User) updatePlanIntent.getSerializableExtra("user");
        ArrayList<ItemData> reservationList = new ArrayList<>();
        listViewReservation = (ListView)findViewById(R.id.listView_reservation);

        ProgressDialog progressDialogInAO = new ProgressDialog(SearchPlanActivity.this);

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
                return appController.readReservationByStudentNumber(integers[0]);
            }

            @Override
            protected void onPostExecute(List<Reservation> reservations) {
                // 예약목록 뿌리기
                try{
                    final ReservationListAdapter reservationListAdapter = new ReservationListAdapter(reservations);
                    listViewReservation.setAdapter(reservationListAdapter);
                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    progressDialogInAO.dismiss();
                }
            }
        }.execute(user.getStudentNumber());

        String reserve_date = rYear + "-" + rMonth + "-" + rDay;
        String fromTimeString = fromTime + ":00";
        String toTimeString = toTime + ":00";

        Reservation reservationObject = new Reservation(roomNumber, reserve_date, user.getStudentNumber(),
                user.getName(), objective, peopleNumber, fromTimeString, toTimeString, buildingName);

        btnToMain = (TextView)findViewById(R.id.btn_to_main_in_search_plan);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_search_plan);
        btnSettings = (TextView)findViewById(R.id.btn_setting_in_search_plan);
        btnLogout = (TextView)findViewById(R.id.btn_logout_in_search_plan);

        btnUserInfo = (TextView)findViewById(R.id.btn_userInfo_in_search_plan);
        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_search_plan);
        frameSelectMenu = (FrameLayout)findViewById(R.id.search_plan_frame);
        frameSelectMenu.bringChildToFront(slideLayout);

        backPressCloseHandler = new BackPressCloseHandler(this);

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSettingIntent = new Intent(getApplicationContext(), SettingActivity.class);
                toSettingIntent.putExtra("user",user);
                startActivity(toSettingIntent);
                anim();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder logoutDlg = new AlertDialog.Builder(SearchPlanActivity.this);
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
            case R.id.fab_in_search_plan:
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
