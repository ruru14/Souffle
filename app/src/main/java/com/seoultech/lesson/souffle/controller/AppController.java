package com.seoultech.lesson.souffle.controller;

import android.app.Activity;
import android.content.res.Resources;

import com.seoultech.lesson.souffle.data.LoginManager;
import com.seoultech.lesson.souffle.data.OptionManager;
import com.seoultech.lesson.souffle.data.ReservationManager;
import com.seoultech.lesson.souffle.data.model.Option;
import com.seoultech.lesson.souffle.data.model.User;

public class AppController {

    private LoginManager loginManager;
    private OptionManager optionManager;
    private ReservationManager reservationManager;

    private AppController(){
    }

    public void init(Activity activity){
        loginManager = new LoginManager();
        optionManager = new OptionManager(activity);
        reservationManager = new ReservationManager();
    }

    public boolean isAutoLogin(){
        return optionManager.isAutoLogin();
    }

    public User autoLogin(){
        return null;
    }

    public User login(int studentNumber, String password){
        return loginManager.login(studentNumber, password);
    }

    public void setAutoLogin(boolean attr, User user){
        optionManager.setAutoLogin(attr, user);
    }

    public String getLanguage(){
        return optionManager.getLanguage();
    }

    public void setLanguage(String attr){
        optionManager.setLanguage(attr);
    }

    private static class LazyHolder{
        public static final AppController instance = new AppController();
    }

    public static AppController getInstance(){
        return LazyHolder.instance;
    }
}
