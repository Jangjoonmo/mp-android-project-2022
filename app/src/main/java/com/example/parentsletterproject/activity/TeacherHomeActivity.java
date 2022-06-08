package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.parentsletterproject.R;

public class TeacherHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        String meal;

        Intent intent= getIntent();
        meal = intent.getStringExtra("식단");
        System.out.println(meal);
        TextView meal_notice = (TextView) findViewById(R.id.meal_notice);
        meal_notice.setText(meal);
    }
}