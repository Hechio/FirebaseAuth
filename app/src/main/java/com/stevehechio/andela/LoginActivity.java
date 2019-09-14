package com.stevehechio.andela;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
private EditText etEmailLogin, etPassLogin;
private Button btnLogin;
private ProgressDialog pdialogue;
private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        collectID();
        mAuth = FirebaseAuth.getInstance();
        pdialogue = new ProgressDialog(this);
        btnLogin.setOnClickListener(this);
    }

    public void collectID(){
        etEmailLogin=findViewById(R.id.etMail);
        etPassLogin=findViewById(R.id.etPass);
        btnLogin=findViewById(R.id.btnLogin);
    }

    @Override
    public void onClick(View view) {
        if (view==btnLogin){
            loginUser();
        }
    }
    public void loginUser(){
        String email = etEmailLogin.getText().toString().trim();
        String pass = etPassLogin.getText().toString().trim();
        pdialogue.setMessage("Verifying...");
        pdialogue.setCanceledOnTouchOutside(false);
        pdialogue.show();
        mAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        startActivity(new Intent(LoginActivity.this,AdminActivity.class));
                        pdialogue.dismiss();
                        finish();
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Try again",Toast.LENGTH_SHORT).show();
                        pdialogue.dismiss();
                    }

                    }
                });
    }
}
