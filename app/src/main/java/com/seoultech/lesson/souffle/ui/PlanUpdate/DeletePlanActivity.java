package com.seoultech.lesson.souffle.ui.PlanUpdate;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import com.seoultech.lesson.souffle.ui.adapter.DeleteListener;
import com.seoultech.lesson.souffle.ui.adapter.ReservationDeleteAdapter;
import com.seoultech.lesson.souffle.ui.adapter.ReservationModifyAdapter;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

import java.util.ArrayList;
import java.util.List;

public class DeletePlanActivity extends AppCompatActivity implements View.OnClickListener, DeleteListener {
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
    private ListView listView_delete;
    private Button btnBackToMain;
    private BackPressCloseHandler backPressCloseHandler;
    private ReservationDeleteAdapter reservationDeleteAdapter;
    private List<Reservation> reservations;
    private TextView btnSettings, btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_delete_plan);
        appController = AppController.getInstance();
        Intent deletePlanIntent = new Intent(this.getIntent());
        user = (User)deletePlanIntent.getSerializableExtra("user");

        btnToMain = (TextView)findViewById(R.id.btn_to_main_in_delete_plan);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_delete_plan);

        btnUserInfo = (TextView)findViewById(R.id.btn_userInfo_in_delete_plan);
        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());
        btnSettings = (TextView)findViewById(R.id.btn_setting_in_delete_plan);
        btnLogout = (TextView)findViewById(R.id.btn_logout_in_delete_plan);

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_delete_plan);
        frameSelectMenu = (FrameLayout)findViewById(R.id.delete_plan_frame);
        frameSelectMenu.bringChildToFront(slideLayout);

        backPressCloseHandler = new BackPressCloseHandler(this);

        btnBackToMain = (Button)findViewById(R.id.btn_back_main);

        listView_delete = (ListView)findViewById(R.id.listView_reservation_delete);
        reservations = new ArrayList<>();
        reservationDeleteAdapter = new ReservationDeleteAdapter(reservations, this);
        listView_delete.setAdapter(reservationDeleteAdapter);

        ProgressDialog progressDialogInDO = new ProgressDialog(DeletePlanActivity.this);

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
                DeletePlanActivity.this.reservations.clear();
                DeletePlanActivity.this.reservations.addAll(reservations);
                reservationDeleteAdapter.notifyDataSetChanged();
                progressDialogInDO.dismiss();
            }
        }.execute(user.getStudentNumber());

        listView_delete.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(reservationDeleteAdapter.getItemList().get(position).toString());
            }
        });

        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                toMainMenuIntent.putExtra("user",user);
                startActivity(toMainMenuIntent);
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
            case R.id.fab_in_delete_plan:
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
    public void onDelete() {
        reservationDeleteAdapter.notifyDataSetChanged();
    }
}
