package com.example.sith2.dummy1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    private EditText mpass, memail, mdname;
    private Button mup, min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        mAuth = FirebaseAuth.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("users");

        mdname = (EditText) findViewById(R.id.dname);
        memail = (EditText) findViewById(R.id.uemail);
        mpass = (EditText) findViewById(R.id.upass);

        mup = (Button) findViewById(R.id.btnr);
        min = (Button) findViewById(R.id.btnin);

        mup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });
    }

    private void register() {

        final String DName = mdname.getText().toString().trim();
        final String Email = memail.getText().toString().trim();
        String Password = mpass.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    String uid = mAuth.getCurrentUser().getUid();
                    mRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

                    mRef.child("Dname").setValue(DName);
                    mRef.child("Email").setValue(Email);

                    Intent b = new Intent(FirstActivity.this, SecondActivity.class);
                    startActivity(b);
                    finish();

                }
            }
        });
    }

    private void signin() {

        final String DName = mdname.getText().toString().trim();
        final String Email = memail.getText().toString().trim();
        String Password = mpass.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Intent a = new Intent(FirstActivity.this, SecondActivity.class);
                    startActivity(a);
                    finish();

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            success(mAuth.getCurrentUser());
        }
    }

    private void success(FirebaseUser currentUser) {

        Intent c = new Intent(FirstActivity.this, SecondActivity.class);
        startActivity(c);
        finish();
    }
}