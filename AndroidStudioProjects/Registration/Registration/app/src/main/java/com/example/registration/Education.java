package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Education extends AppCompatActivity {

    EditText et, et1, et2, et3, et4, et5, et6, et7;
    Account account = new Account();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        account = (Account) bundle.getParcelable("Account");

        Button btn = findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et = (EditText) findViewById(R.id.tfElem5);
                et1 = (EditText) findViewById(R.id.tfElem6);
                et2 = (EditText) findViewById(R.id.tfJunior1);
                et3 = (EditText) findViewById(R.id.tfJunior2);
                et4 = (EditText) findViewById(R.id.tfSenior1);
                et5 = (EditText) findViewById(R.id.tfSenior2);
                et6 = (EditText) findViewById(R.id.tfCollege1);
                et7 = (EditText) findViewById(R.id.tfCollege2);

                account.setElem(et.getText().toString());
                account.setYear1(et1.getText().toString());
                account.setJunior(et2.getText().toString());
                account.setYear2(et3.getText().toString());
                account.setSenior(et4.getText().toString());
                account.setYear3(et5.getText().toString());
                account.setCollege(et6.getText().toString());
                account.setYear4(et7.getText().toString());

                Intent intent = new Intent(Education.this,Skills.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Account", account);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        Button btn1 = findViewById(R.id.back);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Education.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Account", account);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    //public void content(View view){

    //}
}
