package com.seoultech.lesson.souffle.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnExit, btn_test;
    TextView text_id, text_pw, textView, testId, testPW;
    EditText editId, editPW;
    Intent to_floor_select;
    private AppController appController;
    private User user;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //만약 자동로그인 상태면 바로 다음화면으로 넘기게 할것 - if(isAutoLogin) => 다음액티비티
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_login);

        appController = AppController.getInstance();
        appController.init(this);
        appController.readReservation();

        backPressCloseHandler = new BackPressCloseHandler(this);

        to_floor_select = new Intent(this.getIntent());
        testId = (TextView) findViewById(R.id.test_ID);
        testPW = (TextView) findViewById(R.id.test_PW);
        text_id = (TextView) findViewById(R.id.text_id);
        text_pw = (TextView) findViewById(R.id.text_pw);
        textView = (TextView) findViewById(R.id.textView);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnExit = (Button) findViewById(R.id.btn_exit);
        btn_test = (Button) findViewById(R.id.btn_test);

        editId = (EditText) findViewById(R.id.edit_login_ID);
        editPW = (EditText) findViewById(R.id.edit_login_PW);

        //textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);   -> 글자에 선긋기

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testId.setText(editId.getText());
                testPW.setText(editPW.getText());
                user = appController.login(Integer.parseInt(String.valueOf(editId.getText())), String.valueOf(editPW.getText()));
                if(!user.isAuthorized()){
                    Intent To_select_intent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                    startActivity(To_select_intent);
                }

            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.runFinalization();
                System.exit(0);

            }
        });

        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     Intent To_select_intent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                     startActivity(To_select_intent);
                //  Uri uri = Uri.parse("http://for-a.seoultech.ac.kr/generate/STECH/index.html");
                //  Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                //  startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }
}

