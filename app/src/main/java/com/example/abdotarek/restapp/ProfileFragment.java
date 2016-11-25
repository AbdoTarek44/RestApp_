package com.example.abdotarek.restapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdotarek.restapp.DataBase.ControllerDB;

/**
 * Created by Abdo Tarek on 11/3/2016.
 */

public class ProfileFragment extends Fragment {

    TextView pname ,pemail ,page ,pphone ,pgender;

    ControllerDB database ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_profile,container,false);


        pname = (TextView) v.findViewById(R.id.pname);
        pemail = (TextView) v.findViewById(R.id.pemail);
        page = (TextView) v.findViewById(R.id.page);
        pphone = (TextView) v.findViewById(R.id.pphone);
        pgender = (TextView) v.findViewById(R.id.pgender);

        ControllerDB database = new ControllerDB(getActivity());
        database.OpenDB();
        Bundle b = getArguments();
        String nn =b.getString("name");
        String pp =b.getString("pass");

        Cursor cursor = database.select(nn ,pp ,getActivity());

        pname.setText(cursor.getString(1));
        pemail.setText(nn);
        page.setText((int) cursor.getDouble(4)+" ");
        pphone.setText((int)  cursor.getDouble(5)+" ");
        pgender.setText(cursor.getString(6));


        return  v;

    }


}
