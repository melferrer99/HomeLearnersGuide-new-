package com.example.homelearnersguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GLClassList extends AppCompatActivity {

    Button ViewList;
    Spinner YearSpinner,secSpinner,learningSpinner;

    ListView listView;
    DatabaseReference databaseReference;
    List<googleHelper> EntryList;

    String subSec,subYear,sic,yer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_l_class_list);

        listView = findViewById(R.id.GLGroupListView);
        ViewList = findViewById(R.id.GLBtn);
        EntryList = new ArrayList<>();

        YearSpinner = (Spinner) findViewById(R.id.GLYearSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(GLClassList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YearSpinner.setAdapter(myAdapter);

        secSpinner = (Spinner) findViewById(R.id.GLSectionSpinner);

        ArrayAdapter<String> sec = new ArrayAdapter<String>(GLClassList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.section));
        sec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secSpinner.setAdapter(sec);

        learningSpinner = (Spinner) findViewById(R.id.ClassroomLearning);

        ArrayAdapter<String> learn = new ArrayAdapter<String>(GLClassList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.learningSystem));
        learn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        learningSpinner.setAdapter(learn);


        ViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subYear = YearSpinner. getSelectedItem().toString();
                subSec = secSpinner. getSelectedItem().toString();
                yer = subYear;
                sic= subSec;


                RetrieveList();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int intention, long id) {
                googleHelper googleHelper = EntryList.get(intention);

                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(googleHelper.getSubjectLink()));
                startActivity(intent);
            }
        });


    }

    private void RetrieveList() {


        databaseReference = FirebaseDatabase.getInstance().getReference("GoogleClassroom").child(learningSpinner.getSelectedItem().toString()).child(subYear).child(subSec);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    googleHelper googleHelper = ds.getValue(com.example.homelearnersguide.googleHelper.class);
                    EntryList.add(googleHelper);

                }

                String[] entryList = new String[EntryList.size()];

                for(int i = 0; i<entryList.length;i++){
                    entryList[i]= EntryList.get(i).getSubjectTitle();

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