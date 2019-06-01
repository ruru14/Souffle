package com.seoultech.lesson.souffle.data.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reservation {

    @SerializedName("id")
    private Long id;

    @SerializedName("roomNumber")
    private int roomNumber;

    @SerializedName("date")
    private String date;

    @SerializedName("studentNumber")
    private int studentNumber;

    @SerializedName("name")
    private String name;

    @SerializedName("purpose")
    private String purpose;

    @SerializedName("totalMember")
    private int totalMember;

    @SerializedName("timeStart")
    private String timeStart;

    @SerializedName("timeEnd")
    private String timeEnd;

    @SerializedName("state")
    private String state;

    @Builder
    public Reservation(int roomNumber, String date, int studentNumber, String name, String purpose, int totalMember, String timeStart, String timeEnd, String state){
        this.roomNumber = roomNumber;
        this.date = date;
        this.studentNumber = studentNumber;
        this.name = name;
        this.purpose = purpose;
        this.totalMember = totalMember;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.state = state;
    }

}
