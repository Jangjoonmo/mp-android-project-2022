package com.example.parentsletterproject.activity;

import android.os.Bundle;

import com.example.parentsletterproject.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class mealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        TextView to_text = (TextView) findViewById(R.id.to_text);
        //to_text.setText();
    }
}
