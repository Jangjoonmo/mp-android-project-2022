package com.example.parentsletterproject.action;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.parentsletterproject.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private TextView className;
    private TextView teacherInCharge;

    private ArrayList<ListViewItem> listViewItemArrayList = new ArrayList<ListViewItem>();

    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수 반환
    @Override
    public int getCount() {
        return listViewItemArrayList.size();
    }

    // 지정한 위치에 있는 데이터를 반환
    @Override
    public Object getItem(int position) {
        return listViewItemArrayList.get(position);
    }

    // 지정한 위치에 있는 데이터의 아이템 ID를 반환
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 반환
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final int pos = position;
        final Context context = viewGroup.getContext();

        // listview_item을 inflate하여 view 참조 획득
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item, viewGroup, false);
        }

        className = (TextView) view.findViewById(R.id.list_class_name);
        teacherInCharge = (TextView) view.findViewById(R.id.list_teacher_in_charge);

        ListViewItem listViewItem = listViewItemArrayList.get(pos);

        className.setText(listViewItem.getClassName());
        teacherInCharge.setText(listViewItem.getTeacherInCharge());

        return view;
    }

    public void addItem(String className, String teacherInCharge) {
        ListViewItem item = new ListViewItem();

        item.setClassName(className);
        item.setTeacherInCharge(teacherInCharge);

        listViewItemArrayList.add(item);
    }
}
