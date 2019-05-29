package com.seoultech.lesson.souffle.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Option {

    private boolean autoLogin = false;

    private String language;

}
