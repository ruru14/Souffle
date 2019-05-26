package com.seoultech.lesson.souffle.ui.add_Plan;


import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//해당 강의실을 예약할 날짜와 시간을 고르는 액티비티

public class TimeReserveActivity extends AppCompatActivity implements View.OnClickListener{

    private BackPressCloseHandler backPressCloseHandler;
    CoordinatorLayout layout_cor;
    ScrollView scrollView;
    TimePicker time;
    TextView txt_year_dlg, txt_month_dlg, txt_day_dlg, txt_room_dlg;
    String room_nums;
    Button btn_date, btn_plan_in_time, btn_select_date;
    Date current_time;
    CheckBox chk_am9, chk_am10, chk_am11, chk_pm12, chk_pm2, chk_pm3, chk_pm4, chk_pm5;
    TextView txt_am9, txt_am10, txt_am11, txt_pm12, txt_pm2, txt_pm3, txt_pm4, txt_pm5;
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
    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_time_reserve);

        fab_main = (FloatingActionButton)findViewById(R.id.fab) ;
        fab_menu1 = (FloatingActionButton)findViewById(R.id.fab1) ;
        fab_menu2 = (FloatingActionButton)findViewById(R.id.fab2) ;

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        fab_main.setOnClickListener(this);
        fab_menu1.setOnClickListener(this);
        fab_menu2.setOnClickListener(this);

        backPressCloseHandler = new BackPressCloseHandler(this);

        Intent time_intent = new Intent(this.getIntent());

        txt_room_dlg = (TextView)findViewById(R.id.txt_room_dlg);
        txt_year_dlg = (TextView)findViewById(R.id.txt_year_dlg);
        txt_month_dlg = (TextView)findViewById(R.id.txt_month_dlg);
        txt_day_dlg = (TextView)findViewById(R.id.txt_day_dlg);

        chk_am9 = (CheckBox)findViewById(R.id.chk_am9);     chk_am10 = (CheckBox)findViewById(R.id.chk_am10);
        chk_am11 = (CheckBox)findViewById(R.id.chk_am11);   chk_pm12 = (CheckBox)findViewById(R.id.chk_pm12);
        chk_pm2 = (CheckBox)findViewById(R.id.chk_pm2);     chk_pm3 = (CheckBox)findViewById(R.id.chk_pm3);
        chk_pm4 = (CheckBox)findViewById(R.id.chk_pm4);     chk_pm5 = (CheckBox)findViewById(R.id.chk_pm5);

        txt_am9 = (TextView)findViewById(R.id.txt_am9);        txt_am10 = (TextView)findViewById(R.id.txt_am10);
        txt_am11 = (TextView)findViewById(R.id.txt_am11);      txt_pm12 = (TextView)findViewById(R.id.txt_pm12);
        txt_pm2 = (TextView)findViewById(R.id.txt_pm2);        txt_pm3 = (TextView)findViewById(R.id.txt_pm3);
        txt_pm4 = (TextView)findViewById(R.id.txt_pm4);        txt_pm5 = (TextView)findViewById(R.id.txt_pm5);

        chk_am9.setId(View.generateViewId());           txt_am9.setId(View.generateViewId());
        chk_am10.setId(View.generateViewId());          txt_am10.setId(View.generateViewId());
        chk_am11.setId(View.generateViewId());          txt_am11.setId(View.generateViewId());
        chk_pm12.setId(View.generateViewId());          txt_pm12.setId(View.generateViewId());
        chk_pm2.setId(View.generateViewId());           txt_pm2.setId(View.generateViewId());
        chk_pm3.setId(View.generateViewId());           txt_pm3.setId(View.generateViewId());
        chk_pm4.setId(View.generateViewId());           txt_pm4.setId(View.generateViewId());
        chk_pm5.setId(View.generateViewId());           txt_pm5.setId(View.generateViewId());


        room_nums = time_intent.getExtras().getString("room_number");
        txt_room_dlg.setText(room_nums + "호");
        final int room_num_int = Integer.parseInt(room_nums);

        btn_date = (Button)findViewById(R.id.btn_date_time);
        time = (TimePicker) findViewById(R.id.time_pick);
        btn_select_date = (Button)findViewById(R.id.btn_select_date);

        numberPicker = (NumberPicker)time.findViewById(Resources.getSystem().getIdentifier("minute","id","android"));
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue((60/TIME_INTERVAL)-1);
        List<String> minuteVal = new ArrayList<String>();
        for(int i=0;i<60;i+=30){
            minuteVal.add(String.format("%2d",i));
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

        btn_select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDlg = new DatePickerDialog(TimeReserveActivity.this, listener, mYear, mMonth,mDay);
                dateDlg.getDatePicker().setMaxDate(System.currentTimeMillis()+3600000 * 168 * 2);   //날짜선택은 오늘~2주 후까지만 가능
                dateDlg.getDatePicker().setMinDate(System.currentTimeMillis());
                dateDlg.getDatePicker().setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dYear = year;
                        dMonth = monthOfYear +1;
                        dDay = dayOfMonth;
                    }
                });
                dateDlg.setButton(DialogInterface.BUTTON_POSITIVE, "확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == DialogInterface.BUTTON_POSITIVE) {
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
                if(dYear == -1 || dHour == -1)
                    Toast.makeText(getApplicationContext(),"날짜나 시간을 제대로 입력해 주십시오",Toast.LENGTH_SHORT).show();
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
                            to_option_intent.putExtra("reserve_year",tYear);
                            to_option_intent.putExtra("reserve_month",tMonth);
                            to_option_intent.putExtra("reserve_day",tDay);
                            to_option_intent.putExtra("reserve_hour",tHour);
                            to_option_intent.putExtra("reserve_minute",tMinute);
                            to_option_intent.putExtra("room_numbers",room_nums);
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
                dMinute = minute*30 ;       //15분 단위로설정
            }
        });



    }

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
            fab_menu1.startAnimation(fab_close);
            fab_menu2.startAnimation(fab_close);
            fab_menu1.setClickable(false);
            fab_menu2.setClickable(false);
            isFabOpen = false;
        } else {
            fab_menu1.startAnimation(fab_open);
            fab_menu2.startAnimation(fab_open);
            fab_menu1.setClickable(true);
            fab_menu2.setClickable(true);
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
            case R.id.fab1:
                anim();
                Toast.makeText(this, "Button1", Toast.LENGTH_SHORT).show();
                Intent to_main_menu_intent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                startActivity(to_main_menu_intent);
                break;
            case R.id.fab2:
                anim();
                Toast.makeText(this, "Button2", Toast.LENGTH_SHORT).show();
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


