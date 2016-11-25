package com.example.abdotarek.restapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.abdotarek.restapp.DataBase.ControllerDB;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Abdo Tarek on 11/4/2016.
 */

public class HomeFragment extends Fragment {



    ListView list ;
    AdapterRest adapterRest ;

    ArrayList<String> nName;
    ArrayList<String> nAdress;
    ArrayList<String> nPhone;
    ArrayList<Bitmap> xx;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vv =  inflater.inflate(R.layout.fragment_home,container,false);

        list = (ListView) vv.findViewById(R.id.list);
        nName    = new ArrayList<String>();
        nAdress  = new ArrayList<String>();
        nPhone = new ArrayList<String>();
        xx = new ArrayList<Bitmap>();

        SharedPreferences preferences = getActivity().getSharedPreferences("abdo",getActivity().MODE_PRIVATE);

        ControllerDB database = new ControllerDB(getActivity());
        database.OpenDB();

        if(preferences.getString("f","1")=="1"){
            putData();
            SharedPreferences preferences1=getActivity().getSharedPreferences("abdo", getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor=preferences1.edit();
            editor.putString("f","2");
            editor.commit();
        }


        Cursor cursor =database.GetDataRest();


        for (int j = 0; j < cursor.getCount(); j++) {

            String n = cursor.getString(1);
            byte[] photo = cursor.getBlob(2);
            String a = cursor.getString(3);
            String p = cursor.getString(4);

            nName.add(n);
            xx.add(GetImage(photo));
            nAdress.add(a);
            nPhone.add(p);
            cursor.moveToNext();

        }

        adapterRest = new AdapterRest(getActivity(), xx, nName);
        list.setAdapter(adapterRest);
        database.CloseDB();



        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle bundle = new Bundle();
                String m = nName.get(i);
                String d = nAdress.get(i);
                String n = nPhone.get(i);
                Bitmap b = xx.get(i);

                bundle.putString("dname",m);
                bundle.putString("daddress",d);
                bundle.putString("dphone",n);
                bundle.putParcelable("photo",b);

                Intent in = new Intent(getActivity(),Detail_Activity.class);
                in.putExtras(bundle);
                startActivity(in);


            }
        });

        return  vv;

        }

    private void putData() {
        ControllerDB database = new ControllerDB(getActivity());
        database.OpenDB();

        Bitmap bit = BitmapFactory.decodeResource(getResources(),R.drawable.ghh);
        Bitmap bit1 = BitmapFactory.decodeResource(getResources(),R.drawable.ghh5);


        byte [] b = GetByte(bit);
        byte [] b1 = GetByte(bit1);


        database.InsertREST("KFC" ,b, "El bahr Street","01025232695");
        database.InsertREST("CookDoor",b1, "Tanta Club","01152365498");
        database.InsertREST("Pizaa Hut",b,"Saeed Street","01265998566");
        database.InsertREST("Katchab",b1,"El hlew Street","01022548523");
        database.InsertREST("EL Taybat" ,b, "El bahr Street","01022222222");
        database.InsertREST("Watnya",b1, "Tanta Club","01133333333");
        database.InsertREST("Mac",b,"Hassan Street","01244444555");
        database.InsertREST("Spectra",b1,"El Nahas Street","01022556677");
        database.InsertREST("Fresky" ,b, "Saeed Street","01033888995");
        database.InsertREST("Om Mohamed",b1, "Kafr Essam ","01144778589");
        database.InsertREST("Ebn Elbald",b,"Sager","01266558865");
        database.InsertREST("Abo Dshesh",b1,"El Nady Street","01088668866");
        database.InsertREST("El Ga3an",b,"El hlew Street","01033665533");

        database.CloseDB();

    }


    public static byte [] GetByte (Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream);

        return stream.toByteArray();
    }

    public  static Bitmap GetImage (byte [] image){
        return BitmapFactory.decodeByteArray(image,0,image.length);
    }

    }
