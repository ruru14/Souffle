package com.seoultech.lesson.souffle.ui.add_Plan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;

public class AddOptionActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editName, editStudentNumber, editTime, editObjective, editPeopleNumber, editRoomNum, editBuilding, editPhoneNumber, editETC;
    private Button btnBackToTimeReserve, btnCommitReserve;
    private int rYear, rMonth, rDay;
    private String roomNum;
    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private Button btnToMain;
    private User user;
    private Button btnUserInfo;
    private String fromTime, toTime;
    private String building_name;
    private AppController appController;


    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appController = AppController.getInstance();
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_add_option);
        Intent AddOptionIntent = new Intent(this.getIntent());

        rYear = AddOptionIntent.getExtras().getInt("reserve_year");
        rMonth = AddOptionIntent.getExtras().getInt("reserve_month");
        rDay = AddOptionIntent.getExtras().getInt("reserve_day");

        user = (User) AddOptionIntent.getSerializableExtra("user");
        btnUserInfo = (Button)findViewById(R.id.btn_userInfo_in_addoption);

        building_name = AddOptionIntent.getExtras().getString("building_name");
        editBuilding = (EditText) findViewById(R.id.txt_building);
        editBuilding.setText(building_name);

        btnCommitReserve = (Button)findViewById(R.id.btn_commit_reserve);

        backPressCloseHandler = new BackPressCloseHandler(this);

        btnToMain = (Button)findViewById(R.id.btn_to_main_in_add_option);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_add_option);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_add_option);
        frameSelectMenu = (FrameLayout)findViewById(R.id.add_option_frame);
        frameSelectMenu.bringChildToFront(slideLayout);

        roomNum = AddOptionIntent.getExtras().getString("room_numbers");

        editName = (EditText)findViewById(R.id.edit_name);
        editStudentNumber = (EditText)findViewById(R.id.edit_student_number);
        editTime = (EditText)findViewById(R.id.edit_time);
        editObjective = (EditText)findViewById(R.id.edit_object);
        editPeopleNumber = (EditText)findViewById(R.id.edit_people_number);
        editRoomNum = (EditText)findViewById(R.id.edit_room);
        editPhoneNumber = (EditText)findViewById(R.id.edit_phone_number);
        editETC = (EditText)findViewById(R.id.edit_etc);
        editName.setText(user.getName());

        editStudentNumber.setText(Integer.toString(user.getStudentNumber()));

        btnBackToTimeReserve = (Button)findViewById(R.id.btn_back_to_time_reserve);

        int year = AddOptionIntent.getExtras().getInt("reserve_year");
        int month = AddOptionIntent.getExtras().getInt("reserve_month");
        int day = AddOptionIntent.getExtras().getInt("reserve_day");
        int hour = AddOptionIntent.getExtras().getInt("reserve_hour");
        int minute = AddOptionIntent.getExtras().getInt("reserve_minute");

        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());
        fromTime = String.valueOf(AddOptionIntent.getExtras().getInt("fromTime")) + ":00";
        toTime = String.valueOf(AddOptionIntent.getExtras().getInt("toTime")) + ":00";
        editTime.setText(year + "년 " + month + "월 " +
                day + "일\n" + fromTime + " ~ " + toTime);
        editRoomNum.setText(roomNum + "호");

        btnBackToTimeReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOptionActivity.super.onBackPressed();
            }
        });



        btnCommitReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reservation reservation = new Reservation(Integer.parseInt(roomNum),
                        year+"-"+month+"-"+day,
                        user.getStudentNumber(),
                        user.getName(),
                        editObjective.getText().toString(),
                        Integer.parseInt(editPeopleNumber.getText().toString()),
                        fromTime,
                        toTime,
                        editBuilding.getText().toString());

                AlertDialog.Builder reserveCommitDlg =  new AlertDialog.Builder(AddOptionActivity.this);
                reserveCommitDlg.setTitle("최종 확인");
                reserveCommitDlg.setIcon(R.drawable.ic_launcher_foreground);
                reserveCommitDlg.setMessage("해당 내용으로 예약하시겠습니까?");
                reserveCommitDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ProgressDialog progressDialogInAO = new ProgressDialog(AddOptionActivity.this);
                        new AsyncTask<Object, Integer, Boolean>() {

                            @Override
                            protected void onPreExecute() {
                                // 전송중...
                                progressDialogInAO.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                progressDialogInAO.setMessage("Logining");
                                progressDialogInAO.show();
                                super.onPreExecute();
                            }

                            @Override
                            protected Boolean doInBackground(Object... objects) {
                                // 예약 (성공여부 bool)
                                return appController.createReservation((Reservation) objects[0]);
                            }

                            @Override
                            protected void onPostExecute(Boolean aBoolean) {
                                // 예약 후 실행됨
                                if(aBoolean){
                                    Toast.makeText(getApplicationContext(),"예약되었습니다",Toast.LENGTH_SHORT).show();
                                    Intent toMainMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                                    toMainMenuIntent.putExtra("user",user);
                                    startActivity(toMainMenuIntent);
                                }
                                else{
                                    AlertDialog.Builder logInFailDlg = new AlertDialog.Builder(AddOptionActivity.this);
                                    logInFailDlg.setMessage("예약에 실패하였습니다.");
                                    logInFailDlg.setPositiveButton("확인",null);
                                    logInFailDlg.show();
                                }
                            }
                        }.execute(reservation);

                    }
                });
                reserveCommitDlg.show();
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
            case R.id.fab_in_add_option:
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
