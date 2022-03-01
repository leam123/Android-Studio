package com.example.userinfo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
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
import org.apache.http.client.methods.HttpGet;
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
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    Switch[] pattern = new Switch[16];
    Button register, login;
    EditText username;
    char[] x;
    UserDetails acc;
    String user, ptn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        username = findViewById(R.id.userName);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        acc = new UserDetails();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                acc.setUserName(user);
                x = new char[16];
                for(int i=0; i<16; i++){
                    if(pattern[i].isChecked()){
                        x[i] = '1';
                    }else {
                        x[i] = '0';
                    }
                }
                ptn = new String(x);
                acc.setPassword(ptn);
                if(!user.equals("")){
                    new ConnectIt().execute("1", acc.getUserName(), acc.getPassword());
                }else{
                    Toast.makeText(MainActivity.this,"Does not exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });
    }

   public class ConnectIt extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {
            final String code = params[0];
            HttpClient client = new DefaultHttpClient();
            if (code=="1") {
                try {
                    URI uri = null;
                    HttpGet get = new HttpGet();
                    HttpResponse response;
                    BufferedReader bf = null;

                    uri = new URI("http://10.0.2.2/mobile/search.php?username="+ params[1] + "&password=" + params[2]);
                    //uri = new URI("http://10.0.2.2/mobile/search.php?username=" + params[1]);
                    get.setURI(uri);
                    response = client.execute(get);
                    bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    String ln;

                    while ((ln = bf.readLine()) != null) {
                        final String msg = ln;

                        try {
                            if (Integer.parseInt(msg) > 0) {
                                //uri = new URI("http://10.0.2.2/mobile/searchAccount.php?username=" + params[1]);
                                uri = new URI("http://10.0.2.2/mobile/searchAccount.php?username="+ params[1] + "&password=" + params[2]);
                                get.setURI(uri);
                                response = client.execute(get);

                                bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                                String message = "";
                                while ((ln = bf.readLine()) != null) {
                                    message = ln;
                                }
                                final String str = message;

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        StringTokenizer st = new StringTokenizer(str, ":");
                                        List<String> list = new ArrayList<>();
                                        while (st.hasMoreTokens()) {
                                            list.add(st.nextToken());
                                        }
                                        acc.setFirstName(list.get(0));
                                        acc.setLastName(list.get(1));
                                        acc.setEmail(list.get(2));
                                        acc.setUserName(list.get(3));
                                        acc.setGender(list.get(4));
                                        //acc.setPassword(list.get(5));

                                        username.setText("");

                                        Intent intent = new Intent(MainActivity.this, MainPage.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putSerializable("Account", acc);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Account not found", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (Exception e) {
                            Log.e(">>>>>>>>>>>", e.getMessage());
                        }
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

