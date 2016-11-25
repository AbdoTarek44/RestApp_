package com.example.abdotarek.restapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by Abdo Tarek on 11/11/2016.
 */

public class AdapterRest extends BaseAdapter {

    ArrayList<Bitmap> photo;
    ArrayList<String> name;
    Context c;

    public AdapterRest (Context cc ,ArrayList<Bitmap> ph , ArrayList<String> na){
        this.c=cc;
        this.photo=ph;
        this.name=na;
    }

    @Override
    public int getCount() {
        return name.size() ;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View A = inflater.inflate(R.layout.home_list,viewGroup,false);

        ImageView imageView = (ImageView) A.findViewById(R.id.restimage);
        TextView textView = (TextView) A.findViewById(R.id.restname);

        imageView.setImageBitmap(photo.get(i));


        textView.setText(name.get(i));

        return A;
    }
}
