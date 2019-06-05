package com.seoultech.lesson.souffle.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.controller.AppController;
import com.seoultech.lesson.souffle.data.model.Reservation;

import java.util.List;

public class ReservationDeleteAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    private List<Reservation> reservationDeleteList;
    private int listSize;
    private AppController appController;

    public ReservationDeleteAdapter(List<Reservation> list) {
        this.reservationDeleteList = list;
        listSize = reservationDeleteList.size();
    }

    @Override
    public int getCount() {
        return listSize;
    }

    @Override
    public Object getItem(int position) {
        return reservationDeleteList.get(position);
    }

    public Object getItemList() { return reservationDeleteList; }

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
            convertView = inflater.inflate(R.layout.list_delete_reservation, parent, false);
        }

        TextView date = convertView.findViewById(R.id.txt_date_delete);
        TextView buildingName = convertView.findViewById(R.id.txt_buildingName_delete);
        TextView studentNumber = convertView.findViewById(R.id.txt_stNumber_delete);
        Button btnDeleteReservation = convertView.findViewById(R.id.btn_delete_list);

        date.setText(reservationDeleteList.get(position).getDate());
        buildingName.setText(reservationDeleteList.get(position).getBuilding());
        studentNumber.setText(Integer.toString(reservationDeleteList.get(position).getStudentNumber()));

        btnDeleteReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder infoDlg = new AlertDialog.Builder(v.getContext());
                infoDlg.setTitle("상세 정보");
                infoDlg.setMessage("학번 : " + reservationDeleteList.get(position).getStudentNumber()+
                        "\n이름 : " + reservationDeleteList.get(position).getName() +
                        "\n예약 날짜 : " + reservationDeleteList.get(position).getDate() +
                        "\n예약 시간 : " + reservationDeleteList.get(position).getTimeStart() + "~" +
                        reservationDeleteList.get(position).getTimeEnd() +
                        "\n건물명 : " + reservationDeleteList.get(position).getBuilding() +
                        "\n사용 인원 : " + reservationDeleteList.get(position).getTotalMember() +
                        "\n강의실 : " + reservationDeleteList.get(position).getRoomNumber() +
                        "\n목적 : " + reservationDeleteList.get(position).getPurpose() +
                        "\n\n 정말 해당 예약을 지우시겠습니까?");
                infoDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AsyncTask<Reservation, Integer, Boolean>() {
                            @Override
                            protected void onPreExecute() {
                                // 로딩
                                super.onPreExecute();
                            }

                            @Override
                            protected Boolean doInBackground(Reservation... reservations) {
                                return appController.deleteReservation(reservations[0]);
                            }

                            @Override
                            protected void onPostExecute(Boolean aBoolean) {
                                if(aBoolean){ // 삭제 성공시 (로딩 없애기도 해줘야함) + 새로고침
                                    reservationDeleteList.remove(position);
                                }else{ // 삭제 실패시 (로딩 ~~~ (후략))
                                }
                            }
                        }.execute((Reservation) reservationDeleteList.get(position));
                    }
                });
                infoDlg.setNegativeButton("취소",null);
                infoDlg.show();
            }
        });

        return convertView;
    }
}
