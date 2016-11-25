package com.example.abdotarek.restapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;



public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        FragmentManager mange = getSupportFragmentManager();
        FragmentTransaction tranc = mange.beginTransaction();
        tranc.replace(R.id.Fragmentcontainer,new HomeFragment() ,"rte");
        tranc.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.homee) {
            FragmentManager mange = getSupportFragmentManager();
            FragmentTransaction tranc = mange.beginTransaction();
            tranc.replace(R.id.Fragmentcontainer,new HomeFragment(), "rte");
            tranc.commit();
        }
        else if (id == R.id.profile){
            Intent intent=getIntent();
            Bundle b = intent.getExtras();
            ProfileFragment pf = new ProfileFragment();
            pf.setArguments(b);

            FragmentManager mange = getSupportFragmentManager();
            FragmentTransaction tranc = mange.beginTransaction();
            tranc.replace(R.id.Fragmentcontainer,pf,"rte");
            tranc.commit();
        }
        else if (id == R.id.out){
            Intent Logout = new Intent(FragmentActivity.this ,MainActivity.class);
            startActivity(Logout);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
