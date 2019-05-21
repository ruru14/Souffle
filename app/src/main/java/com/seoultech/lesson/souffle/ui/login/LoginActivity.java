package com.seoultech.lesson.souffle.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

public class LoginActivity extends AppCompatActivity {

    Button btn_login, btn_exit, btn_test;
    TextView text_id, text_pw, textView, test_id, test_pw;
    EditText edit_id, edit_pw;
    Intent to_floor_select;

    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backPressCloseHandler = new BackPressCloseHandler(this);

        to_floor_select = new Intent(this.getIntent());
        test_id = (TextView) findViewById(R.id.test_ID);
        test_pw = (TextView) findViewById(R.id.test_PW);
        text_id = (TextView) findViewById(R.id.text_id);
        text_pw = (TextView) findViewById(R.id.text_pw);
        textView = (TextView) findViewById(R.id.textView);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_exit = (Button) findViewById(R.id.btn_exit);
        btn_test = (Button) findViewById(R.id.btn_test);

        edit_id = (EditText) findViewById(R.id.edit_login_ID);
        edit_pw = (EditText) findViewById(R.id.edit_login_PW);

        //textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);   -> 글자에 선긋기

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test_id.setText(edit_id.getText());
                test_pw.setText(edit_pw.getText());
            }
        });



        btn_exit.setOnClickListener(new View.OnClickListener() {
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

