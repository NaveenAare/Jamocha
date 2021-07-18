package com.mynews.jamocha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class upload extends Activity {
    EditText bookname, author, rating;
    TextView register_tv, msg_reg_tv;
    Button upload;
    String currentUserID,saveCurrentDate,saveCurrentTime,productRandomKey;
    FirebaseAuth auth;
    private ProgressDialog loadingBar;
    String sbookname,sauthor,srating;
    private DatabaseReference ProductsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload);

        loadingBar = new ProgressDialog(this);
        bookname = findViewById(R.id.bookname);

        author = findViewById(R.id.author);
        rating = findViewById(R.id.rating);
        upload = findViewById(R.id.upload);


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sbookname = bookname.getText().toString();
                 sauthor = author.getText().toString();
                 srating =  rating.getText().toString();
                // Utils.hideKeyboard(RegisterActivity.this);

                if (TextUtils.isEmpty(sbookname) || TextUtils.isEmpty(sauthor) || TextUtils.isEmpty(srating)){
                    Toast.makeText(upload.this, "All fileds are required", Toast.LENGTH_SHORT).show();
                } else if (Double.parseDouble(srating)>5|| Double.parseDouble(srating)<0){
                    Toast.makeText(upload.this, "Rating must be at between 1 to 5 ", Toast.LENGTH_SHORT).show();
                } else {

                    loadingBar.setTitle("Add New Product");
                    loadingBar.setMessage("Dear Admin, please wait while we are adding the new product.");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();
                    auth = FirebaseAuth.getInstance();
                    FirebaseUser mFirebaseUser = auth.getCurrentUser();
                    if(mFirebaseUser != null) {
                        currentUserID = mFirebaseUser.getUid(); //Do what you need to do with the id
                    }
                    ProductsRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("books");
                    StoreProductInformation();

                }
            }
        });
    }

    private void StoreProductInformation()
    {
        loadingBar.setTitle("Add New Book");
        loadingBar.setMessage("Dear User, please wait while we are adding the new Book.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        productRandomKey = saveCurrentDate + saveCurrentTime;



        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pid", productRandomKey);
        productMap.put("date", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("pname", sbookname);
        productMap.put("state",sauthor);
        productMap.put("image", srating);

        ProductsRef.child(productRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            loadingBar.dismiss();
                            Intent intent = new Intent(upload.this,MainActivity3.class);
                            startActivity(intent);
                            Toast.makeText(upload.this, "Book  uploaded Successfully...", Toast.LENGTH_SHORT).show();


                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(upload.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });







}}