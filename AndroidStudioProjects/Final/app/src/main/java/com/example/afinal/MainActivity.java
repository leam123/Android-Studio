package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences("Pref",0);
                String password = sharedPreferences.getString("password","0");
                if(password.equals("0")){
                    Intent intent = new Intent(getApplicationContext(),SetLockPattern.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        },2000);
    }
}
