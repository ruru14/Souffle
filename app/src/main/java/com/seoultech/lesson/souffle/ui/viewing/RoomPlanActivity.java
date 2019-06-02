package com.seoultech.lesson.souffle.ui.viewing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

public class RoomPlanActivity extends AppCompatActivity {

    Button btnBackToRoomSelect;
    TextView txtRoomGuide;
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

        txtRoomGuide = (TextView)findViewById(R.id.txt_room_guide);
    btnBackToRoomSelect = (Button)findViewById(R.id.btn_back_to_room_select);
    Intent get_intent = new Intent(this.getIntent());

    txtRoomGuide.setText(get_intent.getExtras().getString("room_number_to_plan") + "호 강의실 사진");

    btnBackToRoomSelect.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }
}
