package com.example.homelearnersguide;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbList extends AppCompatActivity {


    ListView listView;
    DatabaseReference databaseReference;
    List<userHelper> EntryList;
    String EDITU;

    String subSec,subYear,sic,yer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_list);

        EntryList = new ArrayList<>();


        listView = findViewById(R.id.k);
//         sic = getIntent().getStringExtra("sec");
//         yer = getIntent().getStringExtra("year");

        RetrieveList();
    }

    private void RetrieveList() {

        databaseReference = FirebaseDatabase.getInstance().getReference("teachers");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    userHelper userHelper = ds.getValue(com.example.homelearnersguide.userHelper.class);
                    EntryList.add(userHelper);

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