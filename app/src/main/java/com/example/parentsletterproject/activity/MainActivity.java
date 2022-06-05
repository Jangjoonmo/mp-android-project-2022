package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.parentsletterproject.R;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_TCM = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TeacherClassManagementActivity.class);
            startActivity(intent);
        });

        Button button1 = findViewById(R.id.button3);
        button1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BoardRegisterActivity.class);
            startActivity(intent);
        });

    }
}