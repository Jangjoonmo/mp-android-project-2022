package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parentsletterproject.R;
import com.example.parentsletterproject.server.Board;
import com.example.parentsletterproject.server.RetrofitClient;
import com.example.parentsletterproject.server.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardDetailActivity extends AppCompatActivity {

    private TextView postName;
    private TextView postBody;
    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;
    private Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        Intent intent = getIntent();
        int postNum = intent.getExtras().getInt("board_seq");

        postName = findViewById(R.id.post_up);
        postBody = findViewById(R.id.post_down);

        postName.setText("");
        postBody.setText("");

        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();
        retrofitInterface.getBoard(postNum).enqueue(new Callback<Board>() {

            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                postName.setText(response.body().getPostName().toString());
                postBody.setText(response.body().getPostBody().toString());
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {

            }
        });


        postName = (TextView) findViewById(R.id.post_up);
        postBody = (TextView) findViewById(R.id.post_down);


    }

}