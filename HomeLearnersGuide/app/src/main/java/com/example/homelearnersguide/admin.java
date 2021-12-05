package com.example.homelearnersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin extends AppCompatActivity {

        Button Adduser,Connect,Orient,logoutad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Adduser = (Button) findViewById(R.id.AddUsers);
        Connect = findViewById(R.id.ConnectBtn);
        Orient = findViewById(R.id.OrientBtn);
        logoutad = (Button) findViewById(R.id.logoutadmin);

        Adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this,addselect.class);
                startActivity(intent);

            }
        });

        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this,adminConnect.class);
                startActivity(intent);

            }
        });

        Orient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(admin.this,AddPdf.class);
                startActivity(intent);

            }
        });

        logoutad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutad(admin.this);
            }
        });
    }

    private void logoutad(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(admin.this,MainActivity.class);
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