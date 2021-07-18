package com.mynews.jamocha;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mynews.jamocha.databinding.ActivityMain3Binding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    String currentUserID,saveCurrentDate,saveCurrentTime,productRandomKey;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMain3Binding binding;
    private RecyclerView recyclerView,r2;
    FirebaseAuth auth;
    private MyRecyclerViewAdapter MyRecyclerViewAdapter;
    FirebaseRecyclerAdapter<tech, mmmm> adapter1;
    private DatabaseReference ProductsRef;
    ArrayList<String> myhh2;
    private Button sortt;
    private ProgressBar pg;
    ArrayList<ArrayList<String>> listOfLists = new ArrayList<ArrayList<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        pg=(ProgressBar)findViewById(R.id.prg);
        recyclerView = findViewById(R.id.myrecycular);
        r2=(RecyclerView)findViewById(R.id.myrecycular1) ;
        recyclerView.setHasFixedSize(true);
        pg.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = auth.getCurrentUser();
        if(mFirebaseUser != null) {
            currentUserID = mFirebaseUser.getUid(); //Do what you need to do with the id
        }
        Activity thisActivity=(Activity)this;
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("books");
        FirebaseRecyclerOptions<tech> options1 =
                new FirebaseRecyclerOptions.Builder<tech>()
                        .setQuery(ProductsRef, tech.class)
                        .build();

      adapter1 =
              new FirebaseRecyclerAdapter<tech, mmmm>(options1) {
                    @Override
                    protected void onBindViewHolder(@NonNull mmmm holder1, int position, @NonNull final tech model) {

                        holder1.topbookaslot.setText(model.getPname());
                        holder1.author.setText(model.getState());
                        holder1.rating.setText(model.getImage());
                        pg.setVisibility(View.GONE);

                        holder1.remove.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                try{
                                                    FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("books").child(model.getPid()).removeValue()
                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                      /*  Intent intent = new Intent(mcontext,MainActivity.class);
                                                                        mActivity.overridePendingTransition( 0, 0);
                                                                        mcontext.startActivity(intent);
                                                                        mActivity.overridePendingTransition( 0, 0);*/
                                                                        Toast.makeText(MainActivity3.this, "Deleted successfully", Toast.LENGTH_SHORT).show();
                                                                    } else {


                                                                    }
                                                                }
                                                            });
                                                }catch (Exception e){
                                                    Toast.makeText(MainActivity3.this,"Error Occured please try again later", Toast.LENGTH_SHORT).show();

                                                }


                                                break;

                                            case DialogInterface.BUTTON_NEGATIVE:
                                                //No button clicked
                                                break;
                                        }
                                    }
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                                builder.setTitle("Continue to Delete").setMessage("Do you Want To Delete this book For Sure?").setPositiveButton("Yes", dialogClickListener)
                                        .setNegativeButton("No", dialogClickListener).show();
                            }
                        });

                        ArrayList myhh = new ArrayList<>();
                        myhh2 = new ArrayList<>();
                        myhh2=myhh;
                        myhh2.add(0,model.getPid());
                        myhh2.add(1,model.getImage());
                        myhh2.add(2,model.getPname());
                        myhh2.add(3,model.getState());

                       listOfLists.add(myhh2);
                        //Toast.makeText(MainActivity3.this, String.valueOf(listOfLists), Toast.LENGTH_SHORT).show();
                       //
                    }
                    @NonNull
                    @Override
                    public mmmm onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.servicescard2, parent, false);
                        mmmm holder1 = new mmmm(view);
                        return holder1;
                    }
                };

        adapter1.startListening();
        recyclerView.setAdapter(adapter1);




     //   Toast.makeText(MainActivity3.this, String.valueOf(listOfLists), Toast.LENGTH_SHORT).show();


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
       //


        sortt=(Button)findViewById(R.id.buttonsort);
        sortt.setActivated(true);
        sortt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortt.setActivated(false);
                listOfLists = sorting();
                MyRecyclerViewAdapter = new MyRecyclerViewAdapter(MainActivity3.this, listOfLists,currentUserID,thisActivity);
                recyclerView.setAdapter(MyRecyclerViewAdapter);
            }
        });











        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity3.this,upload.class);
               startActivity(intent);
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case  R.id.logout:
                FirebaseAuth.getInstance().signOut();
                // change this code beacuse your app will crash
                startActivity(new Intent(MainActivity3.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;
        }

        return false;
    }
    public ArrayList<ArrayList<String >> sorting(){
        double n, temp;
        ArrayList<String> mine;
        n=listOfLists.size();
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                if (Double.parseDouble(listOfLists.get(i).get(1)) < Double.parseDouble(listOfLists.get(j).get(1)))
                {
                    mine = new ArrayList<>();

                    mine =listOfLists.get(i);
                    listOfLists.set(i,listOfLists.get(j));
                    listOfLists.set(j, mine);
                }
            }
        }
        return listOfLists;


    }
    public void Oncli(MenuItem item) {

        Intent intent =new Intent(MainActivity3.this,upload.class);
        startActivity(intent);
    }

}