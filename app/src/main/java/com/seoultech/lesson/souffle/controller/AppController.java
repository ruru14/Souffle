package com.seoultech.lesson.souffle.controller;

import android.app.Activity;

import com.seoultech.lesson.souffle.data.LoginManager;
import com.seoultech.lesson.souffle.data.OptionManager;
import com.seoultech.lesson.souffle.data.ReservationManager;
import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.data.model.User;

import java.util.List;

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

    // LoginManager
    public boolean isAutoLogin(){
        return optionManager.isAutoLogin();
    }

    public User autoLogin(){
        return null;
    }

    public User login(int studentNumber, String password){
        return loginManager.login(studentNumber, password);
    }


    //자동로그인 설정
    public void setAutoLogin(boolean attr, User user){
        optionManager.setAutoLogin(attr, user);
    }

    // OptionManager
    public String getLanguage(){
        return optionManager.getLanguage();
    }

    public void setLanguage(String attr){
        optionManager.setLanguage(attr);
    }

    // ReservationManager
    public boolean createReservation(Reservation reservation){
        return reservationManager.createReservation(reservation);
    }

    public List<Reservation> readReservation(){
        return reservationManager.readReservation();
    }

    public List<Reservation> readReservationByStudentNumber(int studentNumber){
        return reservationManager.readReservationByStudentNumber(studentNumber);
    }

    public List<Reservation> readReservationByStudentNumberAndDate(int studentNumber, String date){
        return reservationManager.readReservationByStudentNumberAndDate(studentNumber, date);
    }

    public List<Reservation> readReservationByBuilding(String building){
        return reservationManager.readReservationByBuilding(building);
    }


    //해당 건물의 해당 시간, 해당 날짜의 예약된 시간 받아오기 요거쓰면됨
    public List<Reservation> readReservationByBuildingAndRoomNumberAndDate(String building, int roomNumber, String date){
        return reservationManager.readReservationByBuildingAndRoomNumberAndDate(building, roomNumber, date);
    }

    public boolean updateReservation(Reservation reservation){
        return reservationManager.updateReservation(reservation);
    }

    public boolean deleteReservation(Reservation reservation){
        return reservationManager.deleteReservation(reservation);
    }

    // Singleton
    private static class LazyHolder{
        public static final AppController instance = new AppController();
    }

    public static AppController getInstance(){
        return LazyHolder.instance;
    }
}
