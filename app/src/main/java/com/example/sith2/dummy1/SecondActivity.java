package com.example.sith2.dummy1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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
        loadusers();
    }

    private FirebaseRecyclerAdapter<ModelUser, ListAdapter> madapter;

    private void loadusers(){
        DatabaseReference users = FirebaseDatabase.getInstance().getReference();
        Query userQuery = users.child("users");

        madapter = new FirebaseRecyclerAdapter<ModelUser, ListAdapter>(ModelUser.class,R.layout.list_users, ListAdapter.class, userQuery) {
            @Override
            protected void populateViewHolder(ListAdapter viewHolder, final ModelUser modelUser, int position) {
                viewHolder.userlist.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //Toast.makeText(SecondActivity.this, "send message", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                        i.putExtra(ThirdActivity.MAIN, modelUser);
                        startActivity(i);

                    }
                });

                viewHolder.bind(modelUser, new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                    }
                });
            }
        };
        mrecycle.setAdapter(madapter);
    }
}
