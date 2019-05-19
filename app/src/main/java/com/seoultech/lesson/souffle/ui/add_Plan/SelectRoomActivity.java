package com.seoultech.lesson.souffle.ui.add_Plan;

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
import com.seoultech.lesson.souffle.ui.add_Plan.TimeReserveActivity;
import com.seoultech.lesson.souffle.ui.login.LoginActivity;
import com.seoultech.lesson.souffle.ui.viewing.FloorPlanActivity;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SelectRoomActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Button btn_109, btn_111, btn_113, btn_115;
    Button btn_209, btn_211, btn_213, btn_215;
    Button btn_301, btn_305, btn_309;
    Button btn_401, btn_405, btn_409;
    Button btn_501, btn_505, btn_509;
    Button btn_b101, btn_b105, btn_b109;


    Button btn_layer_select;
    Button btn_plan;
    Intent First_floor_intent;
    Button btn_Return_Main;
    Spinner spinner_layer;
    String[] floor_layer;
    GridLayout grid_1st_floor, grid_second_floor, grid_third_floor,
               grid_fourth_floor, grid_fifth_floor, grid_b1_floor;
    Intent to_floor_plan_intent;
    Intent to_time_reserve_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);

        First_floor_intent = new Intent(this.getIntent());
        spinner_layer = (Spinner) findViewById(R.id.spinner_layer);

        btn_layer_select = (Button) findViewById(R.id.btn_layer_select);
        btn_Return_Main = (Button) findViewById(R.id.btn_Return_Main);

        btn_109 = (Button) findViewById(R.id.btn_109);
        btn_111 = (Button) findViewById(R.id.btn_111);
        btn_113 = (Button) findViewById(R.id.btn_113);
        btn_115 = (Button) findViewById(R.id.btn_115);

        btn_209 = (Button) findViewById(R.id.btn_209);
        btn_211 = (Button) findViewById(R.id.btn_211);
        btn_213 = (Button) findViewById(R.id.btn_213);
        btn_215 = (Button) findViewById(R.id.btn_215);

        btn_301 = (Button) findViewById(R.id.btn_301);
        btn_305 = (Button) findViewById(R.id.btn_305);
        btn_309 = (Button) findViewById(R.id.btn_309);

        btn_401 = (Button) findViewById(R.id.btn_301);
        btn_405 = (Button) findViewById(R.id.btn_305);
        btn_409 = (Button) findViewById(R.id.btn_309);

        btn_501 = (Button) findViewById(R.id.btn_301);
        btn_505 = (Button) findViewById(R.id.btn_305);
        btn_509 = (Button) findViewById(R.id.btn_309);

        btn_b101 = (Button) findViewById(R.id.btn_b101);
        btn_b105 = (Button) findViewById(R.id.btn_b105);
        btn_b109 = (Button) findViewById(R.id.btn_b109);

        btn_plan = (Button) findViewById(R.id.btn_plan);

        grid_1st_floor = (GridLayout) findViewById(R.id.grid_first_select);
        grid_second_floor = (GridLayout) findViewById(R.id.grid_second_floor);
        grid_third_floor = (GridLayout)findViewById(R.id.grid_third_floor);
        grid_fourth_floor = (GridLayout)findViewById(R.id.grid_fourth_floor);
        grid_fifth_floor = (GridLayout)findViewById(R.id.grid_fifth_floor);
        grid_b1_floor = (GridLayout)findViewById(R.id.grid_b1_floor);


        to_floor_plan_intent = new Intent(getApplicationContext(), FloorPlanActivity.class);
        to_time_reserve_intent = new Intent(getApplicationContext(), TimeReserveActivity.class);

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

        btn_109.setOnClickListener(this);        btn_111.setOnClickListener(this);
        btn_113.setOnClickListener(this);        btn_115.setOnClickListener(this);

        btn_209.setOnClickListener(this);        btn_211.setOnClickListener(this);
        btn_213.setOnClickListener(this);        btn_215.setOnClickListener(this);

        btn_301.setOnClickListener(this);        btn_305.setOnClickListener(this);
        btn_309.setOnClickListener(this);

        btn_401.setOnClickListener(this);        btn_405.setOnClickListener(this);
        btn_409.setOnClickListener(this);

        btn_501.setOnClickListener(this);        btn_505.setOnClickListener(this);
        btn_509.setOnClickListener(this);

        btn_b101.setOnClickListener(this);        btn_b105.setOnClickListener(this);
        btn_b109.setOnClickListener(this);

    }

    //(버튼)방 선택시 해당 방의 번호 넘김. 코드는 나중에 추가 가능
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_109:
                to_time_reserve_intent.putExtra("room_number","109");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_111:
                to_time_reserve_intent.putExtra("room_number","111");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_113:
                to_time_reserve_intent.putExtra("room_number","113");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_115:
                to_time_reserve_intent.putExtra("room_number","115");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_209:
                to_time_reserve_intent.putExtra("room_number","209");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_211:
                to_time_reserve_intent.putExtra("room_number","211");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_213:
                to_time_reserve_intent.putExtra("room_number","213");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_215:
                to_time_reserve_intent.putExtra("room_number","215");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_301:
                to_time_reserve_intent.putExtra("room_number","301");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_305:
                to_time_reserve_intent.putExtra("room_number","305");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_309:
                to_time_reserve_intent.putExtra("room_number","309");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_401:
                to_time_reserve_intent.putExtra("room_number","401");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_405:
                to_time_reserve_intent.putExtra("room_number","405");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_409:
                to_time_reserve_intent.putExtra("room_number","409");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_501:
                to_time_reserve_intent.putExtra("room_number","501");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_505:
                to_time_reserve_intent.putExtra("room_number","505");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_509:
                to_time_reserve_intent.putExtra("room_number","509");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_b101:
                to_time_reserve_intent.putExtra("room_number","b101");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_b105:
                to_time_reserve_intent.putExtra("room_number","b105");
                startActivity(to_time_reserve_intent);
                break;
            case R.id.btn_b109:
                to_time_reserve_intent.putExtra("room_number","b109");
                startActivity(to_time_reserve_intent);
                break;
        }
    }

    //case 1부터 B1, 1, 2, 3, 4, 5층 순서임
    //어떤 강의실이 이용가능한지 모르니 일단 임의로 대충 만들어둠
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View v, int i, long l) {
        if (i == 0)
            btn_plan.setVisibility(INVISIBLE);
        else
            btn_plan.setVisibility(VISIBLE);
        switch (i) {
            case 1:
                grid_1st_floor.setVisibility(INVISIBLE);
                grid_second_floor.setVisibility(INVISIBLE);
                grid_third_floor.setVisibility(INVISIBLE);
                grid_fourth_floor.setVisibility(INVISIBLE);
                grid_fifth_floor.setVisibility(INVISIBLE);
                grid_b1_floor.setVisibility(VISIBLE);
                btn_plan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        to_floor_plan_intent.putExtra("floor_number",0);
                        to_floor_plan_intent.putExtra("floor_plan",1);
                        startActivity(to_floor_plan_intent);
                    }
                });
                break;
            case 2:
                grid_1st_floor.setVisibility(VISIBLE);
                grid_second_floor.setVisibility(INVISIBLE);
                grid_third_floor.setVisibility(INVISIBLE);
                grid_fourth_floor.setVisibility(INVISIBLE);
                grid_fifth_floor.setVisibility(INVISIBLE);
                grid_b1_floor.setVisibility(INVISIBLE);
                btn_plan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        to_floor_plan_intent.putExtra("floor_number",1);
                        to_floor_plan_intent.putExtra("floor_plan",1);
                        startActivity(to_floor_plan_intent);
                    }
                });
                break;
            case 3:
                grid_1st_floor.setVisibility(INVISIBLE);
                grid_second_floor.setVisibility(VISIBLE);
                grid_third_floor.setVisibility(INVISIBLE);
                grid_fourth_floor.setVisibility(INVISIBLE);
                grid_fifth_floor.setVisibility(INVISIBLE);
                grid_b1_floor.setVisibility(INVISIBLE);
                btn_plan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        to_floor_plan_intent.putExtra("floor_number",2);
                        to_floor_plan_intent.putExtra("floor_plan",1);
                        startActivity(to_floor_plan_intent);
                    }
                });
                break;
            case 4:
                grid_1st_floor.setVisibility(INVISIBLE);
                grid_second_floor.setVisibility(INVISIBLE);
                grid_third_floor.setVisibility(VISIBLE);
                grid_fourth_floor.setVisibility(INVISIBLE);
                grid_fifth_floor.setVisibility(INVISIBLE);
                grid_b1_floor.setVisibility(INVISIBLE);
                btn_plan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        to_floor_plan_intent.putExtra("floor_number",3);
                        to_floor_plan_intent.putExtra("floor_plan",1);
                        startActivity(to_floor_plan_intent);
                    }
                });
                break;
            case 5:
                grid_1st_floor.setVisibility(INVISIBLE);
                grid_second_floor.setVisibility(INVISIBLE);
                grid_third_floor.setVisibility(INVISIBLE);
                grid_fourth_floor.setVisibility(VISIBLE);
                grid_fifth_floor.setVisibility(INVISIBLE);
                grid_b1_floor.setVisibility(INVISIBLE);
                btn_plan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        to_floor_plan_intent.putExtra("floor_number",4);
                        to_floor_plan_intent.putExtra("floor_plan",1);
                        startActivity(to_floor_plan_intent);
                    }
                });
                break;
            case 6:
                grid_1st_floor.setVisibility(INVISIBLE);
                grid_second_floor.setVisibility(INVISIBLE);
                grid_third_floor.setVisibility(INVISIBLE);
                grid_fourth_floor.setVisibility(INVISIBLE);
                grid_fifth_floor.setVisibility(VISIBLE);
                grid_b1_floor.setVisibility(INVISIBLE);
                btn_plan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        to_floor_plan_intent.putExtra("floor_number",5);
                        to_floor_plan_intent.putExtra("floor_plan",1);
                        startActivity(to_floor_plan_intent);
                    }
                });
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(),"층을 선택해 주세요",Toast.LENGTH_SHORT).show();
    }
}

