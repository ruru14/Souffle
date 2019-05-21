package com.seoultech.lesson.souffle.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.add_Plan.ReserveContentsActivity;
import com.seoultech.lesson.souffle.ui.add_Plan.SelectRoomActivity;

public class SelectMenuActivity extends AppCompatActivity {

    Button btn_reserve, btn_confirm, btn_update, btn_cancel, btn_exit;

    Intent select_menu_intent;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        backPressCloseHandler = new BackPressCloseHandler(this);

        btn_reserve = (Button)findViewById(R.id.btn_reserve);
        btn_confirm = (Button)findViewById(R.id.btn_confirm);
        select_menu_intent = new Intent(this.getIntent());

        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent To_layer_intent = new Intent(getApplicationContext(), SelectRoomActivity.class);
                startActivity(To_layer_intent);
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent To_reservecontents_intent = new Intent(getApplicationContext(), ReserveContentsActivity.class);
                startActivity(To_reservecontents_intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
       backPressCloseHandler.onBackPressed();
    }
}
