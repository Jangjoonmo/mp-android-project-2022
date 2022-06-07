package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.parentsletterproject.R;

public class BoardRegisterActivity extends AppCompatActivity {

    // 로그에 사용할 TAG 변수 선언
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    EditText title_et, content_et;
    Button reg_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_register);

        title_et = findViewById(R.id.title_et);
        content_et = findViewById(R.id.content_et);
        reg_button = findViewById(R.id.reg_button);

        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

    }
}

//    class RegBoard extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            Log.d(TAG, "onPreExecute");
//        }
//
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            Log.d(TAG, "onPostExecute, " + result);
//
//            if (result.equals("success")) {
//// 결과값이 success 이면
//// 토스트 메시지를 뿌리고
//// 이전 액티비티(ListActivity)로 이동,
//// 이때 ListActivity 의 onResume() 함수 가 호출되며, 데이터를 새로 고침
//                Toast.makeText(RegisterActivity.this, "등록되었습니다.", Toast.LENGTH_SHORT).show();
//                finish();
//            } else {
//                Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    }
//}
