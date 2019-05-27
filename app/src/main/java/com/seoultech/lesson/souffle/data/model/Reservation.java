package com.seoultech.lesson.souffle.data.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {

    private Long id;

    private int room_number;

    private String data;

    private int student_number;

    private String name;

    private String purpose;

    private int total_member;

    private String time_start;

    private String time_end;

    private String state;

}
