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
        setContentView(R.layout.activity_main);

//        String url = "https://open.neis.go.kr/hub/mealServiceDietInfo?KEY=c0dadaab5ff14ca48e48aa850bb3652a&Type=xml&pIndex=1&pSize=10&ATPT_OFCDC_SC_CODE=J10&SD_SCHUL_CODE=7751096";
//        OpenAPI dust = new OpenAPI(url);
//        dust.execute();

        Button button = findViewById(R.id.class_button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), TeacherClassManagementActivity.class);
            startActivity(intent);
        });



        Button button1 = findViewById(R.id.board_button);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), BoardListActivity.class);
            startActivity(intent);
        });
    }
}