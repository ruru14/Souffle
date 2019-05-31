package com.seoultech.lesson.souffle;

import com.seoultech.lesson.souffle.data.LoginManager;
import com.seoultech.lesson.souffle.data.model.User;

import org.junit.Test;

public class LoginTest {

    @Test
    public void loginTest(){
        LoginManager loginManager = new LoginManager();
        User user = loginManager.login(15109355,"dnjs0402@889");
        System.out.println(user.getName());
        System.out.println(user.isAuthorized());
        System.out.println(user.getMajor());
    }
}
