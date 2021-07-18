package com.mynews.jamocha;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.utilities.ImmutableTree;
import com.mynews.jamocha.Interface.ItemClickListener;


public class mmmm extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView topbookaslot,author,rating,remove,timestamp1,timestamp2,selecteddd2,houserfor123,selecteddd, belowdistance2, belowrating2,datee,toprating,belowaddress,topdistance,topcentername,belowbookaslot,belowrating,belowdistance,belowcentername,middeltext,sidetext;
    public ImageView imageView,cancel,crossred,comp_crs,i1,top2,top3,callhim,finaldirections,nouploads,top1,below1,below2,below3,getdirectionss;
    public ItemClickListener listner;
    public CardView cardView;
    public TextView crdad;
    public mmmm(@NonNull View itemView) {
        super(itemView);



        topbookaslot =(TextView)itemView.findViewById(R.id.vbookname);
        author=(TextView)itemView.findViewById(R.id.vauthor);
        rating=(TextView)itemView.findViewById(R.id.vrating);
        remove=(TextView)itemView.findViewById(R.id.removebutton);


    }
    public void setItemClickListner(ItemClickListener listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View v) {
        listner.onClick(v, getAdapterPosition(), false);

    }
}
