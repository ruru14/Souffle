package com.seoultech.lesson.souffle.ui.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
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

import java.util.Locale;

import butterknife.BindView;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin, btnExit, btnTest;
    private TextView textId, textPw, textView, testId, testPW;
    private EditText editId, editPW;
    private Intent toFloorSelectIntent;
    private long timeChecker;
    private ContactsContract.Contacts.Data userData;

    @BindView(R.id.chkAutoLogin)
    private CheckBox chkAutoLogin;
    private AppController appController;
    private User user;
    private String setLanguage = "ko";

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

        appController = AppController.getInstance();
        appController.init(this);

        if(appController.getLanguage().equals("ko")){
            Locale locale = new Locale("ko");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }
        else{
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        }


        setContentView(R.layout.activity_login);

//        //만약 자동로그인 상태면 바로 다음화면으로 넘기게 할것 - if(isAutoLogin) => 다음액티비티
//        System.out.println(appController.isAutoLogin());
//        if(appController.isAutoLogin()){
//            Intent ToSelectMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
//            ToSelectMenuIntent.putExtra("user",user);
//            startActivity(ToSelectMenuIntent);
//        }
        backPressCloseHandler = new BackPressCloseHandler(this);


        chkAutoLogin = (CheckBox)findViewById(R.id.chkAutoLogin);
        toFloorSelectIntent = new Intent(this.getIntent());
        textId = (TextView) findViewById(R.id.text_id);
        textPw = (TextView) findViewById(R.id.text_pw);
        textView = (TextView) findViewById(R.id.textView);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnExit = (Button) findViewById(R.id.btn_exit);

        editId = (EditText) findViewById(R.id.edit_login_ID);
        editPW = (EditText) findViewById(R.id.edit_login_PW);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                ToSelectMenuIntent.putExtra("user",user);
                startActivity(ToSelectMenuIntent);
            }else{ // 로그인 실패
                //메세지박스 다이얼로그 띄우삼
                        AlertDialog.Builder failLoginDlg = new AlertDialog.Builder(LoginActivity.this);
                        failLoginDlg.setMessage("로그인에 실패하였습니다.\n다시 로그인해주세요");
                        failLoginDlg.setPositiveButton("알겠습니다",null);
                        failLoginDlg.show();

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
            if(!(editId.getText() == null || editPW.getText() == null))
            user = appController.login(Integer.parseInt(String.valueOf(editId.getText())), String.valueOf(editPW.getText()));
            appController.setAutoLogin(false,user);
            return null;
        }

        // 끝나고 실행
        // 로그인 성공 (체크)
        // 로그인 성공 (ㄴ 체크)
        // 로그인 실패

        @Override
        protected void onPostExecute(Object o) {
            if(user.isAuthorized()) {
                // 자동로그인 체크여부 설정 -> 체크박스 만들기
//                appController.setAutoLogin(false,user);

                if (chkAutoLogin.isChecked()) { //자동로그인 체크
                    appController.setAutoLogin(chkAutoLogin.isChecked(), user);
//                    appController.setAutoLogin(false, user);
                    Intent toSelectMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                    toSelectMenuIntent.putExtra("user", user);
                    progressDialog.dismiss();
                    startActivity(toSelectMenuIntent);
                }else{
                    Intent toSelectMenuIntent = new Intent(getApplicationContext(), SelectMenuActivity.class);
                    toSelectMenuIntent.putExtra("user", user);
                    progressDialog.dismiss();
                    startActivity(toSelectMenuIntent);
                }
            }else{ // 로그인 실패
                AlertDialog.Builder logInFailDlg= new AlertDialog.Builder(LoginActivity.this);
                logInFailDlg.setMessage("로그인에 실패하였습니다");
                logInFailDlg.setTitle("로그인 실패");
                logInFailDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        progressDialog.dismiss();
                    }
                });
                logInFailDlg.show();
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

