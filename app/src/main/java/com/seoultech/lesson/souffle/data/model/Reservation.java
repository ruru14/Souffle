package com.seoultech.lesson.souffle.data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Reservation {

    private Long id;

    private int roomNumber;

    private String date;

    private int studentNumber;

    private String name;

    private String purpose;

    private int totalMember;

    private String timeStart;

    private String timeEnd;

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
