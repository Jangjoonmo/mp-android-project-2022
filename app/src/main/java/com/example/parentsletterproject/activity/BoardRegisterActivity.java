package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.parentsletterproject.R;
import com.example.parentsletterproject.server.Board;
import com.example.parentsletterproject.server.RetrofitClient;
import com.example.parentsletterproject.server.RetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardRegisterActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;

    // 사용할 컴포넌트 선언
    EditText title_et, content_et;
    Button reg_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_register);

        title_et = findViewById(R.id.title_et);
        content_et = findViewById(R.id.content_et);
        reg_button = findViewById(R.id.reg_button);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String resultPostName = title_et.getText().toString();
                String resultPostBody = content_et.getText().toString();

                System.out.println(resultPostName);
                System.out.println(resultPostBody);

                retrofitClient = RetrofitClient.getInstance();
                retrofitInterface = RetrofitClient.getRetrofitInterface();
                retrofitInterface.putBoard(resultPostName, resultPostBody).enqueue(new Callback<Board>() {
                    @Override
                    public void onResponse(Call<Board> call, Response<Board> response) {
                        Log.d("Retrofit PUT", "삽입 성공");
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Board> call, Throwable t) {
                        Log.e("Retrofit PUT", t.getMessage().toString());
                    }
                });

            }
        });

    }
}