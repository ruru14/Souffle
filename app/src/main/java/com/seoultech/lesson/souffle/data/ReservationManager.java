package com.seoultech.lesson.souffle.data;

import com.seoultech.lesson.souffle.data.model.Reservation;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReservationManager {

    private Retrofit retrofit;
    private ReservationAPI reservationAPI;

    public ReservationManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://35.194.37.76:1234/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        reservationAPI = retrofit.create(ReservationAPI.class);
    }

    public boolean createReservation(Reservation reservation) {
        Call<Reservation> create = reservationAPI.createReservation(reservation);
        boolean isSuccess = false;
        try{
            isSuccess = create.execute().isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public List<Reservation> readReservation() {
        Call<List<Reservation>> read = reservationAPI.readReservation();
        List<Reservation> response = null;
        try{
            response = read.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public List<Reservation> readReservationByStudentNumber(int studentNumber) {
        Call<List<Reservation>> read = reservationAPI.readReservationByStudentNumber(studentNumber);
        List<Reservation> response = null;
        try{
            response = read.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public List<Reservation> readReservationByStudentNumberAndDate(int studentNumber, String date) {
        Call<List<Reservation>> read = reservationAPI.readReservationByStudentNumberAndDate(studentNumber, date);
        List<Reservation> response = null;
        try{
            response = read.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public List<Reservation> readReservationByBuilding(String building) {
        Call<List<Reservation>> read = reservationAPI.readReservationByBuilding(building);
        List<Reservation> response = null;
        try{
            response = read.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public List<Reservation> readReservationByBuildingAndRoomNumberAndDate(String building, int roomNumber, String date) {
        Call<List<Reservation>> read = reservationAPI.readReservationByBuildingAndRoomNumberAndDate(building, roomNumber, date);
        List<Reservation> response = null;
        try{
            response = read.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public boolean updateReservation(Reservation reservation) {
        Call<Reservation> update = reservationAPI.updateReservation(reservation);
        boolean isSuccess = false;
        try{
            isSuccess = update.execute().isSuccessful();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean deleteReservation(Reservation reservation) {
        Call<Reservation> delete = reservationAPI.deleteReservation(reservation.getId());
        boolean isSuccess = false;
        try{
            isSuccess = delete.execute().isSuccessful();
            System.out.println(isSuccess);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
