package com.seoultech.lesson.souffle.ui.add_plan;

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
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.Setting.SettingActivity;
import com.seoultech.lesson.souffle.ui.login.LoginActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;

import java.util.Locale;

public class AddOptionActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editName, editStudentNumber, editTime, editObjective, editPeopleNumber, editRoomNum, editBuilding, editPhoneNumber;
    private Button btnCommitReserve;
    private int rYear, rMonth, rDay;
    private int rYearModify, rMonthModify, rDayModify;
    private String roomNum;
    private String getRoomNumModify;
    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private TextView btnToMain;
    private TextView btnSettings, btnLogout;
    private User user;
    private TextView btnUserInfo;
    private String fromTime, toTime;
    private String fromTimeModify, toTimeModify;
    private String building_name;
    private String building_nameModify;
    private AppController appController;
    private Boolean boolUnderground;
    private Boolean boolModify = false;
    private int year, month, day, hour, minute;
    private String roomNumModify;
    private Reservation reservation;
    private String reserveDate;



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

        setContentView(R.layout.activity_add_option);
        Intent AddOptionIntent = new Intent(this.getIntent());

        Reservation reservation_modify = (Reservation) AddOptionIntent.getExtras().getSerializable("reservation_modify");

        rYear = AddOptionIntent.getExtras().getInt("reserve_year");
        rMonth = AddOptionIntent.getExtras().getInt("reserve_month");
        rDay = AddOptionIntent.getExtras().getInt("reserve_day");

        AlertDialog.Builder ReservationFailDlg = new AlertDialog.Builder(AddOptionActivity.this);

        boolUnderground = AddOptionIntent.getExtras().getBoolean("bool_underground");
        boolModify = AddOptionIntent.getExtras().getBoolean("bool_modify");

        user = (User) AddOptionIntent.getSerializableExtra("user");
        btnUserInfo = (TextView) findViewById(R.id.btn_userInfo_in_add_option);
        btnSettings = (TextView)findViewById(R.id.btn_setting_in_add_option);
        btnLogout = (TextView)findViewById(R.id.btn_logout_in_add_option);

        building_name = AddOptionIntent.getExtras().getString("building_name");

        editBuilding = (EditText) findViewById(R.id.txt_building);

        if(boolModify == false)
        editBuilding.setText(building_name);
        else {
            building_nameModify = reservation_modify.getBuilding();
            editBuilding.setText(building_nameModify);
//            editBuilding.setText(Changer.buildingChange(reservation_modify.getBuilding()));
        }

        btnCommitReserve = (Button)findViewById(R.id.btn_commit_reserve);

        backPressCloseHandler = new BackPressCloseHandler(this);

        btnToMain = (TextView) findViewById(R.id.btn_to_main_in_add_option);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_add_option);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_add_option);
        frameSelectMenu = (FrameLayout)findViewById(R.id.add_option_frame);
        frameSelectMenu.bringChildToFront(slideLayout);

        roomNum = AddOptionIntent.getExtras().getString("room_numbers");

        if(boolModify == true)
        roomNumModify = reservation_modify.getRoomNumber() + "호";


        editName = (EditText)findViewById(R.id.edit_name);
        editStudentNumber = (EditText)findViewById(R.id.edit_student_number);
        editTime = (EditText)findViewById(R.id.edit_time);
        editObjective = (EditText)findViewById(R.id.edit_object);
        editPeopleNumber = (EditText)findViewById(R.id.edit_people_number);
        editRoomNum = (EditText)findViewById(R.id.edit_room);
        editPhoneNumber = (EditText)findViewById(R.id.edit_phone_number);
        editName.setText(user.getName());



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder logoutDlg = new AlertDialog.Builder(AddOptionActivity.this);
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


        editStudentNumber.setText(Integer.toString(user.getStudentNumber()));


        if(boolModify == false) {
            year = AddOptionIntent.getExtras().getInt("reserve_year");
            month = AddOptionIntent.getExtras().getInt("reserve_month");
            day = AddOptionIntent.getExtras().getInt("reserve_day");
            hour = AddOptionIntent.getExtras().getInt("reserve_hour");
            minute = AddOptionIntent.getExtras().getInt("reserve_minute");
        }
        else{
            year = AddOptionIntent.getExtras().getInt("reserve_year");
            month = AddOptionIntent.getExtras().getInt("reserve_month");
            day = AddOptionIntent.getExtras().getInt("reserve_day");
            hour = AddOptionIntent.getExtras().getInt("reserve_hour");
            minute = AddOptionIntent.getExtras().getInt("reserve_minute");
        }

        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());
        fromTime = String.valueOf(AddOptionIntent.getExtras().getInt("fromTime")) + ":00";
        toTime = String.valueOf(AddOptionIntent.getExtras().getInt("toTime")) + ":00";
        if(boolModify == true) {
            fromTimeModify = reservation_modify.getTimeStart();
            toTimeModify = reservation_modify.getTimeEnd();
        }

        if(boolModify == false)
        editTime.setText(year + " - " + month + " - " +
                day + "\n" + fromTime + " ~ " + toTime);
        else
                editTime.setText(reservation_modify.getDate() +"\n" +  reservation_modify.getTimeStart() +
                        " ~ " +reservation_modify.getTimeEnd());

        if(boolUnderground == false)
        editRoomNum.setText(roomNum + "호");
        else
            editRoomNum.setText("b" + roomNum + "호");

        if(boolModify == true){
             editObjective.setText(reservation_modify.getPurpose());
             editPeopleNumber.setText(Integer.toString(reservation_modify.getTotalMember()));
        }


        if(boolModify == false)
            editRoomNum.setText(roomNum + "호");
        else
            editRoomNum.setText(reservation_modify.getRoomNumber() + "호");



        btnCommitReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(boolModify == false) {
                    reservation = new Reservation(Integer.parseInt(roomNum),
                            year + "-" + month +  "-" + day,
                            user.getStudentNumber(),
                            user.getName(),
                            editObjective.getText().toString(),
                            Integer.parseInt(editPeopleNumber.getText().toString()),
                            fromTime,
                            toTime,
                            building_name);
                }
                else{
                    reservation = (Reservation) AddOptionIntent.getSerializableExtra("reservation_modify");
                    reservation.setTotalMember(Integer.parseInt(editPeopleNumber.getText().toString()));
                    reservation.setPurpose(editObjective.getText().toString());
                }


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
                                if(!boolModify){
                                    System.out.println("생성");
                                    return appController.createReservation((Reservation)objects[0]);
                                }
                                else{
                                    System.out.println("수정");
                                    System.out.println(((Reservation)objects[0]).toString());
                                    return appController.updateReservation((Reservation)objects[0]);
                                }
                            }

                            @Override
                            protected void onPostExecute(Boolean aBoolean) {
                                // 예약 후 실행됨
                                if(aBoolean){
                                    if(boolModify){
                                        Toast.makeText(getApplicationContext(),"수정되었습니다",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"예약되었습니다",Toast.LENGTH_SHORT).show();
                                    }
                                    Intent toMainMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                                    toMainMenuIntent.putExtra("user",user);
                                    startActivity(toMainMenuIntent);
                                    finishActivity(0);
                                }
                                else{
                                    ReservationFailDlg.setMessage("예약에 실패하였습니다.");
                                    ReservationFailDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });
                                    ReservationFailDlg.show();
                                }
                                progressDialogInAO.dismiss();
                            }

                        }.execute(reservation);
                    }
                });
                reserveCommitDlg.show();
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSettingIntent = new Intent(getApplicationContext(), SettingActivity.class);
                toSettingIntent.putExtra("user",user);
                startActivity(toSettingIntent);
                anim();
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
    protected void onResume() {
        super.onResume();
        TextView txt_stNumber = findViewById(R.id.option_student_number);
        txt_stNumber.setText(R.string.student_number);
        TextView txt_name = findViewById(R.id.option_name);
        txt_name.setText(R.string.name);
        TextView txt_phone_number = findViewById(R.id.option_phone_number);
        txt_phone_number.setText(R.string.phone_number);
        TextView txt_reserve_time = findViewById(R.id.option_time);
        txt_reserve_time.setText(R.string.reserve_time);
        TextView txt_building = findViewById(R.id.option_building);
        txt_building.setText(R.string.building_name);
        TextView txt_totalPeople = findViewById(R.id.option_people_nunber);
        txt_totalPeople.setText(R.string.total_number);
        TextView txt_room = findViewById(R.id.option_room);
        txt_room.setText(R.string.lecture_room);
        TextView txt_purpose = findViewById(R.id.option_object);
        txt_purpose.setText(R.string.purpose);
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
