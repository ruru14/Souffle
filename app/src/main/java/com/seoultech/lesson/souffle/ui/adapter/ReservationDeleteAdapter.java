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
import com.seoultech.lesson.souffle.util.Changer;

import java.util.List;

public class ReservationDeleteAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    private List<Reservation> reservationDeleteList;
    private AppController appController;
    private DeleteListener deleteListener;

    public ReservationDeleteAdapter(List<Reservation> list, DeleteListener listener) {
        this.deleteListener = listener;
        this.reservationDeleteList = list;
        appController = AppController.getInstance();
    }

    @Override
    public int getCount() {
        return reservationDeleteList.size();
    }

    @Override
    public Object getItem(int position) {
        return reservationDeleteList.get(position);
    }

    public List<Reservation> getItemList() { return reservationDeleteList; }

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
        List<Reservation> reservationDeleteList = this.getItemList();

        TextView date = convertView.findViewById(R.id.txt_date_delete);
        TextView buildingName = convertView.findViewById(R.id.txt_buildingName_delete);
        TextView roomNumber = convertView.findViewById(R.id.txt_roomNumber_delete);
        TextView time = convertView.findViewById(R.id.txt_time_delete);
        Button btnDeleteReservation = convertView.findViewById(R.id.btn_delete_list);
        Button btnConfirm = convertView.findViewById(R.id.btn_confirm_list);

        date.setText(reservationDeleteList.get(position).getDate());
        buildingName.setText(Changer.buildingChange(reservationDeleteList.get(position).getBuilding()));
        roomNumber.setText(Integer.toString(reservationDeleteList.get(position).getRoomNumber()));
        time.setText(reservationDeleteList.get(position).getTimeStart() + "~" + reservationDeleteList.get(position).getTimeEnd());

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder infoDlg = new AlertDialog.Builder(v.getContext());
                infoDlg.setTitle("상세 정보");
                infoDlg.setMessage("학번 : " + reservationDeleteList.get(position).getStudentNumber() +
                        "\n이름 : " + reservationDeleteList.get(position).getName() +
                        "\n예약 날짜 : " + reservationDeleteList.get(position).getDate() +
                        "\n예약 시간 : " + reservationDeleteList.get(position).getTimeStart() + "~" +
                        reservationDeleteList.get(position).getTimeEnd() +
                        "\n건물명 : " + reservationDeleteList.get(position).getBuilding() +
                        "\n사용 인원 : " + reservationDeleteList.get(position).getTotalMember() +
                        "\n강의실 : " + reservationDeleteList.get(position).getRoomNumber() +
                        "\n목적 : " + reservationDeleteList.get(position).getPurpose());
                infoDlg.setPositiveButton("확인",null);
                infoDlg.show();
            }
        });

        btnDeleteReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder infoDlg = new AlertDialog.Builder(v.getContext());
                infoDlg.setTitle("삭제 여부");
                infoDlg.setMessage("다시 복구할 수 없습니다.\n정말 해당 예약을 지우시겠습니까?");
                infoDlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AsyncTask<Object, Integer, Boolean>() {

                            @Override
                            protected Boolean doInBackground(Object... objects) {
                                Reservation temp = (Reservation) objects[0];
                                System.out.println(temp.toString());
                                return appController.deleteReservation((Reservation) objects[0]);
                            }

                            @Override
                            protected void onPreExecute() {
                                // 로딩
                                super.onPreExecute();
                            }

                            @Override
                            protected void onPostExecute(Boolean aBoolean) {
                                if(aBoolean){ // 삭제 성공시 (로딩 없애기도 해줘야함) + 새로고침
                                    reservationDeleteList.remove(position);
                                    deleteListener.onDelete();
                                }else{ // 삭제 실패시 (로딩 ~~~ (후략))
                                }
                            }
                        }.execute(reservationDeleteList.get(position));
                    }
                });
                infoDlg.setNegativeButton("취소",null);
                infoDlg.show();
            }
        });

        return convertView;
    }
}
