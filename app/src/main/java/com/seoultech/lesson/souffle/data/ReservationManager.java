package com.seoultech.lesson.souffle.data;

import com.google.gson.Gson;
import com.seoultech.lesson.souffle.data.model.Option;
import com.seoultech.lesson.souffle.data.model.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationManager {

    private Reservation reservation;
    private Option option;

    private Retrofit retrofit;
    private ReservationAPI reservationAPI;
    private Call<List<Reservation>> getList;
    private Call<Reservation> post;
    private Gson gson;

    public ReservationManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://35.194.37.76:1234/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        reservationAPI = retrofit.create(ReservationAPI.class);
    }

    public void createReservation(Reservation reservation) {
        post = reservationAPI.createReservation(reservation);
        post.enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                System.out.println("CCCCCCCCCC");
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void readReservation() {
        getList = reservationAPI.readReservation();
        getList.enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                List<Reservation> list = response.body();
                for(Reservation reservation : list){
                    System.out.println(reservation.getName());
                }
            }

            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void readReservationByStudentNumber() {

    }

    public void readReservationByState() {

    }

    public void updateReservation(Reservation reservation) {

    }

    public void deleteReservation(Reservation reservation) {

    }
}
