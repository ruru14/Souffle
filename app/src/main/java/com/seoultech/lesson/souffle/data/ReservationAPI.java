package com.seoultech.lesson.souffle.data;

import com.seoultech.lesson.souffle.data.model.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ReservationAPI {

    @POST("reserve")
    Call<Reservation> createReservation(@Body Reservation reservation);

    @GET("reserve")
    Call<List<Reservation>> readReservation();

    @GET("reserve/state")
    Call<List<Reservation>> readReservationByState(@Query("state") String state);

    @GET("reserve/stnumber")
    Call<List<Reservation>> readReservationByStudentNumber(@Query("studentNumber") int studentNumber);

    @PUT("reserve")
    Call<Reservation> updateReservation(Reservation reservation);

    @DELETE("reserve")
    Call<Reservation> deleteReservation(Reservation reservation);
}
