package com.seoultech.lesson.souffle.util;

import com.seoultech.lesson.souffle.R;

public class Changer {
    public static String buildingChange(String building){
        if(building.equals("Building_Mirae")) return "미래관";
        if(building.equals("Building_A")) return "Building_A";
        if(building.equals("Building_B")) return "Building_B";
        if(building.equals("Building_C")) return "Building_C";

        return null;
    }
}
