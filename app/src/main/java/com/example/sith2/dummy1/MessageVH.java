package com.example.sith2.dummy1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Sith2 on 8/2/2017.
 */

public class MessageVH extends RecyclerView.ViewHolder {
    TextView message;


    public MessageVH(View itemView) {
        super(itemView);
        message = (TextView) itemView.findViewById(R.id.msgr);
    }

    public void bind (SRMessages messages, View.OnClickListener clickListener) {
        message.setText(messages.getMessage());
    }
}
