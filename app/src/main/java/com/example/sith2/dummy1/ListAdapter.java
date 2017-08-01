package com.example.sith2.dummy1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Sith2 on 8/1/2017.
 */

public class ListAdapter extends RecyclerView.ViewHolder {

    TextView userlist;
    private Model model;


    public ListAdapter(View itemView) {
        super(itemView);

        userlist = (TextView) itemView.findViewById(R.id.list);
    }

    public void bind(Model model, View.OnClickListener clickListener){
        userlist.setText(model.getDname());
    }
}
