package com.example.homelearnersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Connect extends AppCompatActivity {

        ImageButton fb,classroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        fb = (ImageButton) findViewById(R.id.FBdisplayBtn);
        classroom = (ImageButton) findViewById(R.id.GoogleClassBtn);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Connect.this,FbClassList.class);
                startActivity(intent);

            }
        });

        classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Connect.this,GLClassList.class);
                startActivity(intent);

            }
        });

    }
}