package com.example.homelearnersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LearningSystem extends AppCompatActivity {

        Button LSbutton;
        String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_system);

        LSbutton = (Button) findViewById(R.id.LSbutton);

        String use = getIntent().getStringExtra("Username");
        name= use;

        LSbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LearningSystem.this,Home.class);

                intent.putExtra("Username",name);
                startActivity(intent);

            }
        });
    }
}