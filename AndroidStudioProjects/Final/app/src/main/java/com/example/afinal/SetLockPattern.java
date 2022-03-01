package com.example.afinal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class SetLockPattern extends AppCompatActivity {

    PatternLockView mPatternLockView;
    Button set = findViewById(R.id.set);
    Button back = findViewById(R.id.back);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwordactivity);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);
                finish();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        SharedPreferences sharedPreferences = getSharedPreferences("Pref",0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("password", PatternLockUtils.patternToString(mPatternLockView, pattern));
                        editor.apply();

                        Intent intent = new Intent(getApplicationContext(),ConfirmPattern.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onCleared() {

                    }
                });
            }
        });
    }
}
