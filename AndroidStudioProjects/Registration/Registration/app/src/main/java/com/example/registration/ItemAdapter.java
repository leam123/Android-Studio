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

public class ItemAdapter extends ArrayAdapter<Items> {

    public ItemAdapter(Context context, ArrayList<Items> list) {

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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner,parent,false);
        }
        ImageView img = convertView.findViewById(R.id.imageView);
        TextView text = convertView.findViewById(R.id.textView);

        Items current = getItem(position);
        if(current != null){
            img.setImageResource(current.getFlagImage());
            text.setText(current.getPicName());
        }
        return convertView;
    }
}
