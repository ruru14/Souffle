package com.seoultech.lesson.souffle.ui.add_Plan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;

public class AddOptionActivity extends AppCompatActivity {

    EditText editName, editNumber, editTime, editObjective, editPeopleNumber, editRoomNum;
    Button btnBackToTimeReserve, btnCommitReserve;
    String roomNum;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_add_option);
        backPressCloseHandler = new BackPressCloseHandler(this);

        Intent intent = new Intent(this.getIntent());
        roomNum = intent.getExtras().getString("room_numbers");

        editName = (EditText)findViewById(R.id.edit_name);
        editNumber = (EditText)findViewById(R.id.edit_number);
        editTime = (EditText)findViewById(R.id.edit_time);
        editObjective = (EditText)findViewById(R.id.edit_object);
        editPeopleNumber = (EditText)findViewById(R.id.edit_people_number);
        editRoomNum = (EditText)findViewById(R.id.edit_room);

        btnBackToTimeReserve = (Button)findViewById(R.id.btn_back_to_time_reserve);
        btnCommitReserve = (Button)findViewById(R.id.btn_commit_reserve);

        int year = intent.getExtras().getInt("reserve_year");
        int month = intent.getExtras().getInt("reserve_month");
        int day = intent.getExtras().getInt("reserve_day");
        int hour = intent.getExtras().getInt("reserve_hour");
        int minute = intent.getExtras().getInt("reserve_minute");

        editTime.setText(year + "년 " + month + "월 " +
                day + "일\n" + hour + "시 " + minute + "분");
        editRoomNum.setText(roomNum + "호");

        btnBackToTimeReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOptionActivity.super.onBackPressed();
            }
        });


        btnCommitReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder commit_dlg =  new AlertDialog.Builder(AddOptionActivity.this);
                commit_dlg.setTitle("최종 확인");
                commit_dlg.setIcon(R.drawable.ic_launcher_foreground);
                commit_dlg.setMessage("해당 내용으로 예약하시겠습니까?");
                commit_dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"예약되었습니다",Toast.LENGTH_SHORT).show();
                        Intent to_menu_intent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                        startActivity(to_menu_intent);
                    }
                });
            }
        });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }
}
