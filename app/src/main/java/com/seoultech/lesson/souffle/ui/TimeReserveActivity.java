package com.seoultech.lesson.souffle.ui;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//해당 강의실을 예약할 날짜와 시간을 고르는 액티비티

public class TimeReserveActivity extends AppCompatActivity {

    TimePicker time;
    TextView test_hour, test_txt;
    String room_nums;
    DatePicker date;
    Button btn_date;
    Date current_time;
    int dYear = -1;
    int dMonth, dDay;
    int dHour = -1;
    int dMinute;
    NumberPicker numberPicker;
    private final int TIME_INTERVAL = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_reserve);

        Intent time_intent = new Intent(this.getIntent());
        date = (DatePicker)findViewById(R.id.datePick);
        final TextView test_txt = (TextView)findViewById(R.id.test_test);
        btn_date = (Button)findViewById(R.id.btn_date_time);

        room_nums = time_intent.getExtras().getString("room_number");
        test_txt.setText(room_nums + "호 강의실 테스트중");

        time = (TimePicker) findViewById(R.id.time_pick);
        test_hour = (TextView) findViewById(R.id.txt_hour);
        numberPicker = (NumberPicker)time.findViewById(Resources.getSystem().getIdentifier("minute","id","android"));
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue((60/TIME_INTERVAL)-1);
        List<String> minuteVal = new ArrayList<String>();
        for(int i=0;i<60;i+=30){
            minuteVal.add(String.format("%2d",i));
        }
        numberPicker.setDisplayedValues(minuteVal.toArray(new String[0]));

        current_time = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy");
        sdf.format(current_time.getTime());
        String today = sdf.format(current_time);
        test_txt.setText(today);


        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dHour == -1)
                    Toast.makeText(getApplicationContext(),"시간을 입력해 주시기 바랍니다",Toast.LENGTH_SHORT).show();
                else if(dYear == -1)
                    Toast.makeText(getApplicationContext(),"날짜를 입력해 주시기 바랍니다.", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dlg = new AlertDialog.Builder(TimeReserveActivity.this);
                dlg.setTitle("예약 화면");
                dlg.setMessage(dYear+"년 "+dMonth+"월 "+dDay +"일\n"+dHour+"시 "+dMinute+"분 예약이 맞습니까?");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        }
                });
                dlg.setNegativeButton("취소",null);
                dlg.show();


            }
        });


        date.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dYear = year;
                dMonth = monthOfYear +1;
                dDay = dayOfMonth;
                test_txt.setText(dYear+"년 " + dMonth +"월 " +dDay +"일 ");
            }
        });

        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                dHour = hourOfDay;
                dMinute = minute*30 ;       //15분 단위로설정
                test_hour.setText(dHour +"시 "+dMinute+"분");
            }
        });

    }
}

