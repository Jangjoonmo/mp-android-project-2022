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
    }

    public void insertUser(SQLiteDatabase db, String name, String id, String password, boolean isTeacher){
        Log.i("TAG", "회원가입 시 실행");
        db.beginTransaction();
        try{
            String sql = "INSERT INTO " + DATABASE_NAME + "(name, id, password, is_teacher)" +
                    "values('"+ name + "', '"+ id +"', '" + password + "' '" + isTeacher + "' )";
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

    private Button signButton, delete;
    boolean teacherYes;


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
        teacher_group.setOnCheckedChangeListener((radioGroup, i) -> {
                if (i == R.id.teacher_yes) {                // 첫 번째 버튼이 선택 되었을 때
                    teacherYes = true;
                } else if (i == R.id.parents_yes) {      // 두 번째 버튼이 선택 되었을 때
                    teacherYes = false;
                }
        });
//        is_teacher = findViewById(R.id.teacher_yes);
//        is_parents = findViewById(R.id.parents_yes);


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
                if(userID.length() == 0 || userPass.length()==0 || userName.length()==0 || userPwck.length()==0) {
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

                //이미 존재하는 아이디인지 확인
                sql = "SELECT id FROM user WHERE id = '" + userID + "'";
                cursor = database.rawQuery(sql, null);
                if(cursor.getCount() != 0){
                    Toast toast = Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{//아니라면 데이터베이스에 입력
                    helper.insertUser(database, userName, userID, userPass, teacherYes);
                    Toast toast = Toast.makeText(RegisterActivity.this, "가입이 완료되었습니다.", Toast.LENGTH_SHORT);
                    toast.show();
                    Log.i("TAG", "데이터베이스에 유저 등록 완료");
                    Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent);
//                    finish();
                }

            }
        });
    }
}