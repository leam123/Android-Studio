package com.mylifejustmine.restful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addNew(View view) {
        new ConnectIt().execute("1",((EditText)findViewById(R.id.personname)).getText().toString(),((EditText)findViewById(R.id.address)).getText().toString(),((EditText)findViewById(R.id.salary)).getText().toString());
    }

    public void delete(View view) {
        new ConnectIt().execute("2",((EditText)findViewById(R.id.id)).getText().toString());
    }

    public void update(View view) {
        new ConnectIt().execute("3",((EditText)findViewById(R.id.personname)).getText().toString(),((EditText)findViewById(R.id.address)).getText().toString(),((EditText)findViewById(R.id.salary)).getText().toString(),((EditText)findViewById(R.id.id)).getText().toString());
    }

    public void view(View view) {
        new ConnectIt().execute("4",((EditText)findViewById(R.id.id)).getText().toString(),((EditText)findViewById(R.id.personname)).getText().toString(),((EditText)findViewById(R.id.address)).getText().toString());
    }

    public void search(View view) {
        new ConnectIt().execute("5",((EditText)findViewById(R.id.id)).getText().toString());
    }

    public class ConnectIt extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {
            final TextView textView = findViewById(R.id.list);
            HttpClient client = new DefaultHttpClient();
            try {

                URI uri = null;
                final String code = params[0];

                HttpPost post = new HttpPost();
                if(code=="1"){
                    uri = new URI("http://10.0.2.2/restexample1/addEmp.php");
                    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                    urlParameters.add(new BasicNameValuePair("name", params[1]));
                    urlParameters.add(new BasicNameValuePair("address", params[2]));
                    urlParameters.add(new BasicNameValuePair("salary", params[3]));
                    //Log.e(">>>>>>>>>>>",params[1]+":"+params[2]+":"+params[2]);
                    post.setEntity(new UrlEncodedFormEntity(urlParameters));
                }
                else if(code=="2"){
                    uri = new URI("http://10.0.2.2/restexample1/deleteEmp.php?id="+params[1]);
                }
                else  if(code=="3") {
                    uri = new URI("http://10.0.2.2/restexample1/updateEmp.php?id="+params[4]);
                    List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
                    urlParameters.add(new BasicNameValuePair("name", params[1]));
                    urlParameters.add(new BasicNameValuePair("address", params[2]));
                    urlParameters.add(new BasicNameValuePair("salary", params[3]));
                    post.setEntity(new UrlEncodedFormEntity(urlParameters));
                }
                else if(code=="4"){
                    uri = new URI("http://10.0.2.2/restexample1/viewAll.php");
                }
                else if(code=="5"){
                    uri = new URI("http://10.0.2.2/restexample1/search.php?id="+params[1]);
                }

                post.setURI(uri);
                //get.setURI(uri);

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
                                        Toast.makeText(MainActivity.this,"Successfully Added:"+id, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }catch(Exception e){
                            Log.e(">>>>>>>>>>>",e.getMessage());
                        }
                    }
                    else if(code=="2"){
                        if(msg=="1")
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,"Successfully"+msg+"Deleted!", Toast.LENGTH_SHORT).show();
                                }
                            });
                    }
                    else if(code=="3"){
                        try{
                            if(Integer.parseInt(msg) > 0){
                                final int id = Integer.parseInt(msg);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"Successfully Updated:"+id, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,msg, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }catch(Exception e){
                            Log.e(">>>>>>>>>>>",e.getMessage());
                        }
                    }
                    else if(code=="4"){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(msg);
                                textView.setEnabled(false);
                            }
                        });
                    }
                    else if(code=="5"){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(msg);
                                textView.setEnabled(false);
                            }
                        });
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
