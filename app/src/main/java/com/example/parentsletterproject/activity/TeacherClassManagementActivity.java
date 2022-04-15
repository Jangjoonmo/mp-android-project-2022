package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.parentsletterproject.R;
import com.example.parentsletterproject.action.ListViewAdapter;

public class TeacherClassManagementActivity extends AppCompatActivity {

    private Button addButton;
    private Button delButton;
    private ListView listView;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_class_management);

        // 반 추가
        addButton = (Button) findViewById(R.id.add_student_button);
        addButton.setOnClickListener(v -> {

            AlertDialog.Builder ad = new AlertDialog.Builder(TeacherClassManagementActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.add_class, null);
            ad.setView(view);

            final Button submit = (Button) view.findViewById(R.id.add_class_submit);
            final EditText className = (EditText) view.findViewById(R.id.edittext_add_class_name);
            final EditText teacherInCharge = (EditText) view.findViewById(R.id.edittext_add_class_teacher_in_charge);

            final AlertDialog dialog = ad.create();
            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String resultClassName = className.getText().toString();
                    String resultTeacherInCharge = teacherInCharge.getText().toString();

                    dialog.dismiss();
                }
            });

            dialog.show();

        });

        // 반 삭제
        delButton = (Button)findViewById(R.id.del_student_button);
        delButton.setOnClickListener(view -> {

            AlertDialog.Builder ad = new AlertDialog.Builder(TeacherClassManagementActivity.this);
            ad.setTitle("반 삭제");
            ad.setMessage("삭제할 반을 선택하세요");

            final Spinner spinner = new Spinner(TeacherClassManagementActivity.this);
            ad.setView(spinner);

            ad.setPositiveButton("확인", (dialogInterface, i) -> {

            });

            ad.setNegativeButton("취소", (dialogInterface, i) -> dialogInterface.dismiss());

            ad.show();

        });

        // 반 목록
        adapter = new ListViewAdapter();

        listView = (ListView) findViewById(R.id.listview_class);
        listView.setAdapter(adapter);

        adapter.addItem("나무반", "배승원");
        adapter.addItem("하늘반", "조수아");
        adapter.addItem("바다반", "장준모");
        adapter.addItem("나무반", "배승원");
        adapter.addItem("하늘반", "조수아");
        adapter.addItem("바다반", "장준모");
        adapter.addItem("나무반", "배승원");
        adapter.addItem("하늘반", "조수아");
        adapter.addItem("바다반", "장준모");
        adapter.addItem("나무반", "배승원");
        adapter.addItem("하늘반", "조수아");
        adapter.addItem("바다반", "장준모");

    }
}