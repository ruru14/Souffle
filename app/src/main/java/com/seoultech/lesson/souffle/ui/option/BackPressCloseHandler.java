package com.seoultech.lesson.souffle.ui.option;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BackPressCloseHandler extends AppCompatActivity {
    private long backKeyPressedTime = 0;
    private Toast toast;
    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            //int pid = android.os.Process.myPid();
            //android.os.Process.killProcess(pid);
            //finishAffinity();
            activity.finish();
            toast.cancel();
        }
    }

    public void showGuide() {
        toast = Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}

