package com.example.homelearnersguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

        Button Login_button;
        EditText username, password;

        String Name;

        FirebaseDatabase UsersDatabase;
        DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login_button = (Button) findViewById(R.id.Login_button);
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);


         Name= username.getText().toString();


//        Login_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this,LearningSystem.class);
//                startActivity(intent);
//
//            }
//        });
        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(username.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please enter a username!!!", Toast.LENGTH_SHORT).show();

                }
                else{
                    final DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("users");
                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String searchUser = username.getText().toString();
                            String searchPass= password.getText().toString();


                            if(dataSnapshot.child(searchUser).exists()){
                                String passwordMo = dataSnapshot.child(searchUser).child("password").getValue().toString();
                                if(searchPass.equals(passwordMo)){

                                    if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("Admin")){
                                        openAdmin();
                                    }
                                    else if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("Student")){

                                        openStudent();

                                    }

                                    else if((dataSnapshot.child(searchUser).child("userType").getValue().toString()).equals("Teacher")){

                                        openTeacher();

                                    }


                                }
                                else {
                                    Toast.makeText(MainActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                                }

                            }

                            else{
                                Toast.makeText(MainActivity.this,"USER NOT REGISTERED",Toast.LENGTH_SHORT).show();

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });

    }

    public void openAdmin(){
        Intent intent = new Intent(this, admin.class);
        String Username= username.getText().toString();
        intent.putExtra("Username",Username);
        startActivity(intent);
    }

    public void openStudent(){
        String Username= username.getText().toString();

        Intent intent = new Intent(this, LearningSystem.class);
        intent.putExtra("Username",Username);
        startActivity(intent);
    }

    public void openTeacher(){
        String Username= username.getText().toString();

        Intent intent = new Intent(this, adminConnect.class);
        intent.putExtra("Username",Username);
        startActivity(intent);
    }
}