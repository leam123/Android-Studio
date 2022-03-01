package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText et, et1, et2, et4;
    Account account = new Account();

    RadioButton b1;
    RadioGroup g1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.next);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et = (EditText) findViewById(R.id.tfName);
                et1 = (EditText) findViewById(R.id.tfAge2);
                et2 = (EditText) findViewById(R.id.tfBirth);
                g1 = (RadioGroup) findViewById(R.id.group);
                et4 = (EditText) findViewById(R.id.tfNationality);

                int choice = g1.getCheckedRadioButtonId();
                b1 = (RadioButton) findViewById(choice);

                account.setName(et.getText().toString());
                account.setAge(et1.getText().toString());
                account.setBirthDate(et2.getText().toString());
                account.setGender(b1.getText().toString());
                account.setNationality(et4.getText().toString());

                Intent intent = new Intent(MainActivity.this,Education.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Account", account);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }
}
