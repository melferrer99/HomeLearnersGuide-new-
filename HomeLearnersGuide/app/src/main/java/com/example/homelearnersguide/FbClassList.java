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

public class FbClassList extends AppCompatActivity {

    Button ViewList;
    Spinner YearSpinner,secSpinner,learningSpinner;

    ListView listView;
    DatabaseReference databaseReference;
    List<fbHelper> EntryList;

    String subSec,subYear,sic,yer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_class_list);

        listView = findViewById(R.id.FbGroupListView);
        ViewList = findViewById(R.id.FbBtn);
        EntryList = new ArrayList<>();

        YearSpinner = (Spinner) findViewById(R.id.FbYearSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(FbClassList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.year));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        YearSpinner.setAdapter(myAdapter);

        secSpinner = (Spinner) findViewById(R.id.FbSectionSpinner);

        ArrayAdapter<String> sec = new ArrayAdapter<String>(FbClassList.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.section));
        sec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        secSpinner.setAdapter(sec);

        learningSpinner = (Spinner) findViewById(R.id.FbLearningSpinner);

        ArrayAdapter<String> learn = new ArrayAdapter<String>(FbClassList.this,
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
                fbHelper fbHelper = EntryList.get(intention);

                Intent intent = new Intent();
                intent.setType(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(fbHelper.getSubjectLink()));
                startActivity(intent);
            }
        });


    }

    private void RetrieveList() {


        databaseReference = FirebaseDatabase.getInstance().getReference("FacebookGroups").child(learningSpinner.getSelectedItem().toString()).child(subYear).child(subSec);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    fbHelper fbHelper = ds.getValue(com.example.homelearnersguide.fbHelper.class);
                    EntryList.add(fbHelper);

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