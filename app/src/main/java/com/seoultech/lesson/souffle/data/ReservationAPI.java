package com.seoultech.lesson.souffle.data;

import com.seoultech.lesson.souffle.data.model.Reservation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ReservationAPI {

    @POST("reserve")
    Call<Reservation> createReservation(@Body Reservation reservation);

    @GET("reserve")
    Call<List<Reservation>> readReservation();

    @GET("reserve/building")
    Call<List<Reservation>> readReservationByBuilding(@Query("building") String building);

    @GET("reserve/building_roomnum_date")
    Call<List<Reservation>> readReservationByBuildingAndRoomNumberAndDate(@Query("building") String building, @Query("roomNumber") int roomNumber, @Query("date") String date);

    @GET("reserve/stnumber")
    Call<List<Reservation>> readReservationByStudentNumber(@Query("student_number") int studentNumber);

    @GET("reserve/stnumber_date")
    Call<List<Reservation>> readReservationByStudentNumberAndDate(@Query("studentNumber") int studentNumber, @Query("date") String date);

    @PUT("reserve")
    Call<Reservation> updateReservation(@Body Reservation reservation);

    @HTTP(method = "DELETE", path = "reserve", hasBody = true)
    Call<Reservation> deleteReservation(@Body Long id);
}
