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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.adapter.ItemData;
import com.seoultech.lesson.souffle.ui.adapter.ReservationDeleteAdapter;
import com.seoultech.lesson.souffle.ui.adapter.ReservationListAdapter;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnItemClick;

public class DeletePlanActivity extends AppCompatActivity implements View.OnClickListener {
    private AppController appController;
    private User user;
    private Animation pullFromRight, pushToRight;
    private Boolean isFabOpen = false;
    private FloatingActionButton fabMenu;
    private LinearLayout slideLayout;
    private FrameLayout frameSelectMenu;
    private Button btnToMain;
    private Button btnUserInfo, btnInIt, btnCheckTime;
    private ListView listView_delete;
    private Button btnBackToMain;
    private BackPressCloseHandler backPressCloseHandler;
    private ReservationDeleteAdapter reservationDeleteAdapter;


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
        ArrayList<ItemData> deleteList = new ArrayList<>();

        btnToMain = (Button)findViewById(R.id.btn_to_main_in_delete_plan);
        fabMenu = (FloatingActionButton) findViewById(R.id.fab_in_delete_plan);

        btnUserInfo = (Button)findViewById(R.id.btn_userInfo_in_delete_plan);
        btnUserInfo.setText(user.getName() + "님\n" + "학번 : " + user.getStudentNumber() + "\n" + user.getMajor());

        pushToRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pushtoright);
        pullFromRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.pullfromright);

        slideLayout = (LinearLayout)findViewById(R.id.slide_layout_in_delete_plan);
        frameSelectMenu = (FrameLayout)findViewById(R.id.delete_plan_frame);
        frameSelectMenu.bringChildToFront(slideLayout);

        backPressCloseHandler = new BackPressCloseHandler(this);

        btnBackToMain = (Button)findViewById(R.id.btn_back_main);


        ArrayList<ItemData> reservationList = new ArrayList<>();
        listView_delete = (ListView)findViewById(R.id.listView_reservation_delete);


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
                try{
                    final ReservationDeleteAdapter tempAdapter = new ReservationDeleteAdapter(reservations);
                    listView_delete.setAdapter(tempAdapter);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    progressDialogInDO.dismiss();
                }
            }
        }.execute(user.getStudentNumber());

        reservationDeleteAdapter = (ReservationDeleteAdapter) listView_delete.getAdapter();

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

}
