package com.example.sith2.dummy1;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView mrecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mrecycle = (RecyclerView)findViewById(R.id.recycle);
        mrecycle.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.logout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();

                Intent d = new Intent(SecondActivity.this,FirstActivity.class);
                startActivity(d);
            }
        });
    }
}
