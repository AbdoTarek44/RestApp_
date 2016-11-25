package com.example.abdotarek.restapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abdotarek.restapp.DataBase.ControllerDB;

public class MainActivity extends AppCompatActivity {
    EditText email,pass;
    Button login,signup ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.singup);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ir = new Intent(MainActivity.this ,RegisterActivity.class);
                startActivity(ir);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e =email.getText().toString();
                String p =pass.getText().toString();

                ControllerDB database = new ControllerDB(MainActivity.this);
                database.OpenDB();

                Cursor cursor = database.select(e,p,MainActivity.this);

                if (e.equals("admin") && p.equals("admin123")){
                    Intent ii = new Intent(MainActivity.this , AdminActivity.class);
                    startActivity(ii);
                }
                else if (e.equals("") || p.equals("")) {
                    Toast.makeText(MainActivity.this, "Error In Email or Password", Toast.LENGTH_SHORT).show();
                }

                else if (cursor.getCount() >0){
                    Bundle b = new Bundle();
                    b.putString("name",e);
                    b.putString("pass",p);
                    Intent i = new Intent(MainActivity.this ,FragmentActivity.class);
                    i.putExtras(b);
                    database.CloseDB();
                    startActivity(i);
                    finish();
                }

            }
        });


    }

}
