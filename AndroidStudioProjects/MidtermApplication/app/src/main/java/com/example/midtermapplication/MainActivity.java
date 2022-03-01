package com.example.midtermapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Button btn = (Button) findViewById(R.id.buttonflames);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText name1 = (EditText) findViewById(R.id.editText2);
                    EditText name2 = (EditText) findViewById(R.id.editText5);
                    TextView result = (TextView) findViewById(R.id.textView3);

                    String first = name1.getText().toString();
                    String second = name2.getText().toString();
                    int len1 = first.length();
                    int len2 = second.length();
                    //int limit = Math.min(len1,len2);

                    int count = 0;

                    char[] ch1 = first.toCharArray();
                    char[] ch2 = second.toCharArray();

                    //compare the two strings
                    for(int i=1; i<=first.length(); i++){
                        for(int j=1; j<=second.length(); j++){
                                if(ch1[i] == ch2[j]){
                                        len1--;
                                        len2--;
                                        count = len1 + len2;
                                }else{
                                    count = len1 + len2;
                                }
                        }
                    }

                    int[] f = {1,7,13,19,25,31,37,43};
                    int[] l = {2,8,14,20,26,32,38,44};
                    int[] a = {3,9,15,21,27,33,39,45};
                    int[] m = {4,10,16,22,28,34,40,46};
                    int[] e= {5,11,17,23,29,35,41,47};
                    int[] s = {6,12,18,24,30,36,42,48};

                    for(int i=1;i<=8;i++){
                        if(count == f[i]){
                            result.setText("Friends");
                        }else if(count == l[i]){
                            result.setText("Lovers");
                        }else if(count == a[i]){
                            result.setText("Admire");
                        }else if(count == m[i]){
                            result.setText("Married");
                        }else if(count == e[i]){
                            result.setText("Enemies");
                        }else if(count == s[i]){
                            result.setText("Siblings");
                        }
                    }

                }
            });
        }
    }
