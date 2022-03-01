package com.example.userinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ConfirmLock extends AppCompatActivity {

    Switch[] pattern = new Switch[16];
    Button confirm, back, next;
    UserDetails acc;
    char[] x;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_lock);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        acc = (UserDetails) bundle.getSerializable("Account");
        final String str = getIntent().getStringExtra("pattern");

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
        confirm = findViewById(R.id.set);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);

        confirm.setOnClickListener(new View.OnClickListener() {
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
                String ptn = new String(x);
                if(str.equals(ptn)){
                    acc.setPassword(ptn);
                    flag = true;
                    Toast.makeText(ConfirmLock.this, "Password Confirmed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ConfirmLock.this,"Password do not match...try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmLock.this, SetLock.class);
                startActivity(intent);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConnectIt().execute("1", acc.getFirstName(),acc.getLastName(),acc.getEmail(),acc.getUserName(),acc.getGender(),acc.getPassword());
                //Intent intent = new Intent(ConfirmLock.this,MainActivity.class);
                //startActivity(intent);
            }
        });
    }
    public class ConnectIt extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {

            final String code = params[0];
            HttpClient client = new DefaultHttpClient();
            if (code == "1") {
                try {
                    params[1] = acc.getFirstName();
                    params[2] = acc.getLastName();
                    params[3] = acc.getEmail();
                    params[4] = acc.getUserName();
                    params[5] = acc.getGender();
                    params[6] = acc.getPassword();

                    URI uri = null;
                    HttpPost post = new HttpPost();

                    uri = new URI("http://10.0.2.2/mobile/addAccount.php");
                    List<NameValuePair> urlParameters = new ArrayList<>();
                    urlParameters.add(new BasicNameValuePair("firstName", params[1]));
                    urlParameters.add(new BasicNameValuePair("lastName", params[2]));
                    urlParameters.add(new BasicNameValuePair("email", params[3]));
                    urlParameters.add(new BasicNameValuePair("userName", params[4]));
                    urlParameters.add(new BasicNameValuePair("gender", params[5]));
                    urlParameters.add(new BasicNameValuePair("password", params[6]));
                    post.setEntity(new UrlEncodedFormEntity(urlParameters));

                    post.setURI(uri);
                    HttpResponse response = client.execute(post);
                    BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    String ln;

                    while ((ln = bf.readLine()) != null) {
                        final String msg = ln, p = params[0];

                        //if (code == "1") {
                            try {
                                if (Integer.parseInt(msg) > 0) {
                                    final int id = Integer.parseInt(msg);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(ConfirmLock.this, "Successfully Added:" + id, Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(ConfirmLock.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(ConfirmLock.this, msg, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } catch (Exception e) {
                                Log.e(">>>>>>>>>>>", e.getMessage());
                            }
                        //}
                    }

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
