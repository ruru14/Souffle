package com.seoultech.lesson.souffle.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.data.model.Reservation;

import java.util.List;

public class ReservationListAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    private List<Reservation> reservationList;
    private int listSize;

    public ReservationListAdapter(List<Reservation> list) {
        this.reservationList = list;
        listSize = reservationList.size();
    }

    @Override
    public int getCount() {
        return listSize;
    }

    @Override
    public Object getItem(int position) {
        return reservationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.list_reservation, parent, false);
        }

        TextView date = convertView.findViewById(R.id.txt_date_reservation);
        TextView buildingName = convertView.findViewById(R.id.txt_buildingName_reservation);
        TextView studentNumber = convertView.findViewById(R.id.txt_stNumber_reservation);
        Button btnReservation = convertView.findViewById(R.id.btn_reservation_list);

        date.setText(reservationList.get(position).getDate());
        buildingName.setText(reservationList.get(position).getBuilding());
        studentNumber.setText(Integer.toString(reservationList.get(position).getStudentNumber()));

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder infoDlg = new AlertDialog.Builder(v.getContext());
                infoDlg.setTitle("상세 정보");
                infoDlg.setMessage("학번 : " + reservationList.get(position).getStudentNumber()+
                                    "\n이름 : " + reservationList.get(position).getName() +
                                    "\n예약 시간 : " + reservationList.get(position).getDate() + "\t\n " +
                                                reservationList.get(position).getTimeStart() + "~" +
                                                reservationList.get(position).getTimeEnd() +
                                    "\n건물명 : " + reservationList.get(position).getBuilding() +
                                    "\n사용 인원 : " + reservationList.get(position).getTotalMember() +
                                    "\n강의실 : " + reservationList.get(position).getRoomNumber() +
                                    "\n목적 : " + reservationList.get(position).getPurpose());
                infoDlg.setPositiveButton("확인",null);
                infoDlg.show();
            }
        });

        return convertView;
    }
}
