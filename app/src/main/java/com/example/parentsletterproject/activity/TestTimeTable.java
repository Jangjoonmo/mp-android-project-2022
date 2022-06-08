package com.example.parentsletterproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.parentsletterproject.R;
import com.github.tlaabs.timetableview.TimetableView;

public class TestTimeTable extends AppCompatActivity {

    private TimetableView timetable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_test_time_table);

        timetable = findViewById(R.id.timetable);
        if(EditTimeTableMain.getCrurrentWeek()-1 <6)
            timetable.setHeaderHighlight(EditTimeTableMain.getCrurrentWeek()-1);
        SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(this);
        String savedData = mPref.getString("timetable_demo","");
        if(savedData == null && savedData.equals("")) return;
        timetable.load(savedData);

    }


}