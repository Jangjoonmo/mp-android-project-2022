package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.parentsletterproject.R;

public class TeacherClassManagementActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button searchButton;
    private Button addButton;
    private Button delButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_management);

        // 반 조회
        searchButton = (Button)findViewById(R.id.search_class_button);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder ad = new AlertDialog.Builder(TeacherClassManagementActivity.this);
                ad.setTitle("반 조회");
                ad.setMessage("조회할 원생의 이름을 입력하세요");

                final EditText et = new EditText(TeacherClassManagementActivity.this);
                ad.setView(et);

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String result = et.getText().toString();
                        Log.e(result, result);
                        dialogInterface.dismiss();

                    }
                });

                ad.setNegativeButton("취소", ((dialogInterface, i) -> dialogInterface.dismiss()));

                ad.show();
            }
        });

        addButton = (Button)findViewById(R.id.add_class_button);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder ad = new AlertDialog.Builder(TeacherClassManagementActivity.this);
                ad.setTitle("반 추가");
                ad.setMessage("추가할 반을 선택하세요");

                final Spinner spinner = new Spinner(TeacherClassManagementActivity.this);
                ad.setView(spinner);

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                ad.setNegativeButton("취소", (dialogInterface, i) -> dialogInterface.dismiss());

                ad.show();

            }
        });

        delButton = (Button)findViewById(R.id.del_class_button);
        delButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                AlertDialog.Builder ad = new AlertDialog.Builder(TeacherClassManagementActivity.this);
                ad.setTitle("반 삭제");
                ad.setMessage("삭제할 반을 선택하세요");

                final Spinner spinner = new Spinner(TeacherClassManagementActivity.this);
                ad.setView(spinner);

                ad.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }

                });

                ad.setNegativeButton("취소", (dialogInterface, i) -> dialogInterface.dismiss());

                ad.show();

            }
        });
    }
}