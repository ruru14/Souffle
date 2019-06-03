package com.seoultech.lesson.souffle.ui.add_Plan;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.login.LoginActivity;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.option.CheckAdapter;
import com.seoultech.lesson.souffle.ui.option.CheckData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


//해당 강의실을 예약할 날짜와 시간을 고르는 액티비티

public class TimeReserveActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<CheckData> chkArrList;
    private CheckAdapter chkAdapter;
    private BackPressCloseHandler backPressCloseHandler;
    private ListView chk_list;
    private CoordinatorLayout layout_cor;
    private NestedScrollView scrollView;
    private LinearLayout slideLayout;
    private TextView txtYearDlg, txtMonthDlg, txtDayDlg, txtRoomDlg;
    private String roomNumber;
    private Button btnDate, btnPlanInTime, btnSelectDate;
    private Date currentTime;
    private CheckBox chk_am9, chk_am10, chk_am11, chk_pm12, chk_pm2, chk_pm3, chk_pm4, chk_pm5;
    private  CheckBox chk_pm6, chk_pm7, chk_pm8, chk_pm9, chk_pm10, chk_pm11;
    private TextView txtAm9, txtAm10, txtAm11, txtPm12, txtPm2, txtPm3, txtPm4, txtPm5;
    private Button btnToMain, btnSetting, btnLogout, btnUserInfo;
    private User user;
    private DatePickerDialog dateDlg;
    private FrameLayout timeReserveFrame;
    private int dYear = -1;
    private int dMonth, dDay;
    private int dHour = 12;
    private int dMinute;
    private int tYear = 0;
    private int tMonth = 0;
    private int tDay = 0;
    private int tHour = 0;
    private int tMinute = 0;
    private final int TIME_INTERVAL = 30;
    private FloatingActionButton fabMain;
    private Animation pullFromRight, pushToRight;
    private Animation pullFromLeft, pushToLeft;
    private Boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_time_reserve);

        Intent timeReserveIntent = new Intent(this.getIntent());
        user = (User) timeReserveIntent.getSerializableExtra("user");

        btnUserInfo = (Button)findViewById(R.id.btn_userInfo_in_timereserve);
        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        fabMain = (FloatingActionButton) findViewById(R.id.fab_in_time_reserve);
        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_time_reserve);
        scrollView = (NestedScrollView)findViewById(R.id.ScrollView01);

        btnToMain = (Button)findViewById(R.id.btn_to_main_in_time_reserve);
        btnSetting = (Button)findViewById(R.id.btn_setting_in_time_reserve);
        btnLogout = (Button)findViewById(R.id.btn_logout_in_time_reserve);

        TextView txt_test = (TextView) findViewById(R.id.text_test);

        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);
        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
//        pullFromLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromleft);
//        pushToLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoleft);

        timeReserveFrame = (FrameLayout)findViewById(R.id.time_reserve_frame);
        timeReserveFrame.bringChildToFront(slideLayout);

        fabMain.setOnClickListener(this);

        backPressCloseHandler = new BackPressCloseHandler(this);


        txtRoomDlg = (TextView) findViewById(R.id.txt_room_dlg);
        txtYearDlg = (TextView) findViewById(R.id.txt_year_dlg);
        txtMonthDlg = (TextView) findViewById(R.id.txt_month_dlg);
        txtDayDlg = (TextView) findViewById(R.id.txt_day_dlg);

        chk_am9 = (CheckBox) findViewById(R.id.chk_am9);
        chk_am10 = (CheckBox) findViewById(R.id.chk_am10);
        chk_am11 = (CheckBox) findViewById(R.id.chk_am11);
        chk_pm12 = (CheckBox) findViewById(R.id.chk_pm12);
        chk_pm2 = (CheckBox) findViewById(R.id.chk_pm2);
        chk_pm3 = (CheckBox) findViewById(R.id.chk_pm3);
        chk_pm4 = (CheckBox) findViewById(R.id.chk_pm4);
        chk_pm5 = (CheckBox) findViewById(R.id.chk_pm5);
        chk_pm6 = (CheckBox) findViewById(R.id.chk_pm6);
        chk_pm7 = (CheckBox) findViewById(R.id.chk_pm7);
        chk_pm8 = (CheckBox) findViewById(R.id.chk_pm8);
        chk_pm9 = (CheckBox) findViewById(R.id.chk_pm9);
        chk_pm10 = (CheckBox) findViewById(R.id.chk_pm10);
        chk_pm11 = (CheckBox) findViewById(R.id.chk_pm11);

        txtAm9 = (TextView) findViewById(R.id.txt_am9);
        txtAm10 = (TextView) findViewById(R.id.txt_am10);
        txtAm11 = (TextView) findViewById(R.id.txt_am11);
        txtPm12 = (TextView) findViewById(R.id.txt_pm12);
        txtPm2 = (TextView) findViewById(R.id.txt_pm2);
        txtPm3 = (TextView) findViewById(R.id.txt_pm3);
        txtPm4 = (TextView) findViewById(R.id.txt_pm4);
        txtPm5 = (TextView) findViewById(R.id.txt_pm5);

        chk_am9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chk_am9.isChecked()){
                    chk_am10.setEnabled(true);                    chk_am11.setEnabled(false);
                    chk_pm12.setEnabled(false);                    chk_pm2.setEnabled(false);
                    chk_pm3.setEnabled(false);                    chk_pm4.setEnabled(false);
                    chk_pm5.setEnabled(false);
                }
                if(!chk_am9.isChecked()){
                    chk_am11.setEnabled(true);                    chk_pm12.setEnabled(true);
                    chk_pm2.setEnabled(true);                    chk_pm3.setEnabled(true);
                    chk_pm4.setEnabled(true);                    chk_pm5.setEnabled(true);
                }
            }
        });

        chk_am10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chk_am10.isChecked()){
                    chk_am9.setEnabled(true);                    chk_am11.setEnabled(true);
                    chk_pm12.setEnabled(false);                    chk_pm2.setEnabled(false);
                    chk_pm3.setEnabled(false);                    chk_pm4.setEnabled(false);
                    chk_pm5.setEnabled(false);
                }
                if(!chk_am10.isChecked()){
                    chk_pm12.setEnabled(true);
                    chk_pm2.setEnabled(true);                    chk_pm3.setEnabled(true);
                    chk_pm4.setEnabled(true);                    chk_pm5.setEnabled(true);
                }
            }
        });

        roomNumber = timeReserveIntent.getExtras().getString("room_number");
        txtRoomDlg.setText(roomNumber + "호");
        final int room_num_int = Integer.parseInt(roomNumber);

        btnDate = (Button) findViewById(R.id.btn_date_time);
        btnSelectDate = (Button) findViewById(R.id.btn_select_date);

        currentTime = Calendar.getInstance().getTime();
        final int mYear = Calendar.getInstance().get(Calendar.YEAR);
        final int mMonth = Calendar.getInstance().get(Calendar.MONTH);
        final int mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd hh mm");
        SimpleDateFormat sdf_date = new SimpleDateFormat("yy MM dd");
        sdf.format(currentTime.getTime());
        sdf_date.format(currentTime.getTime());
        String today = sdf.format(currentTime);

        btnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainMenuIntent = new Intent(getApplicationContext(),SelectMenuActivity.class);
                toMainMenuIntent.putExtra("user",user);
                startActivity(toMainMenuIntent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder logout_dlg = new AlertDialog.Builder(TimeReserveActivity.this);
                logout_dlg.setTitle("로그아웃 확인");
                logout_dlg.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //로그아웃 구현하면 됨
                        Intent to_login_intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(to_login_intent);
                    }
                });
                logout_dlg.setNegativeButton("No",null);
                logout_dlg.show();
            }
        });

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDlg = new DatePickerDialog(TimeReserveActivity.this, listener, mYear, mMonth, mDay);
                dateDlg.getDatePicker().setMaxDate(System.currentTimeMillis() + 3600000 * 168 * 2);   //날짜선택은 오늘~2주 후까지만 가능
                dateDlg.getDatePicker().setMinDate(System.currentTimeMillis());
                dateDlg.getDatePicker().setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dYear = year;
                        dMonth = monthOfYear + 1;
                        dDay = dayOfMonth;
                    }
                });
                dateDlg.setButton(DialogInterface.BUTTON_POSITIVE, "확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == DialogInterface.BUTTON_POSITIVE) {
                            txtYearDlg.setText(dYear + "년 ");
                            txtMonthDlg.setText(dMonth + "월 ");
                            txtDayDlg.setText(dDay + "일 ");
                        }
                    }
                });
                dateDlg.show();
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(TimeReserveActivity.this);
                dlg.setTitle("예약 화면");
                dlg.setIcon(R.mipmap.ic_launcher);
                if (dYear == -1 || dHour == -1)
                    Toast.makeText(getApplicationContext(), "날짜나 시간을 제대로 입력해 주십시오", Toast.LENGTH_SHORT).show();
                else {
                    dlg.setMessage(dYear + "년 " + dMonth + "월 " + dDay + "일\n" + dHour + "시 " + dMinute + "분 예약이 맞습니까?");
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //확인 누르면 해당 시간, 날짜정보를 DB에 저장하면 됨. dHour, dMinute, dYear, dMonth, dDay 정보
                            Toast.makeText(getApplicationContext(), dYear + "년 " + dMonth + "월 " +
                                    dDay + "일\n" + dHour + "시 " + dMinute + "분 으로 예약하였습니다.", Toast.LENGTH_SHORT).show();
                            tHour = getReturn(dHour);
                            tMinute = getReturn(dMinute);
                            tYear = getReturn(dYear);
                            tMonth = getReturn(dMonth);
                            tDay = getReturn(dDay);
                            Intent toAddOptionIntent = new Intent(getApplicationContext(), AddOptionActivity.class);
                            toAddOptionIntent.putExtra("reserve_year", tYear);
                            toAddOptionIntent.putExtra("reserve_month", tMonth);
                            toAddOptionIntent.putExtra("reserve_day", tDay);
                            toAddOptionIntent.putExtra("reserve_hour", tHour);
                            toAddOptionIntent.putExtra("reserve_minute", tMinute);
                            toAddOptionIntent.putExtra("room_numbers", roomNumber);
                            toAddOptionIntent.putExtra("user",user);
                            startActivity(toAddOptionIntent);
                        }
                    });
                    dlg.setNegativeButton("취소", null);
                    dlg.show();
                }
            }
        });

    }
        //OnCreate end

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dYear = year;
            dMonth = month +1;
            dDay = dayOfMonth;
        }
    };

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
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_in_time_reserve:
                anim();

                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

    public int getReturn(int n){
        return n;
    }


}


