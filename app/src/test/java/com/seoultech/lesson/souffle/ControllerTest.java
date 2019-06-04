package com.seoultech.lesson.souffle;

import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.ReservationManager;
import com.seoultech.lesson.souffle.data.model.Option;
import com.seoultech.lesson.souffle.data.model.User;

import org.junit.Test;

public class ControllerTest {



    @Test
    public void controllerTest(){
        AppController controller = AppController.getInstance();
        ReservationManager m = new ReservationManager();
        System.out.println(m.readReservationByStudentNumber(15109357).get(0).toString());
    }
}
