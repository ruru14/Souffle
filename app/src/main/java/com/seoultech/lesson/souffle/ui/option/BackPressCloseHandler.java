package com.seoultech.lesson.souffle.ui.option;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.login.LoginActivity;
import com.seoultech.lesson.souffle.ui.login.SelectMenuActivity;

import java.util.List;

public class BackPressCloseHandler extends AppCompatActivity{
    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;
    private Context contexts;


    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        activity.finish();
    }

    public void onBackPressed(int i){

    }

    public void showGuide() {
        toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}

