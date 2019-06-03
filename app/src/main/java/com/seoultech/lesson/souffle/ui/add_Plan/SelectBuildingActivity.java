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

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;

import butterknife.ButterKnife;

public class SelectBuildingActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnMirae;
    private Button btnBuilding1;
    private Button btnBuilding2;
    private Button btnBuilding3;
    private String user_name, user_major;
    private int user_stNumber;

    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private Button btnToMain, btnUserInfo;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_select_building);

        Intent selectBuildingIntent = new Intent(this.getIntent());
        user = (User) selectBuildingIntent.getSerializableExtra("user");

        ButterKnife.bind(this);

        btnToMain = (Button)findViewById(R.id.btn_to_main_in_select_building);
        btnUserInfo = (Button)findViewById(R.id.btn_userInfo_in_selectbuilding);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_select_building);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_select_building);
        frameSelectMenu = (FrameLayout)findViewById(R.id.select_building_frame);
        frameSelectMenu.bringChildToFront(slideLayout);

        btnMirae = (Button)findViewById(R.id.btn_mirae);

        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        btnMirae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMiraeRoomSelectIntent = new Intent(getApplicationContext(),SelectRoomActivity.class);
                toMiraeRoomSelectIntent.putExtra("building_name","미래관");
                toMiraeRoomSelectIntent.putExtra("user",user);
                Intent toAddOptionIntent = new Intent(getApplicationContext(),AddOptionActivity.class);
                toAddOptionIntent.putExtra("building_name","미래관");
                startActivity(toMiraeRoomSelectIntent);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_in_select_building:
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
}
