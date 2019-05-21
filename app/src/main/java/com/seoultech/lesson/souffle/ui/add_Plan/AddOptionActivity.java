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

    EditText edit_name, edit_number, edit_time, edit_objective, edit_people_number, edit_room_num;
    Button btn_back_to_time_reserve, btn_commit_reserve;
    String room_num;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_option);
        backPressCloseHandler = new BackPressCloseHandler(this);

        Intent intent = new Intent(this.getIntent());
        room_num = intent.getExtras().getString("room_numbers");

        edit_name = (EditText)findViewById(R.id.edit_name);
        edit_number = (EditText)findViewById(R.id.edit_number);
        edit_time = (EditText)findViewById(R.id.edit_time);
        edit_objective = (EditText)findViewById(R.id.edit_object);
        edit_people_number = (EditText)findViewById(R.id.edit_people_number);
        edit_room_num = (EditText)findViewById(R.id.edit_room);

        btn_back_to_time_reserve = (Button)findViewById(R.id.btn_back_to_time_reserve);
        btn_commit_reserve = (Button)findViewById(R.id.btn_commit_reserve);

        int year = intent.getExtras().getInt("reserve_year");
        int month = intent.getExtras().getInt("reserve_month");
        int day = intent.getExtras().getInt("reserve_day");
        int hour = intent.getExtras().getInt("reserve_hour");
        int minute = intent.getExtras().getInt("reserve_minute");

        edit_time.setText(year + "년 " + month + "월 " +
                day + "일\n" + hour + "시 " + minute + "분");
        edit_room_num.setText(room_num + "호");

        btn_back_to_time_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddOptionActivity.super.onBackPressed();
            }
        });


        btn_commit_reserve.setOnClickListener(new View.OnClickListener() {
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