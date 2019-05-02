package com.seoultech.lesson.souffle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.seoultech.lesson.souffle.R;

public class SelectMenuActivity extends AppCompatActivity {

    Button btn_reserve, btn_comfirm, btn_update, btn_cancel, btn_exit;

    Intent select_menu_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);


        btn_reserve = (Button)findViewById(R.id.btn_reserve);
        select_menu_intent = new Intent(this.getIntent());

        btn_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent To_layer_intent = new Intent(getApplicationContext(), SelectRoomActivity.class);
                startActivity(To_layer_intent);
            }
        });

    }

}
