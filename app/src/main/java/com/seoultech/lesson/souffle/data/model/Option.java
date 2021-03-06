package com.seoultech.lesson.souffle.data.model;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Option implements Serializable {

    private boolean autoLogin = false;

    private String language;

}
