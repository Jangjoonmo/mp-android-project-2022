package com.example.parentsletterproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parentsletterproject.R;
import com.example.parentsletterproject.action.ClassAdapter;
import com.example.parentsletterproject.action.KidsAdapter;
import com.example.parentsletterproject.server.Classroom;
import com.example.parentsletterproject.server.Kids;
import com.example.parentsletterproject.server.RetrofitClient;
import com.example.parentsletterproject.server.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherStudentManagementActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;
    private Button addButton;
    private Button delButton;
    private EditText editText;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter kidsAdapter;
    private ArrayAdapter arrayAdapter;
    private Kids kids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_student_management);

        // 원생 추가
        addButton = (Button) findViewById(R.id.add_student_button);
        addButton.setOnClickListener(v -> {

            AlertDialog.Builder ad = new AlertDialog.Builder(TeacherStudentManagementActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.add_student, null);
            ad.setView(view);

            final Button submit = (Button) view.findViewById(R.id.add_student_submit);
            final EditText studentName = (EditText) view.findViewById(R.id.edittext_add_student_name);
            final EditText classId = (EditText) view.findViewById(R.id.edittext_add_class_id_for);

            final AlertDialog dialog = ad.create();
            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String resultStudentName = studentName.getText().toString();
                    String resultClassId = classId.getText().toString();

                    retrofitClient = RetrofitClient.getInstance();
                    retrofitInterface = RetrofitClient.getRetrofitInterface();
                    retrofitInterface.putKids(resultStudentName, resultClassId).enqueue(new Callback<Kids>() {
                        @Override
                        public void onResponse(Call<Kids> call, Response<Kids> response) {
                            Log.d("Retrofit PUT", "삽입 성공");
                        }

                        @Override
                        public void onFailure(Call<Kids> call, Throwable t) {
                            Log.e("Retrofit PUT", t.getMessage().toString());
                        }
                    });

                    dialog.dismiss();

                    finish();//인텐트 종료
                    overridePendingTransition(0, 0);//인텐트 효과 없애기
                    Intent intent = getIntent(); //인텐트
                    startActivity(intent); //액티비티 열기
                    overridePendingTransition(0, 0);//인텐트 효과 없애기

                }

            });

            dialog.show();

        });

        // 원생 삭제
        delButton = (Button) findViewById(R.id.del_student_button);
        delButton.setOnClickListener(view -> {

            AlertDialog.Builder ad = new AlertDialog.Builder(TeacherStudentManagementActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            View view1 = inflater.inflate(R.layout.del_student, null);
            ad.setView(view1);

            final AlertDialog dialog = ad.create();
            final Button submit = (Button) view1.findViewById(R.id.del_student_button);
            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    editText = (EditText) view1.findViewById(R.id.student_name_text);
                    String studentName = editText.getText().toString();
                    retrofitClient = RetrofitClient.getInstance();
                    retrofitInterface = RetrofitClient.getRetrofitInterface();
                    retrofitInterface.deleteKids(studentName).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Log.d("Retrofit DELETE", "삭제 성공");
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("Retrofit DELETE", t.getMessage().toString());
                        }
                    });

                    dialog.dismiss();

                    finish();//인텐트 종료
                    overridePendingTransition(0, 0);//인텐트 효과 없애기
                    Intent intent = getIntent(); //인텐트
                    startActivity(intent); //액티비티 열기
                    overridePendingTransition(0, 0);//인텐트 효과 없애기

                }
            });

            dialog.show();

        });

        recyclerView = findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // recyclerview No adapter attached; skipping layout 에러 로그가 떠서 콜백 함수 전에 setAdapter
        kidsAdapter = new ClassAdapter(new ArrayList<>());
        recyclerView.setAdapter(kidsAdapter);

        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();
        retrofitInterface.getKidsList().enqueue(new Callback<List<Kids>>() {
            @Override
            public void onResponse(@NonNull Call<List<Kids>> call, @NonNull Response<List<Kids>> response) {
                kidsAdapter = new KidsAdapter(response.body());
                recyclerView.setAdapter(kidsAdapter);
                Log.d("Retrofit GET", "조회 성공");
            }

            @Override
            public void onFailure(@NonNull Call<List<Kids>> call, @NonNull Throwable t) {
                Log.e("Retrofit GET", t.getMessage().toString());
            }
        });


    }




}