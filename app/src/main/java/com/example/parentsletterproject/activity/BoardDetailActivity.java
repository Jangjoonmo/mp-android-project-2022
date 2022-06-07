package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.parentsletterproject.R;

public class BoardDetailActivity extends AppCompatActivity {

    // 선택한 게시물의 번호
    String board_seq = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        // ListActivity 에서 넘긴 변수들을 받아줌
        board_seq = getIntent().getStringExtra("board_seq");




    }

}