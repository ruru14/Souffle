package com.seoultech.lesson.souffle.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.User;
import com.seoultech.lesson.souffle.ui.option.BackPressCloseHandler;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnExit, btnTest;
    private TextView textId, textPw, textView, testId, testPW;
    private EditText editId, editPW;
    private Intent toFloorSelectIntent;
    private long timeChecker;

    @BindView(R.id.chkAutoLogin)
    private CheckBox chkAutoLogin;
    private AppController appController;
    private User user;

    private Toast toast;
    private long backKeyPressedTime = 0;
    private Activity activity;
    private Context context;

    private BackPressCloseHandler backPressCloseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_login);

        appController = AppController.getInstance();
        appController.init(this);

        //만약 자동로그인 상태면 바로 다음화면으로 넘기게 할것 - if(isAutoLogin) => 다음액티비티
        System.out.println(appController.isAutoLogin());
        if(appController.isAutoLogin()){
            Intent ToSelectMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
            startActivity(ToSelectMenuIntent);
        }
        backPressCloseHandler = new BackPressCloseHandler(this);


        chkAutoLogin = (CheckBox)findViewById(R.id.chkAutoLogin);
        toFloorSelectIntent = new Intent(this.getIntent());
        testId = (TextView) findViewById(R.id.test_ID);
        testPW = (TextView) findViewById(R.id.test_PW);
        textId = (TextView) findViewById(R.id.text_id);
        textPw = (TextView) findViewById(R.id.text_pw);
        textView = (TextView) findViewById(R.id.textView);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnExit = (Button) findViewById(R.id.btn_exit);
        btnTest = (Button) findViewById(R.id.btn_test);

        editId = (EditText) findViewById(R.id.edit_login_ID);
        editPW = (EditText) findViewById(R.id.edit_login_PW);

        //textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);   -> 글자에 선긋기

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testId.setText(editId.getText());
                testPW.setText(editPW.getText());
                LoginTask task = new LoginTask();
                task.execute();
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

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     Intent To_select_intent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                     startActivity(To_select_intent);
            }
        });
    }

    private class AutoLoginTask extends AsyncTask {


        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);

        // 실행중 (Progress Bar)
        @Override
        protected void onPreExecute() {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Logining");

            progressDialog.show();
            super.onPreExecute();
        }

        // 백그라운드 실행
        @Override
        protected Object doInBackground(Object[] objects) {
            user = appController.autoLogin();
            return null;
        }

        // 끝나고 실행
        @Override
        protected void onPostExecute(Object o) {
            if(user.isAuthorized()){
                Intent ToSelectMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                startActivity(ToSelectMenuIntent);
            }else{ // 로그인 실패
                //메세지박스 다이얼로그 띄우삼
//                while(true){
//                    if(System.currentTimeMillis() >  timeChecker + 5000) {
//                        AlertDialog.Builder failLoginDlg = new AlertDialog.Builder(LoginActivity.this);
//                        failLoginDlg.setMessage("로그인에 실패하였습니다.\n다시 로그인해주세요");
//                        failLoginDlg.setPositiveButton("알겠습니다",null);
//                        failLoginDlg.show();
//                        break;
//                    }
//                }
            }
        }
    }

    private class LoginTask extends AsyncTask {

        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);

        // 실행중 (Progress Bar)
        @Override
        protected void onPreExecute() {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Logining");
            progressDialog.show();
            super.onPreExecute();
        }

        // 백그라운드 실행
        @SuppressLint("WrongThread")
        @Override
        protected Object doInBackground(Object[] objects) {
            user = appController.login(Integer.parseInt(String.valueOf(editId.getText())), String.valueOf(editPW.getText()));
            return null;
        }

        // 끝나고 실행
        @Override
        protected void onPostExecute(Object o) {
            if(user.isAuthorized()){
                // 자동로그인 체크여부 설정 -> 체크박스 만들기
                if(chkAutoLogin.isChecked()){
                    appController.setAutoLogin(true, user);
                }
                appController.setAutoLogin(false, user);
                Intent To_select_intent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                startActivity(To_select_intent);
            }else{ // 로그인 실패

            }
        }
    }


    @Override
    public void onBackPressed() {
        this.context = activity;
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finishAffinity();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(getApplicationContext(), "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}

