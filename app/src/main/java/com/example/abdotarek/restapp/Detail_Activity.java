package com.example.abdotarek.restapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail_Activity extends AppCompatActivity {

    ImageView imageView;
    TextView name ,address,phone ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);

        imageView = (ImageView) findViewById(R.id.dimage);
        name = (TextView) findViewById(R.id.dname);
        address = (TextView) findViewById(R.id.dadress);
        phone = (TextView) findViewById(R.id.dphone);

        Intent intent=getIntent();
        Bundle b = intent.getExtras();
        String dname = b.getString("dname");
        String daddress = b.getString("daddress");
        String dphone = b.getString("dphone");
        Bitmap dphoto = b.getParcelable("photo");

        imageView.setImageBitmap(dphoto);
        name.setText(dname);
        address.setText(daddress);
        phone.setText(dphone);

    }
}
