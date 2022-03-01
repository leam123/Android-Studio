package com.example.prefinals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ActivityTwoo extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText editText2, editText3;
    SharedPreferences sharedPreferences;
    Spinner spinner, spinner2, spinner3;
    String[] months = {"01","02","03","04","05","06","07","08","09","10","11","12"};
    String[] years = {"1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013",
            "2014","2015","2016","2017","2018","2019","2020"};
    String[] febDay = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28"};

    String[] days = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twoo);

        spinner = (Spinner) findViewById(R.id.spinner3);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner2 = (Spinner) findViewById(R.id.spinner4);
        spinner2.setOnItemSelectedListener(this);

        ArrayAdapter adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,days);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        spinner3 = (Spinner) findViewById(R.id.spinner5);
        spinner3.setOnItemSelectedListener(this);

        ArrayAdapter adapter3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,years);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Button btn = (Button) findViewById(R.id.add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String first = editText2.getText().toString();
                String last = editText3.getText().toString();

                editor.putString("First", first);
                editor.putString("Last", last);
                editor.commit();
                Intent intent = new Intent(ActivityTwoo.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //To-do
    }
}
