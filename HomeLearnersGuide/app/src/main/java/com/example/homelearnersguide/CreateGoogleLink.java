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

public class CreateGoogleLink extends AppCompatActivity {

    EditText subCode,subTitle,subLink;
    Spinner YearSpinner,secSpinner,learningSpinner;
    Button retrieveGLList,addLink;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_google_link);

        subCode = findViewById(R.id.SubCode);
        subTitle = findViewById(R.id.SubTitle);
        subLink = findViewById(R.id.SubLink);

        retrieveGLList = findViewById(R.id.RetieveGLBtn);
        addLink = findViewById(R.id.CreateGLLink);

        YearSpinner = (Spinner) findViewById(R.id.YearGLSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CreateGoogleLink.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YearSpinner.setAdapter(myAdapter);

        secSpinner = (Spinner) findViewById(R.id.SectionGLSpinner);

        ArrayAdapter<String> sec = new ArrayAdapter<String>(CreateGoogleLink.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.section));
        sec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secSpinner.setAdapter(sec);

        learningSpinner = (Spinner) findViewById(R.id.classLearningSystemSpinner);

        ArrayAdapter<String> learn = new ArrayAdapter<String>(CreateGoogleLink.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.learningSystem));
        learn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        learningSpinner.setAdapter(learn);


        retrieveGLList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateGoogleLink.this,GLClassList.class);
                startActivity(intent);

            }
        });

        addLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty((subTitle.getText().toString()))){
                    Toast.makeText(CreateGoogleLink.this, "Please Enter Subject Title", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty((subCode.getText().toString()))){
                    Toast.makeText(CreateGoogleLink.this, "Please Enter Subject Code", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty((subLink.getText().toString()))){
                    Toast.makeText(CreateGoogleLink.this, "Please Enter Subject Link", Toast.LENGTH_SHORT).show();
                }

                else{
                    String subYear = YearSpinner. getSelectedItem().toString();
                    String subSec = secSpinner. getSelectedItem().toString();
                    final DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("GoogleClassroom").child(subYear).child(subSec);

                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String searchUser = subTitle.getText().toString();


                            if(dataSnapshot.child(searchUser).exists()){
                                Toast.makeText(CreateGoogleLink.this,"SUBJECT ALREADY ADDED REGISTERED",Toast.LENGTH_SHORT).show();
                            }

                            else{

                                UsersDatabase = FirebaseDatabase.getInstance();
                                reference = UsersDatabase.getReference("GoogleClassroom").child(learningSpinner.getSelectedItem().toString()).child(subYear).child(subSec);

                                String subjectCode = subCode.getText().toString();
                                String subjectTitle = subTitle.getText().toString();
                                String subjectLink = subLink. getText().toString();
                                String subjectYear =  YearSpinner. getSelectedItem().toString();
                                String subjectSection = secSpinner. getSelectedItem().toString();
                                String learningSystem = learningSpinner. getSelectedItem().toString();



                                googleHelper googleHelper = new googleHelper(subjectCode, subjectTitle,subjectLink,subjectYear,subjectSection,learningSystem);

                                reference.child(subjectTitle).setValue(googleHelper);

                                Toast.makeText(CreateGoogleLink.this,"Google Classroom Added",Toast.LENGTH_LONG).show();

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