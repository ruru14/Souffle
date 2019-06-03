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
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;

import butterknife.ButterKnife;

public class SelectBuildingActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnMirae;
    private Button btnBuilding1;
    private Button btnBuilding2;
    private Button btnBuilding3;

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
        setContentView(R.layout.activity_select_building);

        Intent toGetIntent = new Intent(this.getIntent());

        ButterKnife.bind(this);

        btnToMain = (Button)findViewById(R.id.btn_to_main_in_select_building);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_select_building);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_select_building);
        frameSelectMenu = (FrameLayout)findViewById(R.id.select_building_frame);
        frameSelectMenu.bringChildToFront(slideLayout);

        btnMirae = (Button)findViewById(R.id.btn_mirae);

        btnMirae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRoomSelectIntent = new Intent(getApplicationContext(),SelectRoomActivity.class);
                toRoomSelectIntent.putExtra("building_name","미래관");
                startActivity(toRoomSelectIntent);
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
