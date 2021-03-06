package com.seoultech.lesson.souffle.ui.planUpdate;

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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.Setting.SettingActivity;
import com.seoultech.lesson.souffle.ui.adapter.ModifyListener;
import com.seoultech.lesson.souffle.ui.adapter.ReservationModifyAdapter;
import com.seoultech.lesson.souffle.ui.add_plan.AddOptionActivity;
import com.seoultech.lesson.souffle.ui.login.LoginActivity;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ModifyPlanActivity extends AppCompatActivity implements View.OnClickListener, ModifyListener {
    private AppController appController;
    private User user;
    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private TextView btnToMain;
    private TextView btnUserInfo;
    private Button btnInIt;
    private Button btnCheckTime;
    private ListView listView_modify;
    private Button btnBackToMain;
    private BackPressCloseHandler backPressCloseHandler;
    private ReservationModifyAdapter reservationModifyAdapter;
    private List<Reservation> reservations;
    private TextView btnSettings, btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

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


        setContentView(R.layout.activity_modify_plan);
        appController = AppController.getInstance();
        Intent deletePlanIntent = new Intent(this.getIntent());
        user = (User)deletePlanIntent.getSerializableExtra("user");

        btnToMain = (TextView)findViewById(R.id.btn_to_main_in_modify_plan);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_modify_plan);

        btnUserInfo = (TextView)findViewById(R.id.btn_userInfo_in_modify_plan);
        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_modify_plan);
        frameSelectMenu = (FrameLayout)findViewById(R.id.modify_plan_frame);
        frameSelectMenu.bringChildToFront(slideLayout);

        backPressCloseHandler = new BackPressCloseHandler(this);

        btnSettings = (TextView)findViewById(R.id.btn_setting_in_modify_plan);
        btnLogout = (TextView)findViewById(R.id.btn_logout_in_modify_plan);

        listView_modify = (ListView)findViewById(R.id.listView_reservation_modify);
        reservations = new ArrayList<>();
        reservationModifyAdapter = new ReservationModifyAdapter(reservations, this);
        listView_modify.setAdapter(reservationModifyAdapter);

        ProgressDialog progressDialogInDO = new ProgressDialog(ModifyPlanActivity.this);

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSettingIntent = new Intent(getApplicationContext(), SettingActivity.class);
                toSettingIntent.putExtra("user",user);
                startActivity(toSettingIntent);
                anim();
            }
        });

        new AsyncTask<Integer, Integer, List<Reservation>>() {
            @Override
            protected void onPreExecute() {
                // Loading
                progressDialogInDO.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialogInDO.setMessage("Logining");
                progressDialogInDO.show();
                super.onPreExecute();
            }

            @Override
            protected List<Reservation> doInBackground(Integer... integers) {
                // 예약목록 불러옴
                return appController.readReservationByStudentNumber(integers[0]);
            }

            @Override
            protected void onPostExecute(List<Reservation> reservations) {
                // 예약목록 뿌리기
                /*try{
                    final ReservationModifyAdapter reservationListAdapter = new ReservationModifyAdapter(reservations);
                    listView_modify.setAdapter(reservationListAdapter);

                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    progressDialogInDO.dismiss();
                }*/
                ModifyPlanActivity.this.reservations.clear();
                ModifyPlanActivity.this.reservations.addAll(reservations);
                reservationModifyAdapter.notifyDataSetChanged();
                progressDialogInDO.dismiss();
            }
        }.execute(user.getStudentNumber());



        listView_modify.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //    System.out.println(reservationModifyAdapter.getItemList().get(position).toString());
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

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder logoutDlg = new AlertDialog.Builder(ModifyPlanActivity.this);
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

        fabMenu.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView txt_modify_plan = findViewById(R.id.txt_modify_list);
        txt_modify_plan.setText(R.string.modify_plan);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_in_modify_plan:
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

    @Override
    public void onModify(Reservation reservation) {
        // 다음 액티비티 (Reservation, User)
        Intent toAddActionIntent_inModify = new Intent(getApplicationContext(), AddOptionActivity.class);
        toAddActionIntent_inModify.putExtra("User_modify",user);
        toAddActionIntent_inModify.putExtra("bool_modify",true);
        toAddActionIntent_inModify.putExtra("user",user);
        toAddActionIntent_inModify.putExtra("reservation_modify",reservation);
        startActivity(toAddActionIntent_inModify);

    }
}
