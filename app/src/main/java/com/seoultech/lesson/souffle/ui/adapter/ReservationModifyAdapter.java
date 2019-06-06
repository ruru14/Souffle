package com.seoultech.lesson.souffle.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Reservation;
import com.seoultech.lesson.souffle.util.Changer;

import java.util.List;

public class ReservationModifyAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    private List<Reservation> reservationModifyList;
    private AppController appController;
    private ModifyListener modifyListener;

    public ReservationModifyAdapter(List<Reservation> list, ModifyListener listener){
        this.reservationModifyList = list;
        this.modifyListener = listener;
        appController = AppController.getInstance();
    }

    @Override
    public int getCount() {
        return reservationModifyList.size();
    }

    @Override
    public Object getItem(int position) {
        return reservationModifyList.get(position);
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
            convertView = inflater.inflate(R.layout.list_modify_reservation, parent, false);
        }


        TextView date = convertView.findViewById(R.id.txt_date_modify);
        TextView buildingName = convertView.findViewById(R.id.txt_buildingName_modify);
        TextView roomNumber = convertView.findViewById(R.id.txt_roomNumber_modify);
        TextView time = convertView.findViewById(R.id.txt_time_modify);
        Button btnReservation = convertView.findViewById(R.id.btn_modify_list);


        date.setText(reservationModifyList.get(position).getDate());
        buildingName.setText(Changer.buildingChange(reservationModifyList.get(position).getBuilding()));
        roomNumber.setText(Integer.toString(reservationModifyList.get(position).getRoomNumber()));
        time.setText(reservationModifyList.get(position).getTimeStart() + "~" + reservationModifyList.get(position).getTimeEnd());
        Intent intent = new Intent(convertView.getContext().getApplicationContext(), ModifyInsertActivity.class);

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder infoDlg = new AlertDialog.Builder(v.getContext());
                infoDlg.setTitle("상세 정보");
                infoDlg.setMessage("학번 : " + reservationModifyList.get(position).getStudentNumber()+
                        "\n이름 : " + reservationModifyList.get(position).getName() +
                        "\n예약 날짜 : " + reservationModifyList.get(position).getDate() + "\n " +
                        "\n예약 시간 : " + reservationModifyList.get(position).getTimeStart() + "~" +
                                reservationModifyList.get(position).getTimeEnd() +
                        "\n건물명 : " + reservationModifyList.get(position).getBuilding() +
                        "\n사용 인원 : " + reservationModifyList.get(position).getTotalMember() +
                        "\n강의실 : " + reservationModifyList.get(position).getRoomNumber() +
                        "\n목적 : " + reservationModifyList.get(position).getPurpose() +
                        "\n정말로 수정하시겠습니까?");

                infoDlg.setNegativeButton("취소",null);
                infoDlg.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { ;
                        modifyListener.onModify(reservationModifyList.get(position));
                    }
                });
                infoDlg.show();
            }
        });

        return convertView;
    }
}
