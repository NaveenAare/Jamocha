package com.mynews.jamocha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {


    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    ArrayList<String> myhh2;
    private Context mcontext;
 Activity mActivity;
    private String currwentid;
    private String myfinal;
    ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, ArrayList<ArrayList<String>> listOfLists,String currentid, Activity mActivity) {
        this.mInflater = LayoutInflater.from(context);
        this.listOfLists = listOfLists;
        this.currwentid = currentid;
        this.mActivity = mActivity;
        this.mcontext=context;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.servicescard2, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        try{
            final ArrayList<ArrayList<String> > animal = listOfLists;


            holder.rating.setText(animal.get(position).get(1));
            holder.author.setText(animal.get(position).get(3));
            holder.name.setText(animal.get(position).get(2));


            holder.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                                    try{
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(currwentid).child("books").child(animal.get(position).get(0)).removeValue()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Intent intent = new Intent(mcontext,MainActivity3.class);
                                                            mActivity.overridePendingTransition( 0, 0);
                                                            mcontext.startActivity(intent);
                                                            mActivity.overridePendingTransition( 0, 0);
                                                            Toast.makeText(mcontext, "Deleted successfully", Toast.LENGTH_SHORT).show();
                                                        } else {


                                                        }
                                                    }
                                                });
                                    }catch (Exception e){
                                        Toast.makeText(mcontext,"Error Occured please try again later", Toast.LENGTH_SHORT).show();

                                    }


                }
            });




        }catch (Exception e){

        }



    }

    // total number of rows
    @Override
    public int getItemCount() {
        return listOfLists.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      //  TextView myTextView1,myTextView2,myTextView3,myTextView4, myTextView5,myTextView6;
        RecyclerView recyclerView3;
        TextView author,name,rating,remove;
        ImageView imageView2,imageView3,caa,dir;


        ViewHolder(View itemView) {
            super(itemView);

           // myTextView1=(TextView)itemView.findViewById(R.id.insideid) ;
            author=(TextView) itemView.findViewById(R.id.vauthor);
            name =(TextView)itemView.findViewById(R.id.vbookname);
            rating=(TextView) itemView.findViewById(R.id.vrating);
            remove=(TextView) itemView.findViewById(R.id.removebutton);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}