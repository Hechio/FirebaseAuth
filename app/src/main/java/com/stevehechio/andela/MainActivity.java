package com.stevehechio.andela;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnsignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIds();
        btnsignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

   public void  findIds(){
        btnsignUp=findViewById(R.id.btnSignUp);
        btnLogin=findViewById(R.id.btnLogin);
   }

    @Override
    public void onClick(View view) {
        if (view==btnsignUp){
            startActivity(new Intent(MainActivity.this,SingUpActivity.class));
        }
        if (view==btnLogin){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }
}
