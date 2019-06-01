package com.seoultech.lesson.souffle.data;

import android.app.Activity;
import android.content.SharedPreferences;

import com.seoultech.lesson.souffle.data.model.Option;
import com.seoultech.lesson.souffle.data.model.User;

public class OptionManager {

    private Option option;
    private SharedPreferences preferences;
    private SharedPreferences.Editor preferencesEditor;

    public OptionManager(Activity activity) {
        preferences = activity.getSharedPreferences("setting", 0);
        option = new Option(preferences.getBoolean("AutoLogin", false), preferences.getString("Language", "한국어"));
        preferencesEditor = preferences.edit();
    }

    public boolean isAutoLogin() {
        return option.isAutoLogin();
    }

    public void setAutoLogin(boolean isTrue, User user) {
        preferencesEditor.putBoolean("AutoLogin", isTrue);
        preferencesEditor.putInt("AutoLoginStudentNumber", user.getStudentNumber());
        preferencesEditor.putString("AutoLoginPassword", user.getPassword());
        preferencesEditor.commit();
    }

    public String getLanguage() {
        return option.getLanguage();
    }

    public void setLanguage(String language) {
        preferencesEditor.putString("Language", language);
        preferencesEditor.commit();
    }

    public Option getOption() {
        return this.option;
    }

}
