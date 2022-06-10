package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.parentsletterproject.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase database;

    private EditText idText, passwordText;
    private Button loginButton, signButton;

    String sql;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        helper = new DBHelper(LoginActivity.this);
        database = helper.getWritableDatabase();

        idText = findViewById( R.id.idText );
        passwordText = findViewById( R.id.passwordText );

        signButton = findViewById( R.id.signButton );

        loginButton = findViewById( R.id.loginButton );
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString();
                String userPass = passwordText.getText().toString();

              if(userID.length() == 0 || userPass.length() == 0){//아이디와 비밀번호를 입력하지 않았을 경우
                  Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                  return;
              }
              sql = "SELECT id FROM user WHERE id = '" + userID + "'";
              cursor = database.rawQuery(sql, null);

              if(cursor.getCount() != 1){//아이디가 틀렸을 경우
                  Toast.makeText(LoginActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                  return;
              }

              sql = "SELECT password FROM user WHERE id = '"+ userID + "'";
              cursor = database.rawQuery(sql, null);
              cursor.moveToNext();
              if (!userPass.equals(cursor.getString(0))){//비밀번호가 틀렸을 경우
                  Toast.makeText(LoginActivity.this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                  return;
              }
              else{//로그인 성공
                  Toast.makeText(LoginActivity.this, "로그인 성공.", Toast.LENGTH_SHORT).show();
                  {//선생인지 학부모인지
                      sql = "SELECT is_teacher FROM user WHERE id = '"+ userID + "'";
                      cursor = database.rawQuery(sql, null);

                      cursor.moveToNext();

                      if(Boolean.parseBoolean(cursor.getString(0))){
                          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                          startActivity(intent);
                          finish();
                      }else
                      {
                          Intent intent = new Intent(getApplicationContext(), ParentsHomeActivity.class);
                          startActivity(intent);
                          finish();
                      }
                  }
              }
              cursor.close();

            }
        });

        signButton.setOnClickListener(new View.OnClickListener(){//회원가입 버튼 클릭
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "회원가입 화면으로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
//                finish();
            }
        });

    }
}