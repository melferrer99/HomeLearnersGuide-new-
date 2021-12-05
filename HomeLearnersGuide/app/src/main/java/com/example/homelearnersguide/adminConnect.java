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

public class adminConnect extends AppCompatActivity {

    ImageButton fbBtn,googleBtn;
    Button logoutconnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_connect);

        fbBtn = findViewById(R.id.fb_button);
        googleBtn = findViewById(R.id.class_button);
        logoutconnect = (Button) findViewById(R.id.logoutconnect);

        fbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminConnect.this,CreateFbList.class);
                startActivity(intent);
            }
        });

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(adminConnect.this,CreateGoogleLink.class);
                startActivity(intent);
            }
        });

        logoutconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutcon(adminConnect.this);
            }
        });
    }

    private void logoutcon(final Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to log out?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(adminConnect.this,MainActivity.class);
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