package com.seoultech.lesson.souffle.controller;

public class AppController {

    private AppController(){ }

    private static class LazyHolder{
        public static final AppController instance = new AppController();
    }

    public static AppController getInstance(){
        return LazyHolder.instance;
    }
}
