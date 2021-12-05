package com.example.homelearnersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class StudentInfoView extends AppCompatActivity {

    TextView studentName, studentEmail, studentSection,studentYear,studentLearning,fullName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info_view);

        studentName = findViewById(R.id.NameInfoViewStudent);
        studentEmail = findViewById(R.id.EmailInfoViewStudent);
        studentSection = findViewById(R.id.SectionInfoViewStudent);
        studentYear = findViewById(R.id.YearInfoViewStudent);
        studentLearning = findViewById(R.id.LearningSystemInfoViewStudent);
        fullName = findViewById(R.id.viewFullName);

        String nem = getIntent().getStringExtra("Name");
        String emil = getIntent().getStringExtra("Email");
        String siksyon = getIntent().getStringExtra("Section");
        String yir = getIntent().getStringExtra("Year");
        String lerning = getIntent().getStringExtra("Learning");
        String fullnem = getIntent().getStringExtra("Fullname");

        studentName.setText(nem);
        studentEmail.setText(emil);
        studentSection.setText(siksyon);
        studentYear.setText(yir);
        studentLearning.setText(lerning);
        fullName.setText(fullnem);

    }
}