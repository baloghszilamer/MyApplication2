package com.example.windows10.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
//import android.content.Context;
import com.example.windows10.myapplication.R;


import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


   // private EditText editTextMobile;
    //private EditText firstName;
    //private EditText lastName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button button1 = findViewById(R.id.loginbutton);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });



        final Button button = findViewById(R.id.registerbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
//    @Override
//   protected void onStart(){
//        super.onStart();
//        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
//            Intent intent = new Intent(MainActivity.this  ,AdvertismentActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//   }

}
