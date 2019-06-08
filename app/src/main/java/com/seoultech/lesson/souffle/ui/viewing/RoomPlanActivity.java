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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.Setting.SettingActivity;
import com.seoultech.lesson.souffle.ui.login.LoginActivity;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

import java.util.Locale;

public class RoomPlanActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBackToRoomSelect;
    TextView txtRoomGuide;
    private BackPressCloseHandler backPressCloseHandler;

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

        setContentView(R.layout.activity_room_plan);
        Intent roomPlanIntent = new Intent(this.getIntent());
        user = (User) roomPlanIntent.getSerializableExtra("user");
        btnUserInfo = (TextView)findViewById(R.id.btn_userInfo_in_room_plan);
        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        backPressCloseHandler = new BackPressCloseHandler(this);
//
        btnToMain = (TextView)findViewById(R.id.btn_to_main_in_room_plan);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_room_plan);
        btnSettings = (TextView)findViewById(R.id.btn_setting_in_room_plan);
        btnLogout = (TextView)findViewById(R.id.btn_logout_in_room_plan);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_room_plan);
        frameSelectMenu = (FrameLayout)findViewById(R.id.room_plan_frame);
        frameSelectMenu.bringChildToFront(slideLayout);
//
        txtRoomGuide = (TextView)findViewById(R.id.txt_room_guide);

    txtRoomGuide.setText(roomPlanIntent.getExtras().getString("room_number_to_plan") + "호 강의실 사진");

        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                toMainMenuIntent.putExtra("user",user);
                startActivity(toMainMenuIntent);
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

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder logoutDlg = new AlertDialog.Builder(RoomPlanActivity.this);
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

        fabMenu.setOnClickListener(this);
    }
    //Oncreate End

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_in_room_plan:
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
