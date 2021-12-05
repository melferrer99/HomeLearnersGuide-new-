package com.example.homelearnersguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class formteacher extends AppCompatActivity {

    EditText UserName,Pass, EmailIn;
    Button Register;

    FirebaseDatabase UsersDatabase;
    DatabaseReference reference,list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formteacher);

        UserName = findViewById(R.id.userNameInput);
        Pass = findViewById(R.id.passwordInput);
        EmailIn = findViewById(R.id.emailInput);

        Register = findViewById(R.id.submitBtn);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty((UserName.getText().toString()))){
                    Toast.makeText(formteacher.this, "Please Enter a Username", Toast.LENGTH_SHORT).show();
                }

                else{
                    final DatabaseReference mref = FirebaseDatabase.getInstance().getReference().child("users");

                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String searchUser = UserName.getText().toString();


                            if(dataSnapshot.child(searchUser).exists()){
                                Toast.makeText(formteacher.this,"USER IS ALREADY REGISTERED",Toast.LENGTH_SHORT).show();
                            }

                            else{

                                UsersDatabase = FirebaseDatabase.getInstance();
                                reference = UsersDatabase.getReference("users");
                                list = UsersDatabase.getReference("teachers");

                                String userName = UserName.getText().toString();
                                String email = EmailIn.getText().toString();
                                String password = Pass.getText().toString();
                                String userType = "Teacher";

                                userHelper userHelper = new userHelper(email, password, userName,userType);

                                reference.child(userName).setValue(userHelper);
                                list.child(userName).setValue(userHelper);

                                Toast.makeText(formteacher.this,"USER REGISTERED",Toast.LENGTH_LONG).show();



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
}