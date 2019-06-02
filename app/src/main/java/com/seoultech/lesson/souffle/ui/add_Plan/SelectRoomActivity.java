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
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;
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

    private BackPressCloseHandler backPressCloseHandler;

    Button btnLayerSelect;
    Button btnPlan;
    Intent FirstFloorIntent;
    Button btnReturnMain;
    Spinner spinnerLayer;
    String[] floorLayer;
    GridLayout gridFirstFloor, gridSecondFloor, gridThirdFloor,
            gridFourthFloor, gridFifthFloor, gridB1Floor;
    Intent toFloorPlanIntent;
    Intent toTimeReserveIntent;

    private String building_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_select_room);

        backPressCloseHandler = new BackPressCloseHandler(this);
        FirstFloorIntent = new Intent(this.getIntent());
        spinnerLayer = (Spinner) findViewById(R.id.spinner_layer);

        building_name = FirstFloorIntent.getExtras().getString("building_name");

        btnLayerSelect = (Button) findViewById(R.id.btn_layer_select);
        btnReturnMain = (Button) findViewById(R.id.btn_Return_Main);

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

        btnPlan = (Button) findViewById(R.id.btn_plan);

        gridFirstFloor = (GridLayout) findViewById(R.id.grid_first_select);
        gridSecondFloor = (GridLayout) findViewById(R.id.grid_second_floor);
        gridThirdFloor = (GridLayout)findViewById(R.id.grid_third_floor);
        gridFourthFloor = (GridLayout)findViewById(R.id.grid_fourth_floor);
        gridFifthFloor = (GridLayout)findViewById(R.id.grid_fifth_floor);
        gridB1Floor = (GridLayout)findViewById(R.id.grid_b1_floor);


        toFloorPlanIntent = new Intent(getApplicationContext(), FloorPlanActivity.class);
        toTimeReserveIntent = new Intent(getApplicationContext(), TimeReserveActivity.class);

        spinnerLayer.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        floorLayer = new String[]{"선택하세요", "B1F", "1F", "2F", "3F", "4F", "5F"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, floorLayer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLayer.setAdapter(adapter);

        btnReturnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                startActivity(toMenuIntent);
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
        toTimeReserveIntent.putExtra("building_name_to_time_reserve",building_name);
        switch(v.getId()){
            case R.id.btn_109:
                toTimeReserveIntent.putExtra("room_number","109");
                break;
            case R.id.btn_111:
                toTimeReserveIntent.putExtra("room_number","111");
                break;
            case R.id.btn_113:
                toTimeReserveIntent.putExtra("room_number","113");
                break;
            case R.id.btn_115:
                toTimeReserveIntent.putExtra("room_number","115");
                break;
            case R.id.btn_209:
                toTimeReserveIntent.putExtra("room_number","209");
                break;
            case R.id.btn_211:
                toTimeReserveIntent.putExtra("room_number","211");
                break;
            case R.id.btn_213:
                toTimeReserveIntent.putExtra("room_number","213");
                break;
            case R.id.btn_215:
                toTimeReserveIntent.putExtra("room_number","215");
                break;
            case R.id.btn_301:
                toTimeReserveIntent.putExtra("room_number","301");
                break;
            case R.id.btn_305:
                toTimeReserveIntent.putExtra("room_number","305");
                break;
            case R.id.btn_309:
                toTimeReserveIntent.putExtra("room_number","309");
                break;
            case R.id.btn_401:
                toTimeReserveIntent.putExtra("room_number","401");
                break;
            case R.id.btn_405:
                toTimeReserveIntent.putExtra("room_number","405");
                break;
            case R.id.btn_409:
                toTimeReserveIntent.putExtra("room_number","409");
                break;
            case R.id.btn_501:
                toTimeReserveIntent.putExtra("room_number","501");
                break;
            case R.id.btn_505:
                toTimeReserveIntent.putExtra("room_number","505");
                break;
            case R.id.btn_509:
                toTimeReserveIntent.putExtra("room_number","509");
                break;
            case R.id.btn_b101:
                toTimeReserveIntent.putExtra("room_number","b101");
                break;
            case R.id.btn_b105:
                toTimeReserveIntent.putExtra("room_number","b105");
                break;
            case R.id.btn_b109:
                toTimeReserveIntent.putExtra("room_number","b109");
                break;
        }
        startActivity(toTimeReserveIntent);
    }

    //case 1부터 B1, 1, 2, 3, 4, 5층 순서임
    //어떤 강의실이 이용가능한지 모르니 일단 임의로 대충 만들어둠
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View v, int i, long l) {
        if (i == 0)
            btnPlan.setVisibility(INVISIBLE);
        else
            btnPlan.setVisibility(VISIBLE);
        switch (i) {
            case 0:
                gridFirstFloor.setVisibility(INVISIBLE);
                gridSecondFloor.setVisibility(INVISIBLE);
                gridThirdFloor.setVisibility(INVISIBLE);
                gridFourthFloor.setVisibility(INVISIBLE);
                gridFifthFloor.setVisibility(INVISIBLE);
                gridB1Floor.setVisibility(INVISIBLE);
                break;
            case 1:
                gridFirstFloor.setVisibility(INVISIBLE);
                gridSecondFloor.setVisibility(INVISIBLE);
                gridThirdFloor.setVisibility(INVISIBLE);
                gridFourthFloor.setVisibility(INVISIBLE);
                gridFifthFloor.setVisibility(INVISIBLE);
                gridB1Floor.setVisibility(VISIBLE);
                btnPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toFloorPlanIntent.putExtra("floor_number",0);
                        toFloorPlanIntent.putExtra("floor_plan",1);
                        startActivity(toFloorPlanIntent);
                    }
                });
                break;
            case 2:
                gridFirstFloor.setVisibility(VISIBLE);
                gridSecondFloor.setVisibility(INVISIBLE);
                gridThirdFloor.setVisibility(INVISIBLE);
                gridFourthFloor.setVisibility(INVISIBLE);
                gridFifthFloor.setVisibility(INVISIBLE);
                gridB1Floor.setVisibility(INVISIBLE);
                btnPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toFloorPlanIntent.putExtra("floor_number",1);
                        toFloorPlanIntent.putExtra("floor_plan",1);
                        startActivity(toFloorPlanIntent);
                    }
                });
                break;
            case 3:
                gridFirstFloor.setVisibility(INVISIBLE);
                gridSecondFloor.setVisibility(VISIBLE);
                gridThirdFloor.setVisibility(INVISIBLE);
                gridFourthFloor.setVisibility(INVISIBLE);
                gridFifthFloor.setVisibility(INVISIBLE);
                gridB1Floor.setVisibility(INVISIBLE);
                btnPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toFloorPlanIntent.putExtra("floor_number",2);
                        toFloorPlanIntent.putExtra("floor_plan",1);
                        startActivity(toFloorPlanIntent);
                    }
                });
                break;
            case 4:
                gridFirstFloor.setVisibility(INVISIBLE);
                gridSecondFloor.setVisibility(INVISIBLE);
                gridThirdFloor.setVisibility(VISIBLE);
                gridFourthFloor.setVisibility(INVISIBLE);
                gridFifthFloor.setVisibility(INVISIBLE);
                gridB1Floor.setVisibility(INVISIBLE);
                btnPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toFloorPlanIntent.putExtra("floor_number",3);
                        toFloorPlanIntent.putExtra("floor_plan",1);
                        startActivity(toFloorPlanIntent);
                    }
                });
                break;
            case 5:
                gridFirstFloor.setVisibility(INVISIBLE);
                gridSecondFloor.setVisibility(INVISIBLE);
                gridThirdFloor.setVisibility(INVISIBLE);
                gridFourthFloor.setVisibility(VISIBLE);
                gridFifthFloor.setVisibility(INVISIBLE);
                gridB1Floor.setVisibility(INVISIBLE);
                btnPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toFloorPlanIntent.putExtra("floor_number",4);
                        toFloorPlanIntent.putExtra("floor_plan",1);
                        startActivity(toFloorPlanIntent);
                    }
                });
                break;
            case 6:
                gridFirstFloor.setVisibility(INVISIBLE);
                gridSecondFloor.setVisibility(INVISIBLE);
                gridThirdFloor.setVisibility(INVISIBLE);
                gridFourthFloor.setVisibility(INVISIBLE);
                gridFifthFloor.setVisibility(VISIBLE);
                gridB1Floor.setVisibility(INVISIBLE);
                btnPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toFloorPlanIntent.putExtra("floor_number",5);
                        toFloorPlanIntent.putExtra("floor_plan",1);
                        startActivity(toFloorPlanIntent);
                    }
                });
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getApplicationContext(),"층을 선택해 주세요",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }
}

