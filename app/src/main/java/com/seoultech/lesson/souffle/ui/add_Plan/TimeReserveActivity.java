package com.seoultech.lesson.souffle.ui.add_Plan;


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

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

//해당 강의실을 예약할 날짜와 시간을 고르는 액티비티

public class TimeReserveActivity extends AppCompatActivity {

    TimePicker time;
    TextView test_hour, test_txt, test_day_between;
    String room_nums;
    DatePicker date;
    Button btn_date;
    Date current_time;
    Date reserve_time;
    Calendar today_cal, reserve_cal;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_reserve);

        Intent time_intent = new Intent(this.getIntent());

        room_nums = time_intent.getExtras().getString("room_number");
  //      test_txt.setText(room_nums + "호 강의실 테스트중");

        final TextView test_txt = (TextView)findViewById(R.id.test_test);
        date = (DatePicker)findViewById(R.id.datePick);
        btn_date = (Button)findViewById(R.id.btn_date_time);
        test_day_between = (TextView)findViewById(R.id.test_between_day);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd hh mm");
       // SimpleDateFormat reserve_sdf = new SimpleDateFormat("yyyy MM dd hh mm");
        sdf.format(current_time.getTime());
        String today = sdf.format(current_time);
        //test_day_between.setText(today);



    /*  reserve_cal.set(Calendar.YEAR,dYear);
        reserve_cal.set(Calendar.MONTH,dMonth);
        reserve_cal.set(Calendar.DAY_OF_MONTH,dDay);
        reserve_cal.set(Calendar.HOUR_OF_DAY,dHour);
        reserve_cal.set(Calendar.MINUTE,dMinute);
        test_txt.setText(reserve_cal.get(Calendar.YEAR) - today_cal.get(Calendar.YEAR));*/

   /*   try {
            Date date1 = sdf.parse(today);
            Date date2 = sdf.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

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
                            startActivity(to_option_intent);
                        }
                    });
                    dlg.setNegativeButton("취소", null);
                    dlg.show();
                }
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

    public int getReturn(int n){
        return n;
    }


}


