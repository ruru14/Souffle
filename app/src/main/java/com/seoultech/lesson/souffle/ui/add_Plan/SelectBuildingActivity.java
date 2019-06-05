package com.seoultech.lesson.souffle.ui.add_Plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;

import butterknife.ButterKnife;

public class SelectBuildingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{

    private Button btnMirae;
    private Button btnBuilding1;
    private Button btnBuilding2;
    private Button btnBuilding3;
    private Button btnSelectBuilding;
    private String user_name, user_major;
    private int user_stNumber;
    private String buildingName;

    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private Button btnToMain, btnUserInfo;
    private User user;
    private Spinner buildingSelecter;


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
        Intent toRoomSelectIntent = new Intent(getApplicationContext(),SelectRoomActivity.class);

        ButterKnife.bind(this);

        btnToMain = (Button)findViewById(R.id.btn_to_main_in_select_building);
        btnUserInfo = (Button)findViewById(R.id.btn_userInfo_in_selectbuilding);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_select_building);
        btnSelectBuilding = (Button)findViewById(R.id.btn_selectBuilding);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_select_building);
        frameSelectMenu = (FrameLayout)findViewById(R.id.select_building_frame);
        frameSelectMenu.bringChildToFront(slideLayout);
        buildingSelecter = (Spinner)findViewById(R.id.spinner_building);

        btnMirae = (Button)findViewById(R.id.btn_mirae);

        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        String[] buildingLayer = new String[]{"선택하세요", "미래관", "건물A", "건물B", "건물C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, buildingLayer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buildingSelecter.setOnItemSelectedListener((OnItemSelectedListener) this);
        buildingSelecter.setAdapter(adapter);

        btnSelectBuilding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRoomSelectIntent.putExtra("building_name",buildingName);
                toRoomSelectIntent.putExtra("user",user);
                startActivity(toRoomSelectIntent);
            }
        });


        btnMirae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View v, int i, long l) {
            switch(i){
                case 0:
                    Toast.makeText(getApplicationContext(),"건물을 선택해주세요.",Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    buildingName = "Building_Mirae";
                    break;
                case 2:
                    buildingName = "Building_A";
                    break;
                case 3:
                    buildingName = "Building_B";
                    break;
                case 4:
                    buildingName = "Building_C";
                    break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
