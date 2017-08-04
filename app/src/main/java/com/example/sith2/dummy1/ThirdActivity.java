package com.example.sith2.dummy1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ThirdActivity extends AppCompatActivity {

    public static String MAIN = "users";

    private DatabaseReference mRef;
    private FirebaseDatabase mfd;
    private FirebaseAuth mAuth;

   private RecyclerView mrecycle;

    private TextView mdid;
    private ModelUser modelUser;
    private Button msend;
    private EditText mmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Bundle bundle = getIntent().getExtras();
        modelUser = bundle.getParcelable(MAIN);
        mAuth = FirebaseAuth.getInstance();

        mdid = (TextView)findViewById(R.id.did);
        mdid.setText(modelUser.getDname());

        msend = (Button)findViewById(R.id.send);
        msend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });

        mmsg = (EditText)findViewById(R.id.msgid);

        mrecycle = (RecyclerView) findViewById(R.id.recycle1);
        mrecycle.setLayoutManager(new LinearLayoutManager(this));

        loadmessage();
    }

    private void send() {
        final String senderuid = mAuth.getCurrentUser().getUid();
        final String receiveruid = modelUser.getUid().toString();
        final String uname = modelUser.dname.toString();

        String mesg = mmsg.getText().toString();

        mRef = FirebaseDatabase.getInstance().getReference().child("message").child(senderuid).child(receiveruid).push();
        mRef.child("sender").setValue(senderuid);
        mRef.child("receiver").setValue(receiveruid);
        mRef.child("message").setValue(mesg);
        mRef.child("dname").setValue(uname);

        mRef = FirebaseDatabase.getInstance().getReference().child("message").child(receiveruid).child(senderuid).push();
        mRef.child("receiver").setValue(receiveruid);
        mRef.child("sender").setValue(senderuid);
        mRef.child("message").setValue(mesg);
        mRef.child("dname").setValue(uname);

        mmsg.setText("");
    }

    private FirebaseRecyclerAdapter<SRMessages, MessageVH> madapter;

    private void loadmessage(){

        final String sender = mAuth.getCurrentUser().getUid();
        final String receiver = modelUser.getUid().toString();

        final DatabaseReference message = FirebaseDatabase.getInstance().getReference();
        Query query = message.child("message").child(sender).child(receiver);

        madapter = new FirebaseRecyclerAdapter<SRMessages, MessageVH>(SRMessages.class, R.layout.messager, MessageVH.class, query) {

            private final int out = 1;
            private final int in = 2;

            @Override
            protected void populateViewHolder(final MessageVH viewHolder, final SRMessages messages, final int position) {
              //  if (currentuid())
                viewHolder.bind(messages, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }
        };
        mrecycle.setAdapter(madapter);


    }
}
