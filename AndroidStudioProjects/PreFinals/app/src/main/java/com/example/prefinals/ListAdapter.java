package com.example.prefinals;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> first;
    ArrayList<String> last;
    ArrayList<String> bdate;
    private LayoutInflater inflater;
    AppCompatActivity app;

    public ListAdapter(Context context, ArrayList<String> first, ArrayList<String> last, ArrayList<String> bdate, AppCompatActivity app) {
        this.context = context;
        this.first = first;
        this.last = last;
        this.bdate = bdate;
        this.app = app;
    }

    @Override
    public int getCount() {
        return first.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.custom,null);
        TextView text =  convertView.findViewById(R.id.tvName);
        TextView text2 = convertView.findViewById(R.id.tvName2);
        TextView text3 = convertView.findViewById(R.id.tvName3);

        Button btn = (Button) convertView.findViewById(R.id.edit1);
        Button btn1 = (Button) convertView.findViewById(R.id.edit2);
        Button btn2 = (Button) convertView.findViewById(R.id.edit3);

        text.setText(first.get(position));
        text2.setText(last.get(position));
        text3.setText(bdate.get(position));

        return convertView;
    }
}
