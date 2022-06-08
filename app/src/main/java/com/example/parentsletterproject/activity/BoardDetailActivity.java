package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parentsletterproject.R;

public class BoardDetailActivity extends AppCompatActivity {

    private TextView postName;
    private TextView postBody;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        intent = getIntent();

        postName = (TextView) findViewById(R.id.post_up);
        postBody = (TextView) findViewById(R.id.post_down);


    }

}