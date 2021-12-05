package com.example.homelearnersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity {

        Button logout;
        TextView name;

        ImageButton orient,conn,mess;

        String Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        orient = (ImageButton) findViewById(R.id.orientationbutton);
        conn = (ImageButton) findViewById(R.id.connect_button);
        mess = (ImageButton) findViewById(R.id.message_button);
        name = findViewById(R.id.nameview);
        logout = (Button) findViewById(R.id.logout_button);

        String use = getIntent().getStringExtra("Username");
        name.setText(use);


        orient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Orientation.class);
                startActivity(intent);

            }
        });

        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Connect.class);
                startActivity(intent);

            }
        });

        mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Message.class);
                startActivity(intent);
            }
        });

       logout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               logout(Home.this);
           }
       });
    }



    private void logout(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Home.this,MainActivity.class);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();

    }


}

