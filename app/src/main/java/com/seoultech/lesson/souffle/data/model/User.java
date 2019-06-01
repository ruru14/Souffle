package com.seoultech.lesson.souffle.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User {

    private String name;

    private int studentNumber;

    private String password;

    private String major;

    private boolean isAuthorized;

}
