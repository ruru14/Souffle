package com.seoultech.lesson.souffle;

import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Option;

import org.junit.Test;

public class ControllerTest {



    @Test
    public void controllerTest(){
        AppController controller = AppController.getInstance();
//        controller.setAutoLogin(false);
        System.out.println(controller.isAutoLogin());
    }
}
