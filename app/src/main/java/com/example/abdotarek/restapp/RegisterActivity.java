package com.example.abdotarek.restapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.abdotarek.restapp.DataBase.ControllerDB;

public class RegisterActivity extends AppCompatActivity {

    EditText name ,email ,password ,age ,phone;
    Button submit;
    RadioGroup radioGroup;
    RadioButton radiobutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email_reg);
        password = (EditText) findViewById(R.id.Pass_reg);
        age = (EditText) findViewById(R.id.age);
        phone = (EditText) findViewById(R.id.phone);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        submit = (Button) findViewById(R.id.submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                radiobutton = (RadioButton) findViewById(selectedId);

                String x = "male";
                if (selectedId == R.id.mradio){x="male";} else { x="female";}

               if (name.getText().toString().equals("") || email.getText().toString().equals("") ||
                       password.getText().toString().equals("") || age.getText().toString().equals("") ||
                       phone.getText().toString().equals("") ){
                   Toast.makeText(RegisterActivity.this, "Complete Your Data", Toast.LENGTH_SHORT).show();
               }
                else {
                   ControllerDB database = new ControllerDB(RegisterActivity.this);
                   database.OpenDB();

                   database.InsertDB(name.getText().toString(),email.getText().toString(),password.getText().toString(),
                           Double.parseDouble(age.getText().toString()),Double.parseDouble(phone.getText().toString()),x);
                   database.CloseDB();
                    finish();
               }
            }
        });
    }


}
