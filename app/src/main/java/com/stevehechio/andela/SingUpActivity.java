package com.stevehechio.andela;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SingUpActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etEmail,etPassword;
    private Button btnSign;
    private FirebaseAuth mAuth;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        collectIds();
        pDialog=new ProgressDialog(this);
        btnSign.setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();


    }
    public  void collectIds(){
        etEmail = findViewById(R.id.etMailSign);
        etPassword= findViewById(R.id.etPassSign);
        btnSign = findViewById(R.id.btnSign);
    }

    @Override
    public void onClick(View view) {
        if (view ==btnSign){
         String email = etEmail.getText().toString().trim();
         String password = etPassword.getText().toString().trim();
         pDialog.setMessage("Please wait...");
         pDialog.setCanceledOnTouchOutside(false);
         pDialog.show();
         mAuth.createUserWithEmailAndPassword(email,password)
                 .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                      if (task.isSuccessful()) {
                          Toast.makeText(SingUpActivity.this,"Registered successful", Toast.LENGTH_SHORT).show();
                          pDialog.dismiss();
                      }
                      else {
                          Toast.makeText(SingUpActivity.this,"Try Again", Toast.LENGTH_SHORT).show();
                          pDialog.dismiss();
                      }
                     }
                 });
        }
    }
}
