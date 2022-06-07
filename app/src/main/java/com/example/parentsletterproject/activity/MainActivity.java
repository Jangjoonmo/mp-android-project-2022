package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.parentsletterproject.R;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_TCM = 101;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);



        Button button1 = findViewById(R.id.class_manage);
        button1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TeacherClassManagementActivity.class);
            startActivity(intent);
        });

        Button button2 = findViewById(R.id.student);
        button2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TeacherStudentManagementActivity.class);
            startActivity(intent);
        });

        Button button3 = findViewById(R.id.posting);
        button3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BoardListActivity.class);
            startActivity(intent);
        });

        Button button4 = findViewById(R.id.meal);
        button4.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MealActivity.class);
            startActivity(intent);
        });
    }
}