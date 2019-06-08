package com.seoultech.lesson.souffle.ui.option;

import android.view.View;

import com.seoultech.lesson.souffle.ui.add_plan.TimeReserveActivity;

import java.util.ArrayList;

public class CheckAdapter extends TimeReserveActivity {

    ArrayList<CheckData> chkArrList;
    View.OnClickListener onItemClick;

    public void setOnItemClickListener(View.OnClickListener v){
        onItemClick = v;
    }

    public CheckAdapter(ArrayList<CheckData> chk_arr_list){ this.chkArrList = chk_arr_list; }



}
