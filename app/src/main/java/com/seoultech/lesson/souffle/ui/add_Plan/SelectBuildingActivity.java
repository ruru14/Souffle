package com.seoultech.lesson.souffle.ui.add_Plan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.seoultech.lesson.souffle.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectBuildingActivity extends AppCompatActivity {

    private Button btnMirae;
    private Button btnBuilding1;
    private Button btnBuilding2;
    private Button btnBuilding3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_building);

        Intent toGetIntent = new Intent(this.getIntent());

        ButterKnife.bind(this);

        btnMirae = (Button)findViewById(R.id.btn_mirae);

        btnMirae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRoomSelectIntent = new Intent(getApplicationContext(),SelectRoomActivity.class);
                toRoomSelectIntent.putExtra("building_name","미래관");
                startActivity(toRoomSelectIntent);
            }
        });
    }

}
