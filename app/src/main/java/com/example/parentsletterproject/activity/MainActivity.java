package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.parentsletterproject.R;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_TCM = 101;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

//        String url = "https://open.neis.go.kr/hub/mealServiceDietInfo?KEY=c0dadaab5ff14ca48e48aa850bb3652a&Type=xml&pIndex=1&pSize=10&ATPT_OFCDC_SC_CODE=J10&SD_SCHUL_CODE=7751096";
//        OpenAPI dust = new OpenAPI(url);
//        dust.execute();
//
//        Button button = findViewById(R.id.button2);
//        button.setOnClickListener(view -> {
//            Intent intent = new Intent(getApplicationContext(), TeacherClassManagementActivity.class);
//            startActivity(intent);
//        });
    }
}