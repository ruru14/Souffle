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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

public class RoomPlanActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnBackToRoomSelect;
    TextView txtRoomGuide;
    private BackPressCloseHandler backPressCloseHandler;

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
        setContentView(R.layout.activity_room_plan);

        backPressCloseHandler = new BackPressCloseHandler(this);
//
        btnToMain = (Button)findViewById(R.id.btn_to_main_in_room_plan);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_room_plan);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_room_plan);
        frameSelectMenu = (FrameLayout)findViewById(R.id.room_plan_frame);
        frameSelectMenu.bringChildToFront(slideLayout);
//
        txtRoomGuide = (TextView)findViewById(R.id.txt_room_guide);
    btnBackToRoomSelect = (Button)findViewById(R.id.btn_back_to_room_select);
    Intent get_intent = new Intent(this.getIntent());

    txtRoomGuide.setText(get_intent.getExtras().getString("room_number_to_plan") + "호 강의실 사진");

    btnBackToRoomSelect.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_main_intent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                startActivity(to_main_intent);
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
