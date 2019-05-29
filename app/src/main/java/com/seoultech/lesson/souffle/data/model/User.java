package com.seoultech.lesson.souffle.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private String name;

    private int studentNumber;

    private String major;

    private boolean isAuthorized;

}
