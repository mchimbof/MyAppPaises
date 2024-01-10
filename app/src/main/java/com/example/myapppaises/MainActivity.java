package com.example.myapppaises;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity
        extends AppCompatActivity
        implements WebServices.Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datoPais = new HashMap<String, String>();
        WebService ws= new WebService("http://www.geognos.com/api/en/countries/info/all.json",
                datoPais, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<lugarPaises> listaLugarPais = new ArrayList<>();

        //Parceo JSON
        JSONObject lista = new JSONObject(result);
        JSONObject JSONlista = lista.getJSONObject("Results");

        listaLugarPais = lugarPaises.JsonObjectsBuild(JSONlista);
        adaptadorPaises adaptadopais = new adaptadorPaises(this, listaLugarPais);

        //Paso3
        ListView lslPaises = (ListView) findViewById(R.id.lstpaises);
        lslPaises.setAdapter(adaptadopais);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
        Toast.makeText(getApplicationContext(),"Error...",Toast.LENGTH_SHORT).show();
    }
}