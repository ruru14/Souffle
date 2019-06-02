package com.seoultech.lesson.souffle.data;

import com.google.gson.Gson;
import com.seoultech.lesson.souffle.data.model.Option;
import com.seoultech.lesson.souffle.data.model.Reservation;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationManager {

    private Retrofit retrofit;
    private ReservationAPI reservationAPI;
    private Call<List<Reservation>> getList;
    private Call<Reservation> post;

    public ReservationManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://35.194.37.76:1234/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        reservationAPI = retrofit.create(ReservationAPI.class);
    }

    public boolean createReservation(Reservation reservation) {
        post = reservationAPI.createReservation(reservation);
        boolean isSuccess = false;
        try{
            isSuccess = post.execute().isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public List<Reservation> readReservation() {
        getList = reservationAPI.readReservation();
        List<Reservation> response = null;
        try{
            response = getList.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public List<Reservation> readReservationByStudentNumber(int studentNumber) {
        getList = reservationAPI.readReservationByStudentNumber(studentNumber);
        List<Reservation> response = null;
        try{
            response = getList.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public List<Reservation> readReservationByState(String state) {
        getList = reservationAPI.readReservationByState(state);
        List<Reservation> response = null;
        try{
            response = getList.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public boolean updateReservation(Reservation reservation) {
        post = reservationAPI.updateReservation(reservation);
        boolean isSuccess = false;
        try{
            isSuccess = post.execute().isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean deleteReservation(Reservation reservation) {
        Call<Reservation> deleteCall = reservationAPI.deleteReservation(reservation.getId());
        boolean isSuccess = false;
        try{
            isSuccess = deleteCall.execute().isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
