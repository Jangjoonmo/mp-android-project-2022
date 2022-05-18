package com.example.parentsletterproject.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parentsletterproject.R;
import com.example.parentsletterproject.server.RetrofitClient;
import com.example.parentsletterproject.server.RetrofitInterface;

public class TeacherStudentManagementActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private RetrofitInterface retrofitInterface;
    private Button addButton;
    private Button delButton;

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

            final Button submit = (Button) view.findViewById(R.id.add_class_submit);
            final EditText studentName = (EditText) view.findViewById(R.id.edittext_add_teacher_id);

            final AlertDialog dialog = ad.create();
            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    String resultClassName = studentName.getText().toString();




                    dialog.dismiss();
                }
            });

            dialog.show();

        });

    }

}