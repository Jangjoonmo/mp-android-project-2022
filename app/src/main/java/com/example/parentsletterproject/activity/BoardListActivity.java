package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.parentsletterproject.R;
import com.example.parentsletterproject.action.BoardAdapter;
import com.example.parentsletterproject.server.Board;
import com.example.parentsletterproject.server.RetrofitClient;
import com.example.parentsletterproject.server.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter boardAdapter;
    private ArrayAdapter arrayAdapter;
    private ListView listView;
    private Button button;
    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);

        recyclerView = findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        boardAdapter = new BoardAdapter(new ArrayList<>());
        recyclerView.setAdapter(boardAdapter);

        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();
        retrofitInterface.getBoardList().enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                boardAdapter = new BoardAdapter(response.body());
                recyclerView.setAdapter(boardAdapter);
                Log.d("Retrofit GET", "조회 성공");
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                Log.e("Retrofit GET", t.getMessage().toString());
            }
        });

//        listView = findViewById(R.id.listView);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////                Toast.makeText(BoardListActivity.this, parent.getItemIdAtPosition(position) + "클릭", Toast.LENGTH_SHORT).show();
////
////                Intent intent = new Intent(BoardListActivity.this, BoardDetailActivity.class);
////                intent.putExtra("board_seq", seqList.get(position));
////                startActivity(intent);
//            }
//        });
//
//        button = findViewById(R.id.reg_button);
//        button.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(BoardListActivity.this, BoardRegisterActivity.class);
////                startActivity(intent);
//            }
//        });

    }



}