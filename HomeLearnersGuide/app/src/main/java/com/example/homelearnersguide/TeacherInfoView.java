package com.example.homelearnersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TeacherInfoView extends AppCompatActivity {

    TextView teacherName, teacherEmail, teacherType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info_view);

        teacherName = findViewById(R.id.NameInfoViewTeacher);
        teacherEmail = findViewById(R.id.EmailInfoViewTeacher);
        teacherType = findViewById(R.id.TypeInfoViewTeacher);

        String nem = getIntent().getStringExtra("Name");
        String emil = getIntent().getStringExtra("Email");
        String taip = getIntent().getStringExtra("Type");

        teacherName.setText(nem);
        teacherEmail.setText(emil);
        teacherType.setText(taip);


    }
}