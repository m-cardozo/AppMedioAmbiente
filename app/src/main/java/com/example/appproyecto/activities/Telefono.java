package com.example.appproyecto.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.appproyecto.R;

public class Telefono extends AppCompatActivity {

    private Toolbar miActionBar2;
    private TextView telefono1;
    private TextView telefono2;
    private TextView telefono3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefono);

        miActionBar2 = (Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void llamarNumero1 (View v) {
        telefono1 = (TextView) findViewById(R.id.tel);
        String telefono = telefono1.getText().toString();
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse ("tel:"+ telefono)));

    }

    public void llamarNumero2 (View v) {
        telefono2 = (TextView) findViewById(R.id.tel2);
        String telefono = telefono2.getText().toString();
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse ("tel:"+ telefono)));

    }

    public void llamarNumero3 (View v) {
        telefono3 = (TextView) findViewById(R.id.tel3);
        String telefono = telefono3.getText().toString();
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse ("tel:"+ telefono)));

    }
}