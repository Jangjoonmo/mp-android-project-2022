package com.example.parentsletterproject.action;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parentsletterproject.R;
import com.example.parentsletterproject.server.Board;

import java.util.ArrayList;
import java.util.List;



public class BoardAdapter extends BaseAdapter {

    private List<Board> board;
    LayoutInflater inflater;

    public BoardAdapter(LayoutInflater inflater, List<Board> board) {
        this.board = board;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return board.size();
    }

    @Override
    public Object getItem(int position) {
        return board.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.board_recycler, null);
        }

        TextView postName = (TextView) convertView.findViewById(R.id.post_name);

        postName.setText(board.get(position).getPostName());

        return convertView;
    }

}