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
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.login.LoginActivity;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.option.CheckAdapter;
import com.seoultech.lesson.souffle.ui.option.CheckData;
import com.seoultech.lesson.souffle.ui.option.ItemData;
import com.seoultech.lesson.souffle.ui.option.TimeListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;


//해당 강의실을 예약할 날짜와 시간을 고르는 액티비티

public class TimeReserveActivity extends AppCompatActivity implements View.OnClickListener{

    private BackPressCloseHandler backPressCloseHandler;
    private ListView chk_list;
    private CoordinatorLayout layout_cor;
    private NestedScrollView scrollView;
    private LinearLayout slideLayout;
    private TextView txtYearDlg, txtMonthDlg, txtDayDlg, txtRoomDlg;
    private String roomNumber;
    private Button btnDate, btnPlanInTime, btnSelectDate;
    private Date currentTime;
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
    private int fromTime = -1;
    private int toTime = -1;
    private String buildingName;
    private final int TIME_INTERVAL = 30;
    private FloatingActionButton fabMain;
    private Animation pullFromRight, pushToRight;
    private Animation pullFromLeft, pushToLeft;
    private Boolean isFabOpen = false;
    private ScrollView scrollView_timereserve;

    private ListView listview = null;
    private Button btnInIt, btnTimeReserve;
    private ArrayList<Integer> linearArr = new ArrayList<>();

    private Reservation reservation;

    // Date 선택시 화면 Refresh
    // Refresh 내용 : Reservation 받아와서 (임시로 생성) + 예약된 시간들 enable

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
        buildingName = timeReserveIntent.getExtras().getString("building_name_to_time_reserve");
        scrollView_timereserve = (ScrollView)findViewById(R.id.scrollView_timereserve);
        ArrayList<ItemData> timeList = new ArrayList<>();
        listview = (ListView)findViewById(R.id.timeListView);
        for (int i=0; i<15; ++i)
        {
            ItemData timeItem = new ItemData();
            timeItem.time = (i+9) + "~" + (i+10) + "시";
            timeItem.isEnable = true;
            timeList.add(timeItem);
        }
        final TimeListAdapter oAdapter = new TimeListAdapter(timeList);
        listview.setAdapter(oAdapter);



        btnInIt = findViewById(R.id.btnInIt);
        btnTimeReserve = findViewById(R.id.btnTimeReserve);

        btnUserInfo = (Button)findViewById(R.id.btn_userInfo_in_timereserve);
        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        fabMain = (FloatingActionButton) findViewById(R.id.fab_in_time_reserve);
        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_time_reserve);

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

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CheckBox checkBox = view.findViewById(R.id.customCheckbox);
                if(!checkBox.isEnabled()) return; // 비활성화 리스트일경우 반응안함
                checkBox.setChecked(!checkBox.isChecked()); // 체크 (체크 -> 해제 / 해제 -> 체크)
                linearArr.add(position); // 연속성 판단을 위한 변수
            }
        });


        btnInIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0; i<10; i++){
                    ItemData data = (ItemData) oAdapter.getItem(i);
                    data.isChecked = false;
                }
                oAdapter.notifyDataSetChanged();
            }
        });



        btnTimeReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0;i<15;i++){
                    boolean boolChk = timeList.get(i).isChecked;
                    if(boolChk){
                        fromTime = (i+9);
                        break;
                    }
                }
                for(int i=15;i>1;i--){
                    boolean boolChk = timeList.get(i-1).isChecked;
                    if(boolChk) {
                        toTime = (i + 9);
                        Toast.makeText(getApplicationContext(),"fromTime : " + fromTime + "\n"
                                + "toTime : " + toTime, Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                if(isLinear(linearArr)){ // 연속성 판단 (연속)
                    Toast.makeText(TimeReserveActivity.this, "GOOOOOOD", Toast.LENGTH_SHORT).show();
                    oAdapter.notifyDataSetChanged(); // 리스트뷰 refresh
                }else{ // 연속성 판단 (비연속)
                    Toast.makeText(TimeReserveActivity.this, "NOOOOOOOP", Toast.LENGTH_SHORT).show();
                    ArrayList<ItemData> data = (ArrayList<ItemData>) oAdapter.getItemList();
                    for(ItemData iterater : data){ // 리스트뷰 체크 해제상태로 변경
                        iterater.isChecked = false;
                    }
                    oAdapter.notifyDataSetChanged(); // 리스트뷰 refresh
                }
                linearArr.clear(); // 연속성 판단 변수 초기화
            }
        });


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
                if (dYear == -1 || fromTime == -1 || toTime == -1)
                    Toast.makeText(getApplicationContext(), "날짜나 시간을 제대로 입력해 주십시오", Toast.LENGTH_SHORT).show();
                else {
                    dlg.setMessage(dYear + "년 " + dMonth + "월 " + dDay + "일\n" +
                            fromTime + "시부터 " + toTime + "시까지 예약이 맞습니까?");
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //확인 누르면 해당 시간, 날짜정보를 DB에 저장하면 됨. dHour, dMinute, dYear, dMonth, dDay 정보
                            Toast.makeText(getApplicationContext(), dYear + "년 " + dMonth + "월 " +
                                    dDay + "일\n" +  fromTime + "시부터 " + toTime + "시까지로 예약하였습니다.", Toast.LENGTH_SHORT).show();
                            tYear = getReturn(dYear);
                            tMonth = getReturn(dMonth);
                            tDay = getReturn(dDay);
                            Intent toAddOptionIntent = new Intent(getApplicationContext(), AddOptionActivity.class);
                            toAddOptionIntent.putExtra("reserve_year", tYear);
                            toAddOptionIntent.putExtra("reserve_month", tMonth);
                            toAddOptionIntent.putExtra("reserve_day", tDay);
                            toAddOptionIntent.putExtra("room_numbers", roomNumber);
                            toAddOptionIntent.putExtra("fromTime",fromTime);
                            toAddOptionIntent.putExtra("toTime",toTime);
                            toAddOptionIntent.putExtra("user",user);
                            toAddOptionIntent.putExtra("building_name",buildingName);
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

    public boolean isLinear(ArrayList<Integer> arr){
        try{
            Collections.sort(arr);
            int temp = arr.get(0);
            for(int i=1; i<arr.size(); i++){
                if(arr.get(i) == temp+1) {
                    temp = arr.get(i);
                    continue;
                }
                return false;
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
        }
        return true;
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


