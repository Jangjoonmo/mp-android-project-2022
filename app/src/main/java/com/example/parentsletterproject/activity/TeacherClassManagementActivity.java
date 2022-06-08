package com.example.parentsletterproject.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.parentsletterproject.R;
import com.example.parentsletterproject.action.ClassAdapter;
import com.example.parentsletterproject.server.ClassName;
import com.example.parentsletterproject.server.Classroom;
import com.example.parentsletterproject.server.ClassroomList;
import com.example.parentsletterproject.server.RetrofitClient;
import com.example.parentsletterproject.server.RetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherClassManagementActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter classAdapter;
    private ArrayAdapter arrayAdapter;
    private Button addButton;
    private Button delButton;
    private Classroom classroom;
    private List<ClassName> classNameList;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_management);

        // 반 추가
        addButton = (Button) findViewById(R.id.add_class_button);
        addButton.setOnClickListener(v -> {

            AlertDialog.Builder ad = new AlertDialog.Builder(TeacherClassManagementActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.add_class, null);
            ad.setView(view);

            final Button submit = (Button) view.findViewById(R.id.add_class_submit);
            final EditText className = (EditText) view.findViewById(R.id.edittext_add_class_name);
            final EditText tId = (EditText) view.findViewById(R.id.edittext_add_teacher_id);
            final EditText tName = (EditText) view.findViewById(R.id.edittext_add_teacher_name);

            final AlertDialog dialog = ad.create();
            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String resultClassName = className.getText().toString();
                    String resultTId = tId.getText().toString();
                    String resultTName = tName.getText().toString();

                    retrofitClient = RetrofitClient.getInstance();
                    retrofitInterface = RetrofitClient.getRetrofitInterface();
                    retrofitInterface.putClassroom(resultClassName, resultTName, resultTId).enqueue(new Callback<Classroom>() {
                        @Override
                        public void onResponse(Call<Classroom> call, Response<Classroom> response) {

                            Log.d("Retrofit PUT", "삽입 성공");
                        }

                        @Override
                        public void onFailure(Call<Classroom> call, Throwable t) {
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

        // 반 삭제
        delButton = (Button)findViewById(R.id.del_class_button);
        delButton.setOnClickListener(view -> {

            AlertDialog.Builder ad = new AlertDialog.Builder(TeacherClassManagementActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            View view1 = inflater.inflate(R.layout.del_class, null);
            ad.setView(view1);

            ArrayList<String> item = new ArrayList<>();
            retrofitClient = RetrofitClient.getInstance();
            retrofitInterface = RetrofitClient.getRetrofitInterface();
            retrofitInterface.getClassName().enqueue(new Callback<List<ClassName>>() {
                @Override
                public void onResponse(Call<List<ClassName>> call, Response<List<ClassName>> response) {
                    List<ClassName> classNames = response.body();
                    for (ClassName className : classNames) {
                        item.add(className.getClassName());
                    }
                }

                @Override
                public void onFailure(Call<List<ClassName>> call, Throwable t) {
                    Log.e("Retrofit spinner", t.getMessage().toString());
                }
            });

//            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spin_dropdown, item);
//            adapter.notifyDataSetChanged();

            final AlertDialog dialog = ad.create();
            final Button submit = (Button) view1.findViewById(R.id.del_class_submit);
            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    editText = (EditText) view1.findViewById(R.id.class_name_text);
                    String className = editText.getText().toString();
                    retrofitInterface.deleteClassroom(className).enqueue(new Callback<ResponseBody>() {

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

        // 반 조회
        recyclerView = findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        // recyclerview No adapter attached; skipping layout 에러 로그가 떠서 콜백 함수 전에 setAdapter
        classAdapter = new ClassAdapter(new ArrayList<>());
        recyclerView.setAdapter(classAdapter);

        retrofitClient = RetrofitClient.getInstance();
        retrofitInterface = RetrofitClient.getRetrofitInterface();
        retrofitInterface.getClassroomList().enqueue(new Callback<List<ClassroomList>>() {
            @Override
            public void onResponse(@NonNull Call<List<ClassroomList>> call, @NonNull Response<List<ClassroomList>> response) {
                classAdapter = new ClassAdapter(response.body());
                recyclerView.setAdapter(classAdapter);
                Log.d("Retrofit GET", "조회 성공");
            }

            @Override
            public void onFailure(@NonNull Call<List<ClassroomList>> call, @NonNull Throwable t) {
                Log.e("Retrofit GET", t.getMessage().toString());
            }
        });

    }

}