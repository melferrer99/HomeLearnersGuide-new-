package com.example.homelearnersguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class adduser extends AppCompatActivity {

    Button student;

    ListView listView;
    DatabaseReference databaseReference;
    List<studentHelper> EntryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);

        listView = findViewById(R.id.StudentListView);
        EntryList = new ArrayList<>();

        student= findViewById(R.id.addStudentBtn);

        RetrieveList();

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adduser.this,Form.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                studentHelper studentHelper = EntryList.get(i);
                String StudentName = studentHelper.getUserName();
                String StudentEmail = studentHelper.getEmail();
                String StudentSection = studentHelper.getSection();
                String StudentLearning = studentHelper.getLearningSystem();
                String StudentYear = studentHelper.getYear();
                String StudentFullName = studentHelper.getStudentFullName();


                Intent intent = new Intent(adduser.this,StudentInfoView.class);

                intent.putExtra("Name", StudentName);
                intent.putExtra("Email", StudentEmail);
                intent.putExtra("Section", StudentSection);
                intent.putExtra("Learning", StudentLearning);
                intent.putExtra("Year", StudentYear);
                intent.putExtra("Fullname",StudentFullName);

                startActivity(intent);

            }
        });

    }

    private void RetrieveList() {
        databaseReference = FirebaseDatabase.getInstance().getReference("students");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    studentHelper studentHelper = ds.getValue(com.example.homelearnersguide.studentHelper.class);
                    EntryList.add(studentHelper);

                }

                String[] entryList = new String[EntryList.size()];

                for(int i = 0; i<entryList.length;i++){
                    entryList[i]= EntryList.get(i).getUserName();

                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,entryList){

                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        TextView textView = (TextView) view.findViewById(android.R.id.text1);
                        textView.setTextColor(Color.BLACK);

                        return view;
                    }
                };

                listView.setAdapter(arrayAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}