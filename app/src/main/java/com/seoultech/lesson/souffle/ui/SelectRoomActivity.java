package com.seoultech.lesson.souffle.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SelectRoomActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btn_109, btn_111, btn_113, btn_115;
    Button btn_layer_select;
    Button btn_plan;
    Intent First_floor_intent;
    Button btn_Return_Main;
    Spinner spinner_layer;
    String[] floor_layer;
    GridLayout grid_1st_floor, grid_second_floor;
    Intent to_floor_plan_intent;
    Intent to_time_reserve_intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);

        btn_layer_select = (Button) findViewById(R.id.btn_layer_select);
        spinner_layer = (Spinner) findViewById(R.id.spinner_layer);
        First_floor_intent = new Intent(this.getIntent());
        btn_Return_Main = (Button) findViewById(R.id.btn_Return_Main);
        btn_109 = (Button) findViewById(R.id.btn_109);
        btn_111 = (Button) findViewById(R.id.btn_111);
        btn_113 = (Button) findViewById(R.id.btn_113);
        btn_115 = (Button) findViewById(R.id.btn_115);
        btn_plan = (Button) findViewById(R.id.btn_plan);
        grid_1st_floor = (GridLayout) findViewById(R.id.grid_first_select);
        grid_second_floor = (GridLayout) findViewById(R.id.grid_second_floor);
        to_floor_plan_intent = new Intent(getApplicationContext(),FloorPlanActivity.class);
        to_time_reserve_intent = new Intent(getApplicationContext(),TimeReserveActivity.class);

        spinner_layer.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        floor_layer = new String[]{"선택하세요", "B1F", "1F", "2F", "3F", "4F", "5F"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, floor_layer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_layer.setAdapter(adapter);

        btn_Return_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent To_Main_intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(To_Main_intent);
            }
        });

//방 선택 버튼들
        btn_109.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_floor_plan_intent.putExtra("room_number","109");
                to_time_reserve_intent.putExtra("room_number","109");
                startActivity(to_time_reserve_intent);

            }
        });

        btn_111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_floor_plan_intent.putExtra("room_number","111");
                to_time_reserve_intent.putExtra("room_number","111");
                startActivity(to_time_reserve_intent);

            }
        });

        btn_113.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_floor_plan_intent.putExtra("room_number","113");
                to_time_reserve_intent.putExtra("room_number","113");
                startActivity(to_time_reserve_intent);
            }
        });

        btn_115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to_floor_plan_intent.putExtra("room_number","115");
                to_time_reserve_intent.putExtra("room_number","115");
                startActivity(to_time_reserve_intent);

            }
        });

    }

    //층 선택 스크롤바
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View v, int i, long l) {
        if (i == 0 || i == 1)
            btn_plan.setVisibility(INVISIBLE);
        else
            btn_plan.setVisibility(VISIBLE);
        switch (i) {
            case 2:
                grid_second_floor.setVisibility(INVISIBLE);
                grid_1st_floor.setVisibility(VISIBLE);
                btn_plan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        to_floor_plan_intent.putExtra("floor_number",1);
                        startActivity(to_floor_plan_intent);
                    }
                });
                break;
            case 3:
                grid_1st_floor.setVisibility(INVISIBLE);
                grid_second_floor.setVisibility(VISIBLE);
                btn_plan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        to_floor_plan_intent.putExtra("floor_number",2);
                        startActivity(to_floor_plan_intent);
                    }
                });
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

