package com.seoultech.lesson.souffle.ui.option;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.seoultech.lesson.souffle.R;
import com.seoultech.lesson.souffle.ui.option.ItemData;

import java.util.ArrayList;

public class TimeListAdapter extends BaseAdapter {

    LayoutInflater inflater = null;
    private ArrayList<ItemData> m_oData = null;
    private int nListCnt = 0;

    public TimeListAdapter(ArrayList<ItemData> _oData)
    {
        m_oData = _oData;
        nListCnt = m_oData.size();
    }

    @Override
    public int getCount() {
        return nListCnt;
    }

    @Override
    public Object getItem(int i) {
        return m_oData.get(i);
    }

    public Object getItemList() { return m_oData; }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            final Context context = parent.getContext();
            if (inflater == null)
            {
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            convertView = inflater.inflate(R.layout.list_layout, parent, false);
        }

        TextView oTextTitle = (TextView) convertView.findViewById(R.id.textTitle);
        CheckBox checkBox = convertView.findViewById(R.id.customCheckbox);
        checkBox.setClickable(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                m_oData.get(position).isChecked = b;
            }
        });
        checkBox.setFocusable(false);
        checkBox.setChecked(m_oData.get(position).isChecked);

        oTextTitle.setText(m_oData.get(position).time);
        checkBox.setEnabled(m_oData.get(position).isEnable);
        return convertView;
    }
}
