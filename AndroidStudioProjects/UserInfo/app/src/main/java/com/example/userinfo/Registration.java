package com.example.userinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import java.util.Set;
import java.util.StringTokenizer;

public class Registration extends AppCompatActivity {
    Button register;
    EditText firstName, lastName, email, userName;
    RadioButton male;
    RadioButton female;
    String first, last, emailAdd, user, gender;
    boolean flag;
    UserDetails acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        register = (Button) findViewById(R.id.register);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        userName = (EditText) findViewById(R.id.userName);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                first = firstName.getText().toString();
                last = lastName.getText().toString();
                emailAdd = email.getText().toString();
                user = userName.getText().toString();
                if(male.isChecked())
                    gender = male.getText().toString();
                else if (female.isChecked())
                    gender = female.getText().toString();

                flag = !first.equals("") && !last.equals("") && !emailAdd.equals("") && !user.equals("") && gender!= null;

                if(flag){
                    acc = new UserDetails(first,last,emailAdd,user,gender);
                    new ConnectIt().execute("1", acc.getUserName(), acc.getEmail());
                }else {
                    Toast.makeText(Registration.this, "Please fill up all fields.", Toast.LENGTH_SHORT).show();
                }
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

                    uri = new URI("http://10.0.2.2/mobile/searchUser.php?username="+ params[1]);
                    get.setURI(uri);
                    response = client.execute(get);
                    bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                    String ln;

                    while ((ln = bf.readLine()) != null) {
                        final String msg = ln;

                        try {
                            if (Integer.parseInt(msg) == 0) {
                                uri = new URI("http://10.0.2.2/mobile/searchEmail.php?email="+ params[2]);
                                get.setURI(uri);
                                response = client.execute(get);

                                bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                                String line;
                                while ((line = bf.readLine()) != null) {
                                    final String string = line;
                                    if(Integer.parseInt(string)==0){
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent intent = new Intent(Registration.this, SetLock.class);
                                                Bundle bundle = new Bundle();
                                                bundle.putSerializable("Account", acc);
                                                intent.putExtras(bundle);
                                                startActivity(intent);
                                            }
                                        });
                                    }else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(Registration.this, "Email Address Already Exist", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                }
                            }else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(Registration.this, "Username Already Exist", Toast.LENGTH_SHORT).show();
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