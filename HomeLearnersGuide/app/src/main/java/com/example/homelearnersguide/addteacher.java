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

public class addteacher extends AppCompatActivity {

    Button button1;

    ListView listView;
    DatabaseReference databaseReference;
    List<userHelper> EntryList;
    String EDITU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addteacher);

        listView = findViewById(R.id.TeacherListView);
        EntryList = new ArrayList<>();
        button1 = (Button) findViewById(R.id.addTeacherBtn);

        RetrieveList();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addteacher.this,formteacher.class);
                startActivity(intent);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                userHelper userHelper = EntryList.get(i);
                String TeacherName = userHelper.getUserName();
                String TeacherEmail = userHelper.getEmail();
                String TeacherType = userHelper.getUserType();


                Intent intent = new Intent(addteacher.this,TeacherInfoView.class);

                intent.putExtra("Name", TeacherName);
                intent.putExtra("Email", TeacherEmail);
                intent.putExtra("Type", TeacherType);

                startActivity(intent);

            }
        });
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