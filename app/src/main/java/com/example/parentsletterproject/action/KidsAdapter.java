package com.example.parentsletterproject.action;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parentsletterproject.R;
import com.example.parentsletterproject.server.Kids;

import java.util.List;

public class KidsAdapter extends RecyclerView.Adapter<KidsAdapter.ViewHolder> {

    private List<Kids> items;

    public KidsAdapter(List<Kids> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public KidsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.kids_recycler, parent, false);
        return new KidsAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull KidsAdapter.ViewHolder holder, int position) {
        Kids item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView kidsName, classId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            kidsName = itemView.findViewById(R.id.student_name_textview);
            classId = itemView.findViewById(R.id.kids_class_id);
        }

        public void setItem(Kids item) {

            kidsName.setText(item.getKidsName());
            classId.setText(item.getClassId());

        }
    }
}
