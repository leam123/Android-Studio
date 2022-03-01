package com.example.afinal;

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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MainPage extends AppCompatActivity {

    TextView user = findViewById(R.id.username);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void search(View view) {
        user.setText(getIntent().getStringExtra("Username"));
        new ConnectIt().execute(((TextView)findViewById(R.id.username)).getText().toString());
    }

    public class ConnectIt extends AsyncTask<String,Void,Void> {

        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);
        TextView user = findViewById(R.id.username);
        TextView gender = findViewById(R.id.gender);
        @Override
        protected Void doInBackground(String... params) {

            HttpClient client = new DefaultHttpClient();
            try {

                URI uri = null;
                final String code = params[0];

                HttpPost post = new HttpPost();
                uri = new URI("http://10.0.2.2/mobile/search.php?username="+params[1]);

                post.setURI(uri);
                HttpResponse response = client.execute(post);

                BufferedReader bf = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String ln;

                while ((ln = bf.readLine()) != null) {
                    final String msg = ln, p = params[0];
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            name.setText(msg);
                        }
                    });
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
