package com.mynews.jamocha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Activity {
    EditText email, password;
    Button btn_login;
    Typeface MR,MRR;
    ProgressDialog dialog;

    ProgressBar prg1;
    FirebaseAuth auth;
    TextView forgot_password, login_tv, msg_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);




        prg1=(ProgressBar)findViewById(R.id.log_pr);
        prg1.setVisibility(View.GONE);

        auth = FirebaseAuth.getInstance();

        login_tv = findViewById(R.id.login_tv);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
        forgot_password = findViewById(R.id.forgot_password);
        msg_tv = findViewById(R.id.msg_tv);



        forgot_password.setVisibility(View.GONE);
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();

              //  Utils.hideKeyboard(LoginActivity.this);

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Login.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    prg1.setVisibility(View.VISIBLE);
                   // dialog = Utils.showLoader(LoginActivity.this);
                    auth.signInWithEmailAndPassword(txt_email, txt_password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        prg1.setVisibility(View.GONE);
                                        Intent intent = new Intent(Login.this, MainActivity3.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        if(dialog!=null){
                                            dialog.dismiss();
                                        }
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        if(dialog!=null){
                                            dialog.dismiss();
                                        }
                                        prg1.setVisibility(View.GONE);
                                        Toast.makeText(Login.this, "Authentication failed!", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }
        });
    }
}
