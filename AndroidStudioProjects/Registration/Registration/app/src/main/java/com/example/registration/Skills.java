package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Skills extends AppCompatActivity {

    private ArrayList<Items> list;
    private ItemAdapter adapter;

    private ArrayList<Item> anotherList;
    private ItemAdapter2 adapter2;

    Account account = new Account();

    Spinner spinner;
    Spinner spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        account = (Account) bundle.getParcelable("Account");

        assignList();
        assignList1();

        spinner = findViewById(R.id.spinner);
        adapter = new ItemAdapter(this, list);

        spinner2 = findViewById(R.id.spinner2);
        adapter2 = new ItemAdapter2(this, anotherList);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Items itemClicked = (Items) parent.getItemAtPosition(position);
                String itemName = itemClicked.getPicName();

                Toast.makeText(Skills.this, itemName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Item itemClicked2 = (Item) parent.getItemAtPosition(position);
                String itemName2 = itemClicked2.getPicName();

                Toast.makeText(Skills.this, itemName2 + " selected", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Button btn = findViewById(R.id.next);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Skills.this,Summary.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Account", account);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button btn2 = findViewById(R.id.back);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Skills.this,Education.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Account", account);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        Button btnAdd = findViewById(R.id.button3);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    //1st spinner (Programming skills)
    public void assignList(){
        list = new ArrayList<>();
        list.add(new Items("None",0));
        list.add(new Items("MS Office", R.drawable.msoffice));
        list.add(new Items("Java", R.drawable.java));
        list.add(new Items("PHP", R.drawable.php));
        list.add(new Items("Python", R.drawable.python));
        list.add(new Items("MySQL", R.drawable.mysql));
        list.add(new Items("Javascript", R.drawable.javascript));
        list.add(new Items("HTML", R.drawable.html));
        list.add(new Items("CSS", R.drawable.css));
        list.add(new Items("C#", R.drawable.csharp));
        list.add(new Items("C++", R.drawable.cplusplus));
        list.add(new Items("C", R.drawable.c));
    }

    //2nd Spinner (Basic skilla)
    public void assignList1(){
        anotherList = new ArrayList<>();
        anotherList.add(new Item("None", 0));
        anotherList.add(new Item("Cooking", R.drawable.cooking));
        anotherList.add(new Item("Baking", R.drawable.baking));
        anotherList.add(new Item("Dancing", R.drawable.dancing));
        anotherList.add(new Item("Singing", R.drawable.singing));
        anotherList.add(new Item("Painting", R.drawable.painting));
        anotherList.add(new Item("Drawing", R.drawable.drawing));
        anotherList.add(new Item("Crafts", R.drawable.crafts));
        anotherList.add(new Item("Poetry", R.drawable.poetry));
        anotherList.add(new Item("Card Game", R.drawable.card));
        anotherList.add(new Item("Data Analysis", R.drawable.analysis));
        anotherList.add(new Item("Research", R.drawable.research));
        anotherList.add(new Item("Communication", R.drawable.communication));
        anotherList.add(new Item("Problem Solving", R.drawable.solving));
        anotherList.add(new Item("Advising", R.drawable.advising));
    }
}
