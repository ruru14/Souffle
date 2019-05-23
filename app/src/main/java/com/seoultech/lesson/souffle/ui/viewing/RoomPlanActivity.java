package com.seoultech.lesson.souffle.ui.viewing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.add_Plan.SelectRoomActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

public class RoomPlanActivity extends AppCompatActivity {

    Button btn_back_to_room_select;
    TextView txt_room_guide;
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_room_plan);

        backPressCloseHandler = new BackPressCloseHandler(this);

        txt_room_guide = (TextView)findViewById(R.id.txt_room_guide);
    btn_back_to_room_select = (Button)findViewById(R.id.btn_back_to_room_select);
    Intent get_intent = new Intent(this.getIntent());

    txt_room_guide.setText(get_intent.getExtras().getString("room_number_to_plan") + "호 강의실 사진");
    btn_back_to_room_select.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent to_room_select_intent = new Intent(getApplicationContext(), SelectRoomActivity.class);
            startActivity(to_room_select_intent);
        }
    });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }
}
