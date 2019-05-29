package com.seoultech.lesson.souffle.ui.add_Plan;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.login.LoginActivity;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.option.CheckAdapter;
import com.seoultech.lesson.souffle.ui.option.CheckData;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;


//해당 강의실을 예약할 날짜와 시간을 고르는 액티비티

public class TimeReserveActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<CheckData> chkArrList;
    CheckAdapter chkAdapter;
    private BackPressCloseHandler backPressCloseHandler;
    ListView chk_list;
    CoordinatorLayout layout_cor;
    NestedScrollView scrollView;
    LinearLayout slide_layout;
    TimePicker time;
    TextView txt_year_dlg, txt_month_dlg, txt_day_dlg, txt_room_dlg;
    String room_nums;
    Button btn_date, btn_plan_in_time, btn_select_date;
    Date current_time;
    CheckBox chk_am9, chk_am10, chk_am11, chk_pm12, chk_pm2, chk_pm3, chk_pm4, chk_pm5;
    TextView txt_am9, txt_am10, txt_am11, txt_pm12, txt_pm2, txt_pm3, txt_pm4, txt_pm5;
    Button btn_to_main, btn_setting, btn_logout;
    DatePickerDialog dateDlg;
    int dYear = -1;
    int dMonth, dDay;
    int dHour = -1;
    int dMinute;
    int tYear = 0;
    int tMonth = 0;
    int tDay = 0;
    int tHour = 0;
    int tMinute = 0;
    NumberPicker numberPicker;
    private final int TIME_INTERVAL = 30;
    FloatingActionButton fab_main, fab_menu1, fab_menu2;
    private Animation pull_from_right, push_to_right;
    private Animation pull_from_left, push_to_left;
    private Boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_time_reserve);

   /*    chk_list = findViewById(R.id.chk_list);

        chkArrList = new ArrayList();
        chkArrList.add(new CheckData(R.id.chk_am9,true));
        chkArrList.add(new CheckData(R.id.chk_am10,true));
        chkArrList.add(new CheckData(R.id.chk_am11,true));
        chkArrList.add(new CheckData(R.id.chk_pm12,true));
        chkArrList.add(new CheckData(R.id.chk_pm2,true));
        chkArrList.add(new CheckData(R.id.chk_pm3,true));
        chkArrList.add(new CheckData(R.id.chk_pm4,true));
        chkArrList.add(new CheckData(R.id.chk_pm5,true));

        chkAdapter = new CheckAdapter(chkArrList);
        chkAdapter.setOnItemClickListener(this);*/
        fab_main = (FloatingActionButton) findViewById(R.id.fab);
        slide_layout = (LinearLayout)findViewById(R.id.layout_slide);
        scrollView = (NestedScrollView)findViewById(R.id.ScrollView01);

        btn_to_main = (Button)findViewById(R.id.btn_to_main);
        btn_setting = (Button)findViewById(R.id.btn_setting);
        btn_logout = (Button)findViewById(R.id.btn_logout);

        TextView txt_test = (TextView) findViewById(R.id.text_test);

        pull_from_right = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);
        push_to_right = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pull_from_left = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromleft);
        push_to_left = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoleft);

        fab_main.setOnClickListener(this);

        backPressCloseHandler = new BackPressCloseHandler(this);

        Intent time_intent = new Intent(this.getIntent());

        txt_room_dlg = (TextView) findViewById(R.id.txt_room_dlg);
        txt_year_dlg = (TextView) findViewById(R.id.txt_year_dlg);
        txt_month_dlg = (TextView) findViewById(R.id.txt_month_dlg);
        txt_day_dlg = (TextView) findViewById(R.id.txt_day_dlg);

        chk_am9 = (CheckBox) findViewById(R.id.chk_am9);
        chk_am10 = (CheckBox) findViewById(R.id.chk_am10);
        chk_am11 = (CheckBox) findViewById(R.id.chk_am11);
        chk_pm12 = (CheckBox) findViewById(R.id.chk_pm12);
        chk_pm2 = (CheckBox) findViewById(R.id.chk_pm2);
        chk_pm3 = (CheckBox) findViewById(R.id.chk_pm3);
        chk_pm4 = (CheckBox) findViewById(R.id.chk_pm4);
        chk_pm5 = (CheckBox) findViewById(R.id.chk_pm5);

        txt_am9 = (TextView) findViewById(R.id.txt_am9);
        txt_am10 = (TextView) findViewById(R.id.txt_am10);
        txt_am11 = (TextView) findViewById(R.id.txt_am11);
        txt_pm12 = (TextView) findViewById(R.id.txt_pm12);
        txt_pm2 = (TextView) findViewById(R.id.txt_pm2);
        txt_pm3 = (TextView) findViewById(R.id.txt_pm3);
        txt_pm4 = (TextView) findViewById(R.id.txt_pm4);
        txt_pm5 = (TextView) findViewById(R.id.txt_pm5);


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

        room_nums = time_intent.getExtras().getString("room_number");
        txt_room_dlg.setText(room_nums + "호");
        final int room_num_int = Integer.parseInt(room_nums);

        btn_date = (Button) findViewById(R.id.btn_date_time);
        time = (TimePicker) findViewById(R.id.time_pick);
        btn_select_date = (Button) findViewById(R.id.btn_select_date);

        numberPicker = (NumberPicker) time.findViewById(Resources.getSystem().getIdentifier("minute", "id", "android"));
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue((60 / TIME_INTERVAL) - 1);
        List<String> minuteVal = new ArrayList<String>();
        for (int i = 0; i < 60; i += 30) {
            minuteVal.add(String.format("%2d", i));
        }
        numberPicker.setDisplayedValues(minuteVal.toArray(new String[0]));

        current_time = Calendar.getInstance().getTime();
        final int mYear = Calendar.getInstance().get(Calendar.YEAR);
        final int mMonth = Calendar.getInstance().get(Calendar.MONTH);
        final int mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd hh mm");
        SimpleDateFormat sdf_date = new SimpleDateFormat("yy MM dd");
        sdf.format(current_time.getTime());
        sdf_date.format(current_time.getTime());
        String today = sdf.format(current_time);

        btn_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_main_intent = new Intent(getApplicationContext(),SelectMenuActivity.class);
                startActivity(to_main_intent);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
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

        btn_select_date.setOnClickListener(new View.OnClickListener() {
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
                            txt_year_dlg.setText(dYear + "년 ");
                            txt_month_dlg.setText(dMonth + "월 ");
                            txt_day_dlg.setText(dDay + "일 ");
                        }
                    }
                });
                dateDlg.show();
            }
        });

        btn_date.setOnClickListener(new View.OnClickListener() {
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
                            Intent to_option_intent = new Intent(getApplicationContext(), AddOptionActivity.class);
                            to_option_intent.putExtra("reserve_year", tYear);
                            to_option_intent.putExtra("reserve_month", tMonth);
                            to_option_intent.putExtra("reserve_day", tDay);
                            to_option_intent.putExtra("reserve_hour", tHour);
                            to_option_intent.putExtra("reserve_minute", tMinute);
                            to_option_intent.putExtra("room_numbers", room_nums);
                            startActivity(to_option_intent);
                        }
                    });
                    dlg.setNegativeButton("취소", null);
                    dlg.show();
                }
            }
        });

        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                dHour = hourOfDay;
                dMinute = minute * 30;       //15분 단위로설정
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
            slide_layout.startAnimation(push_to_right);
            slide_layout.setVisibility(View.INVISIBLE);
            scrollView.startAnimation(pull_from_left);
            scrollView.setVisibility(View.VISIBLE);
            isFabOpen = false;
        } else {
            slide_layout.startAnimation(pull_from_right);
            slide_layout.setVisibility(View.VISIBLE);
            scrollView.startAnimation(push_to_left);
            scrollView.setVisibility(View.INVISIBLE);
            isFabOpen = true;
        }
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                anim();

                Toast.makeText(this, "Floating Action Button", Toast.LENGTH_SHORT).show();
                break;
           /* case R.id.fab1:
                anim();
                Toast.makeText(this, "Button1", Toast.LENGTH_SHORT).show();
                Intent to_main_menu_intent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                startActivity(to_main_menu_intent);
                break;
            case R.id.fab2:
                anim();
                Toast.makeText(this, "Button2", Toast.LENGTH_SHORT).show();
                break;*/
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


