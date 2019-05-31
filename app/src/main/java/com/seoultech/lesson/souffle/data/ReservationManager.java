package com.seoultech.lesson.souffle.data;

import com.seoultech.lesson.souffle.data.model.Option;
import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.data.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationManager {

    Reservation reservation;
    Option option;

    private Retrofit retrofit;
    private ReservationAPI reservationAPI;
    private Call<Reservation> reservationList;

    public ReservationManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("asdf")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        reservationAPI = retrofit.create(ReservationAPI.class);
    }

    public void createReservation(Reservation reservation) {

    }

    public void readReservation() {
        reservationList = reservationAPI.readReservation();
        reservationList.enqueue(reservationCallback);
    }

    private Callback<Reservation> reservationCallback = new Callback<Reservation>() {
        @Override
        public void onResponse(Call<Reservation> call, Response<Reservation> response) {
            Reservation result = response.body();
        }

        @Override
        public void onFailure(Call<Reservation> call, Throwable t) {
            t.printStackTrace();
        }
    };

    public void readReservationByStudentNumber() {

    }

    public void readReservationByState() {

    }

    public void updateReservation(Reservation reservation) {

    }

    public void deleteReservation(Reservation reservation) {

    }
}
