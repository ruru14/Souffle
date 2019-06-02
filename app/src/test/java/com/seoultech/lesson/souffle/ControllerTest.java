package com.seoultech.lesson.souffle;

import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Option;
import com.seoultech.lesson.souffle.data.model.User;

import org.junit.Test;

public class ControllerTest {



    @Test
    public void controllerTest(){
        AppController controller = AppController.getInstance();
        User user = new User("1234", 11111111, "1111", "1234", true);
        controller.setAutoLogin(false, user);
        System.out.println(controller.isAutoLogin());
    }
}
