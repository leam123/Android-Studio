package com.example.prefinals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   //ArrayList<String> first;
    //ArrayList<String> last;
    //  ArrayList<String> bdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.tvName);
        TextView text2 = findViewById(R.id.tvName2);

        /*ListView view = findViewById(R.id.listView);
        ListAdapter list = new ListAdapter(this,  first,  last,  bdate,this);
        view.setAdapter(list);*/

        SharedPreferences sharedPreferences = getSharedPreferences("Preferences",MODE_PRIVATE);
        String value = sharedPreferences.getString("First","");
        String value2 = sharedPreferences.getString("Last","");
        text.setText(value);
        text2.setText(value2);

        Button btn = (Button) findViewById(R.id.addNew);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MainActivity.this, ActivityTwoo.class);
                startActivity(i);
            }
        });

        Button edit1 = findViewById(R.id.edit1);
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ActivityTwoo.class);
                startActivity(intent);
            }
        });
    }
}
