package com.example.homelearnersguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Form extends AppCompatActivity {

        Button form_button;

        Spinner YearSpinner, SectionSpinner, LearningSpinner;
        EditText regName,regEmail,regPassword,regStudentName;

        FirebaseDatabase UsersDatabase;
        DatabaseReference reference,list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        YearSpinner = (Spinner) findViewById(R.id.YearSpinnerStudent);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Form.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YearSpinner.setAdapter(myAdapter);

        SectionSpinner = (Spinner) findViewById(R.id.SectionSpinnerStudent);

        ArrayAdapter<String> Section = new ArrayAdapter<String>(Form.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.section));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SectionSpinner.setAdapter(Section);

        LearningSpinner = (Spinner) findViewById(R.id.LearningSpinnerStudent);

        ArrayAdapter<String> Learning = new ArrayAdapter<String>(Form.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.learningSystem));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        LearningSpinner.setAdapter(Learning);


        form_button = findViewById(R.id.submit_button);
        regEmail = findViewById(R.id.emailInput);
        regName = findViewById(R.id.studentNumberInput);
        regPassword = findViewById(R.id.PasswordInputStudent);
        regStudentName = findViewById(R.id.addstudentfullname);



//        form_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Form.this,Home.class);
//                startActivity(intent);
//            }
//        });

        form_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty((regName.getText().toString()))){
                    Toast.makeText(Form.this, "Please Enter a Username", Toast.LENGTH_SHORT).show();
                }

                else{
                    final DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("users");

                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String searchUser = regName.getText().toString();


                            if(dataSnapshot.child(searchUser).exists()){
                                Toast.makeText(Form.this,"USER IS ALREADY REGISTERED",Toast.LENGTH_SHORT).show();
                            }

                            else{

                                UsersDatabase = FirebaseDatabase.getInstance();
                                reference = UsersDatabase.getReference("users");
                                list = UsersDatabase.getReference("students");

                                String email = regEmail.getText().toString();
                                String userName = regName.getText().toString();
                                String learningSystem = LearningSpinner. getSelectedItem().toString();
                                String password = regPassword.getText().toString();
                                String section = SectionSpinner. getSelectedItem().toString();
                                String userType = "Student";
                                String year = YearSpinner. getSelectedItem().toString();
                                String studentFullName = regStudentName.getText().toString();

                                studentHelper studentHelper = new studentHelper(email, learningSystem, password, section, userName,userType,year,studentFullName);

                                reference.child(userName).setValue(studentHelper);
                                list.child(userName).setValue(studentHelper);

                                Toast.makeText(Form.this,"USER REGISTERED",Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }
}