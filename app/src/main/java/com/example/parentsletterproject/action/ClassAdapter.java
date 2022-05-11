package com.example.parentsletterproject.action;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parentsletterproject.R;
import com.example.parentsletterproject.server.ClassroomList;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private List<ClassroomList> items;

    public ClassAdapter(List<ClassroomList> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_recycler, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ViewHolder holder, int position) {
        ClassroomList item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView classId, className, tName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            classId = itemView.findViewById(R.id.class_id);
            className = itemView.findViewById(R.id.class_name);
            tName = itemView.findViewById(R.id.teacher_in_charge);
        }

        public void setItem(ClassroomList item) {
            classId.setText(item.getClassId());
            className.setText(item.getClassName());
            tName.setText(item.getTName());
        }
    }

}
