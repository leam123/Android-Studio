package com.example.afinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class Login extends AppCompatActivity {

    PatternLockView mPatternLockView;
    String password;
    Button login = findViewById(R.id.login);
    Button register = findViewById(R.id.register);
    EditText user = findViewById(R.id.userName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordinput);

        SharedPreferences sharedPreferences = getSharedPreferences("Pref", 0);
        password = sharedPreferences.getString("password","0");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainPage.class);
                intent.putExtra("Username", user.getText().toString());
                startActivity(intent);

                mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
                mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
                    @Override
                    public void onStarted() {

                    }

                    @Override
                    public void onProgress(List<PatternLockView.Dot> progressPattern) {

                    }

                    @Override
                    public void onComplete(List<PatternLockView.Dot> pattern) {
                        if(password.equals(PatternLockUtils.patternToString(mPatternLockView, pattern))) {
                            Intent intent = new Intent(getApplicationContext(), ProgramActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Login.this,"Incorrect Password",Toast.LENGTH_SHORT).show();
                            mPatternLockView.clearPattern();
                        }
                    }

                    @Override
                    public void onCleared() {

                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);
            }
        });

    }
}