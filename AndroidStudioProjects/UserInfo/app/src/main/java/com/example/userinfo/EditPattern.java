package com.example.userinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class EditPattern extends AppCompatActivity {

    Switch[] pattern = new Switch[16];
    Button set, back, next;
    char[] x;
    String ptn;
    boolean flag;
    UserDetails acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pattern);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        acc = (UserDetails) bundle.getSerializable("Account");

        pattern[0] = findViewById(R.id.switch1);
        pattern[1] = findViewById(R.id.switch2);
        pattern[2] = findViewById(R.id.switch3);
        pattern[3] = findViewById(R.id.switch4);
        pattern[4] = findViewById(R.id.switch5);
        pattern[5] = findViewById(R.id.switch6);
        pattern[6] = findViewById(R.id.switch7);
        pattern[7] = findViewById(R.id.switch8);
        pattern[8] = findViewById(R.id.switch9);
        pattern[9] = findViewById(R.id.switch10);
        pattern[10] = findViewById(R.id.switch11);
        pattern[11] = findViewById(R.id.switch12);
        pattern[12] = findViewById(R.id.switch13);
        pattern[13] = findViewById(R.id.switch14);
        pattern[14] = findViewById(R.id.switch15);
        pattern[15] = findViewById(R.id.switch16);
        set = findViewById(R.id.set);
        back = findViewById(R.id.register);
        next = findViewById(R.id.next);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = new char[16];
                for(int i=0; i<16; i++){
                    if(pattern[i].isChecked()){
                        x[i] = '1';
                    }else {
                        x[i] = '0';
                    }
                }
                ptn = new String(x);
                flag = true;
                Toast.makeText(EditPattern.this, "Password Set", Toast.LENGTH_SHORT).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditPattern.this, MainPage.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Account",acc);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag){
                    Intent intent = new Intent(EditPattern.this, ConfirmEditedPattern.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Account", acc);
                    intent.putExtra("pattern",ptn);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(EditPattern.this,"Please set a pattern",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
