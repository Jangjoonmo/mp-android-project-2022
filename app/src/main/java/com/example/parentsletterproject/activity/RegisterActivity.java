package com.example.parentsletterproject.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.parentsletterproject.R;

import org.json.JSONException;
import org.json.JSONObject;

class DBHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "kinder_book.db";
    private static final int DATABASE_VERSION = 3;

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE user (name text, id text, password text, is_teacher boolean)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void insertUser(SQLiteDatabase db, String name, String id, String password, boolean isTeacher){
        Log.i("TAG", "회원가입 시 실행");
        db.beginTransaction();
        try{
            String sql = "INSERT INTO user (name, id, password, is_teacher)" +
                    "values('"+ name + "', '"+ id +"', '" + password + "', '" + isTeacher + "')";
            db.execSQL(sql);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }
    }
}

public class RegisterActivity extends AppCompatActivity {

    DBHelper helper;
    SQLiteDatabase database;

    private EditText idText, passwordText, nameText, pwckText;
    private RadioGroup teacher_group;
    private Button signButton, delete, checkButton;

    boolean teacherYes ,checkId, checkTeacher;

    String sql;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("TAG","회원가입 액티비티가 만들어짐");
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );



        //아이디값 찾아주기
        nameText = findViewById(R.id.join_name);
        idText = findViewById( R.id.join_id );
        passwordText = findViewById( R.id.join_password );
        pwckText = findViewById(R.id.join_pwck);
        teacher_group = findViewById(R.id.is_teacher);
        checkTeacher=false;

        teacher_group.setOnCheckedChangeListener((radioGroup, i) -> {
            checkTeacher=true;
                if (i == R.id.teacher_yes) {                // 첫 번째 버튼이 선택 되었을 때
                    teacherYes = true;
                } else if (i == R.id.parents_yes) {      // 두 번째 버튼이 선택 되었을 때
                    teacherYes = false;
                }
        });
        delete = findViewById(R.id.join_delete);
        delete.setOnClickListener(new View.OnClickListener() {//취소 버튼을 클릭하였을 시
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "로그인 화면으로 돌아갑니다.", Toast.LENGTH_SHORT);
                finish();
            }
        });

        checkButton = findViewById(R.id.check_button);
        checkButton.setOnClickListener(new View.OnClickListener(){//확인 버튼으로 이미 존재하는 아이디인지 확인
            @Override
            public void onClick(View view) {
                sql = "SELECT id FROM user WHERE id = '" + idText.getText().toString() + "'";
                cursor = database.rawQuery(sql, null);
                if(cursor.getCount() != 0){
                    Toast toast = Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    checkId = false;
                }else{
                    Toast.makeText(RegisterActivity.this, "가능한 아이디입니다.", Toast.LENGTH_SHORT).show();
                    checkId=true;
                }
            }
        });


        //데이터베이스 생성
        helper = new DBHelper(this);
        database = helper.getWritableDatabase();

        //회원가입 버튼 클릭 시 수행
        signButton = findViewById( R.id.join_button );
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = idText.getText().toString();
                String userPass = passwordText.getText().toString();
                String userPwck = pwckText.getText().toString();
                String userName = nameText.getText().toString();

                //입력값이 없을 경우
                if(userID.length() == 0 || userPass.length()==0 || userName.length()==0 || userPwck.length()==0 || !checkTeacher) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "빈칸 없이 입력해 주세요.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                //비밀번호가 불일치할 경우
                if(!userPass.equals(userPwck)){
                    Toast toast = Toast.makeText(RegisterActivity.this, "비밀번호가 다릅니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                if(checkId){//아이디 중복 확인이 되었을 경우
                    helper.insertUser(database, userName, userID, userPass, teacherYes);
                    Toast toast = Toast.makeText(RegisterActivity.this, "가입이 완료되었습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    Log.i("TAG", "데이터베이스에 유저 등록 완료");
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
//                    finish();
                }
                else{
                    Toast.makeText(RegisterActivity.this, "아이디 중복 확인을 해주세요.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}