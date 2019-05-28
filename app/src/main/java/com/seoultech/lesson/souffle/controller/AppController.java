package com.seoultech.lesson.souffle.controller;

import com.seoultech.lesson.souffle.data.LoginManager;
import com.seoultech.lesson.souffle.data.OptionManager;
import com.seoultech.lesson.souffle.data.ReservationManager;

public class AppController {

    private LoginManager loginManager;
    private OptionManager optionManager;
    private ReservationManager reservationManager;

    private AppController(){
        // TODO : Manager Initialize
    }

    private static class LazyHolder{
        public static final AppController instance = new AppController();
    }

    public static AppController getInstance(){
        return LazyHolder.instance;
    }
}
