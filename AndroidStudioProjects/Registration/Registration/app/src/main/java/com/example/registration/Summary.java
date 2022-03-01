package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Summary extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Bundle bundle = getIntent().getExtras();
        Account account = (Account) bundle.getParcelable("Account");

        //ArrayList<String> mylist = getIntent().getStringArrayListExtra("skills");

        TextView text1 = findViewById(R.id.nametf);
        text1.setText(account.getName());

        TextView text2 = findViewById(R.id.agetf);
        text2.setText(account.getAge());

        TextView text3 = findViewById(R.id.birthtf);
        text3.setText(account.getBirthDate());

        TextView text4 =  findViewById(R.id.gendertf);
        text4.setText(account.getGender());

        TextView text5 = findViewById(R.id.ntf);
        text5.setText(account.getNationality());

        TextView text6 = findViewById(R.id.elem);
        text6.setText(account.getElem());
        TextView text7 = findViewById(R.id.elemYear);
        text7.setText(account.getYear1());

        TextView text8 = findViewById(R.id.junior);
        text8.setText(account.getJunior());
        TextView text9 = findViewById(R.id.juniorYear);
        text9.setText(account.getYear2());

        TextView text10 = findViewById(R.id.senior);
        text10.setText(account.getSenior());
        TextView text11 = findViewById(R.id.seniorYear);
        text11.setText(account.getYear3());

        TextView text12 = findViewById(R.id.college);
        text12.setText(account.getCollege());
        TextView text13 = findViewById(R.id.collegeYear);
        text13.setText(account.getYear4());

        //TextView text14 = findViewById(R.id.textView15);
        //text14.setText(mylist.get(0));
    }
}
