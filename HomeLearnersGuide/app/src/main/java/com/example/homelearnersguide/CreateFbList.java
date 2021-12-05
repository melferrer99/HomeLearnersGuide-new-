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

import java.time.Year;

public class CreateFbList extends AppCompatActivity {

    EditText subCode,subTitle,subLink;
    Spinner YearSpinner,secSpinner,learningSpinner;
    Button retrieveFBList,addLink;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fb_list);

        subCode = findViewById(R.id.SubjectCode);
        subTitle = findViewById(R.id.SubjectTitle);
        subLink = findViewById(R.id.SubjectLink);

        retrieveFBList = findViewById(R.id.RetieveFBBtn);
        addLink = findViewById(R.id.CreateFBLink);

        YearSpinner = (Spinner) findViewById(R.id.YearFBSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(CreateFbList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YearSpinner.setAdapter(myAdapter);

        secSpinner = (Spinner) findViewById(R.id.SectionFBSpinner);

        ArrayAdapter<String> sec = new ArrayAdapter<String>(CreateFbList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.section));
        sec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secSpinner.setAdapter(sec);

        learningSpinner = (Spinner) findViewById(R.id.fbLearningSystemSpinner);

        ArrayAdapter<String> learn = new ArrayAdapter<String>(CreateFbList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.learningSystem));
        learn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        learningSpinner.setAdapter(learn);

        retrieveFBList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateFbList.this,FbClassList.class);
                startActivity(intent);

            }
        });

        addLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty((subTitle.getText().toString()))){
                    Toast.makeText(CreateFbList.this, "Please Enter Subject Title", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty((subCode.getText().toString()))){
                    Toast.makeText(CreateFbList.this, "Please Enter Subject Code", Toast.LENGTH_SHORT).show();
                }

                else if(TextUtils.isEmpty((subLink.getText().toString()))){
                    Toast.makeText(CreateFbList.this, "Please Enter Subject Link", Toast.LENGTH_SHORT).show();
                }

                else{
                    String subYear = YearSpinner. getSelectedItem().toString();
                    String subSec = secSpinner. getSelectedItem().toString();
                    final DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("Subjects").child(subYear).child(subSec);

                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String searchUser = subTitle.getText().toString();


                            if(dataSnapshot.child(searchUser).exists()){
                                Toast.makeText(CreateFbList.this,"SUBJECT ALREADY ADDED REGISTERED",Toast.LENGTH_SHORT).show();
                            }

                            else{

                                UsersDatabase = FirebaseDatabase.getInstance();
                                reference = UsersDatabase.getReference("FacebookGroups").child(learningSpinner.getSelectedItem().toString()).child(subYear).child(subSec);

                                String subjectCode = subCode.getText().toString();
                                String subjectTitle = subTitle.getText().toString();
                                String subjectLink = subLink. getText().toString();
                                String subjectYear =  YearSpinner. getSelectedItem().toString();
                                String subjectSection = secSpinner. getSelectedItem().toString();
                                String learningSystem = learningSpinner. getSelectedItem().toString();



                                fbHelper fbHelper = new fbHelper(subjectCode, subjectTitle,subjectLink,subjectYear,subjectSection,learningSystem);

                                reference.child(subjectTitle).setValue(fbHelper);

                                Toast.makeText(CreateFbList.this,"FB Group Added",Toast.LENGTH_LONG).show();

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