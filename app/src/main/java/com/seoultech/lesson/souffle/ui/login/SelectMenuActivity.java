package com.seoultech.lesson.souffle.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.PlanUpdate.DeletePlanActivity;
import com.seoultech.lesson.souffle.ui.PlanUpdate.ModifyPlanActivity;
import com.seoultech.lesson.souffle.ui.adapter.SettingActivity;
import com.seoultech.lesson.souffle.ui.add_Plan.SelectBuildingActivity;
import com.seoultech.lesson.souffle.ui.PlanUpdate.SearchPlanActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

public class SelectMenuActivity extends AppCompatActivity implements View.OnClickListener{

   LinearLayout linearPlanAdd, linearPlanDelete, linearPlanModify, linearPlanUpdate;

    private Intent selectMenuIntent;
    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private TextView btnToMain;
    private String user_name, user_major;
    private int user_stNumber;
    private TextView btnUserInfo;
    private TextView btnSetting;
    private User user;
    private TextView btnLogout;

    private Toast toast;
    private long backKeyPressedTime = 0;
    private Activity activity;
    private Context context;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_select_menu);

        selectMenuIntent = new Intent(this.getIntent());
        user = (User) selectMenuIntent.getSerializableExtra("user");

        backPressCloseHandler = new BackPressCloseHandler(this);
        btnUserInfo = (TextView)findViewById(R.id.btn_userInfo_in_selectmenu);
        btnSetting = (TextView)findViewById(R.id.btn_setting_in_selectmenu);

        linearPlanAdd = (LinearLayout)findViewById(R.id.linear_plan_add);
        linearPlanDelete = (LinearLayout)findViewById(R.id.linear_plan_delete);
        linearPlanModify = (LinearLayout)findViewById(R.id.linear_plan_modify);
        linearPlanUpdate = (LinearLayout)findViewById(R.id.linear_plan_update);

        btnToMain = (TextView)findViewById(R.id.btn_to_main_in_selectmenu) ;
        btnLogout = (TextView)findViewById(R.id.btn_logout_in_selectmenu);

        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_selectMenu);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_select_menu);
        frameSelectMenu = (FrameLayout)findViewById(R.id.select_menu_frame);
        frameSelectMenu.bringChildToFront(slideLayout);


        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder logoutDlg = new AlertDialog.Builder(SelectMenuActivity.this);
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

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLoginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(toLoginIntent);
                finishActivity(0);
            }
        });

       linearPlanAdd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent buildingSelectIntent = new Intent(getApplicationContext(), SelectBuildingActivity.class);
               buildingSelectIntent.putExtra("user",user);
               startActivity(buildingSelectIntent);
           }
       });

       btnSetting.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       linearPlanDelete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent toDeletePlanIntent = new Intent(getApplicationContext(), DeletePlanActivity.class);
               toDeletePlanIntent.putExtra("user",user);
               startActivity(toDeletePlanIntent);
           }
       });

       linearPlanUpdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent toUpdatePlanIntent = new Intent(getApplicationContext(), SearchPlanActivity.class);
               toUpdatePlanIntent.putExtra("user",user);
               startActivity(toUpdatePlanIntent);
           }
       });

        linearPlanModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toModifyPlanIntent = new Intent(getApplicationContext(), ModifyPlanActivity.class);
                toModifyPlanIntent.putExtra("user",user);
                startActivity(toModifyPlanIntent);
            }
        });

        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent to_main_intent = new Intent(getApplicationContext(),SelectMenuActivity.class);
//                startActivity(to_main_intent);
                Toast.makeText(getApplicationContext(),"이미 메인화면입니다",Toast.LENGTH_SHORT).show();
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSettingIntent = new Intent(getApplicationContext(), SettingActivity.class);
                toSettingIntent.putExtra("user",user);
                startActivity(toSettingIntent);
            }
        });

        fabMenu.setOnClickListener(this);

    }
    //onCreate End


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_in_selectMenu:
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
        this.context = activity;
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finishAffinity();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(getApplicationContext(), "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
