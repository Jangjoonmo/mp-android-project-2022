package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.parentsletterproject.R;

import java.util.ArrayList;

public class BoardListActivity extends AppCompatActivity {

    // for log
    final private String TAG = getClass().getSimpleName();

    ListView listView;
    Button button;

    // for listview
    ArrayList<String> titleList = new ArrayList<>();

    // for numbers
    ArrayList<String> seqList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);

        listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BoardListActivity.this, parent.getItemIdAtPosition(position) + "클릭", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(BoardListActivity.this, BoardDetailActivity.class);
                intent.putExtra("board_seq", seqList.get(position));
                startActivity(intent);
            }
        });

        button = findViewById(R.id.reg_button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardListActivity.this, BoardRegisterActivity.class);
                startActivity(intent);
            }
        });

    }



}