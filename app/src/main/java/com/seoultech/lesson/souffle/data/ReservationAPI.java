package com.seoultech.lesson.souffle.data;

import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.data.model.User;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ReservationAPI {

    @POST("/reserve")
    void createReservation(Reservation reservation);

    @GET("/reserve")
    Call<Reservation> readReservation();

    @GET("/reserve/state")
    Call<Reservation> readReservationByState();

    @GET("/reserve/stnumber")
    Call<Reservation> readReservationByStudentNumber();

    @PUT("/reserve")
    void updateReservation(Reservation reservation);

    @DELETE("/reserve")
    void deleteReservation(Reservation reservation);
}
