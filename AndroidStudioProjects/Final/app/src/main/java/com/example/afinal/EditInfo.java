package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

    RadioGroup g1 = (RadioGroup) findViewById(R.id.gender);
    Button back = findViewById(R.id.back);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainPage.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void update(View view) {
        int choice = g1.getCheckedRadioButtonId();
        new ConnectIt().execute("1",((EditText)findViewById(R.id.firstName)).getText().toString(),((EditText)findViewById(R.id.lastName)).getText().toString(),((EditText)findViewById(R.id.email)).getText().toString(),((EditText)findViewById(R.id.userName)).getText().toString(),((RadioButton)findViewById(choice)).getText().toString(),((EditText)findViewById(R.id.userName)).getText().toString());
    }

    public class ConnectIt extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {
            HttpClient client = new DefaultHttpClient();
            try {

                URI uri = null;
                final String code = params[0];

                HttpPost post = new HttpPost();
                if(code=="1"){
                    uri = new URI("http://10.0.2.2/mobile/update.php?username="+params[6]);
                    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                    urlParameters.add(new BasicNameValuePair("firstName", params[1]));
                    urlParameters.add(new BasicNameValuePair("lastName", params[2]));
                    urlParameters.add(new BasicNameValuePair("email", params[3]));
                    urlParameters.add(new BasicNameValuePair("userName", params[4]));
                    urlParameters.add(new BasicNameValuePair("gender", params[5]));
                    post.setEntity(new UrlEncodedFormEntity(urlParameters));
                }
                else{

                }

                post.setURI(uri);
                HttpResponse response = client.execute(post);
                BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String ln;

                while ((ln = bf.readLine()) != null) {
                    final String msg = ln, p = params[0];

                    if(code=="1"){
                        try{
                            if(Integer.parseInt(msg) > 0){
                                final int id = Integer.parseInt(msg);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(EditInfo.this,"Successfully Updated Account: "+id, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(EditInfo.this,msg, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }catch(Exception e){
                            Log.e(">>>>>>>>>>>",e.getMessage());
                        }
                    }else{

                    }
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
