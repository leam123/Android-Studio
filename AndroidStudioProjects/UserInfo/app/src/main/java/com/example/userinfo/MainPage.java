package com.example.userinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainPage extends AppCompatActivity {

    TextView name, email, username, gender;
    Button editInfo, editPattern, signOut;
    UserDetails acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        acc = (UserDetails) bundle.getSerializable("Account");


        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        username = (TextView) findViewById(R.id.username);
        gender = (TextView) findViewById(R.id.gender);
        editInfo = (Button) findViewById(R.id.edit1);
        editPattern = (Button) findViewById(R.id.edit2);
        signOut = (Button) findViewById(R.id.edit3);

        name.setText(acc.getFirstName() + " " + acc.getLastName());
        email.setText(acc.getEmail());
        username.setText(acc.getUserName());
        gender.setText(acc.getGender());

        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, EditInfo.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Account", acc);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        editPattern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, EditPattern.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Account", acc);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainPage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
