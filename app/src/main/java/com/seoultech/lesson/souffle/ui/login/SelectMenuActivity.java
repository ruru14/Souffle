package com.seoultech.lesson.souffle.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.add_Plan.UpdatePlanActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
import com.seoultech.lesson.souffle.ui.add_Plan.SelectRoomActivity;

public class SelectMenuActivity extends AppCompatActivity {

   LinearLayout linear_plan_add, linear_plan_delete, linear_plan_modify, linear_plan_update;

    Intent select_menu_intent;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_menu);

        backPressCloseHandler = new BackPressCloseHandler(this);

        linear_plan_add = (LinearLayout)findViewById(R.id.linear_plan_add);
        linear_plan_delete = (LinearLayout)findViewById(R.id.linear_plan_delete);
        linear_plan_modify = (LinearLayout)findViewById(R.id.linear_plan_modify);
        linear_plan_update = (LinearLayout)findViewById(R.id.linear_plan_update);

        select_menu_intent = new Intent(this.getIntent());

       linear_plan_add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent to_room_select_intent = new Intent(getApplicationContext(),SelectRoomActivity.class);
               startActivity(to_room_select_intent);
           }
       });

       linear_plan_update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent to_update_plan_intent = new Intent(getApplicationContext(), UpdatePlanActivity.class);
               startActivity(to_update_plan_intent);
           }
       });

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
       backPressCloseHandler.onBackPressed();
    }
}
