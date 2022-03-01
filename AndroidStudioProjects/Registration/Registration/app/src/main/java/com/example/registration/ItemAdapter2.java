package com.example.registration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.registration.R;

import java.util.ArrayList;

public class ItemAdapter2 extends ArrayAdapter<Item> {
    public ItemAdapter2(Context context, ArrayList<Item> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initializeView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initializeView(position, convertView, parent);
    }

    public View initializeView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner2,parent,false);
        }
        ImageView img = convertView.findViewById(R.id.imageView1);
        TextView text = convertView.findViewById(R.id.textView1);

        Item current = getItem(position);
        if(current != null){
            img.setImageResource(current.getFlagImage());
            text.setText(current.getPicName());
        }
        return convertView;
    }
}
