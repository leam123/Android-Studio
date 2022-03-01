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

public class EditInfo extends AppCompatActivity {

    EditText firstName, lastName, email, userName;
    RadioButton male, female;
    Button edit, back;
    UserDetails acc;
    String gender, prev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        acc = (UserDetails) bundle.getSerializable("Account");

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        userName = (EditText) findViewById(R.id.userName);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        edit = (Button) findViewById(R.id.set);
        back = (Button) findViewById(R.id.back);

        prev = acc.getUserName();
        firstName.setText(acc.getFirstName());
        lastName.setText(acc.getLastName());
        email.setText(acc.getEmail());
        userName.setText(acc.getUserName());

        if(acc.getGender().equalsIgnoreCase("Male")){
            male.setChecked(true);
        }else if(acc.getGender().equalsIgnoreCase("Female")){
            female.setChecked(true);
        }




        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(male.isChecked()){
                    gender = male.getText().toString();
                }
                else if (female.isChecked()){
                    gender = female.getText().toString();
                }

                new ConnectIt().execute("1", acc.getFirstName(),acc.getLastName(),acc.getEmail(),acc.getUserName(),gender,prev);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                    URI uri = null;
                    HttpPost post = new HttpPost();

                    uri = new URI("http://10.0.2.2/mobile/updateAccount.php");
                    List<NameValuePair> urlParameters = new ArrayList<>();
                    urlParameters.add(new BasicNameValuePair("firstName", params[1]));
                    urlParameters.add(new BasicNameValuePair("lastName", params[2]));
                    urlParameters.add(new BasicNameValuePair("email", params[3]));
                    urlParameters.add(new BasicNameValuePair("userName", params[4]));
                    urlParameters.add(new BasicNameValuePair("gender", params[5]));
                    urlParameters.add(new BasicNameValuePair("prev", params[6]));
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
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Toast.makeText(EditInfo.this, "Successfully Added:" + id, Toast.LENGTH_SHORT).show();
                                        acc.setUserName(userName.getText().toString());
                                        acc.setFirstName(firstName.getText().toString());
                                        acc.setLastName(lastName.getText().toString());
                                        acc.setEmail(email.getText().toString());
                                        acc.setGender(gender);
                                        Intent intent = new Intent(EditInfo.this, MainPage.class);
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
                                        Toast.makeText(EditInfo.this, msg, Toast.LENGTH_SHORT).show();
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
