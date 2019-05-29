package com.seoultech.lesson.souffle;

import com.seoultech.lesson.souffle.data.LoginManager;

import org.junit.Test;

public class LoginTest {

    @Test
    public void loginTest(){
        LoginManager loginManager = new LoginManager();
        loginManager.login(11111111,"asdf");
    }
}
