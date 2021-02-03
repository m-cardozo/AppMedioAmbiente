package com.example.appproyecto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.appproyecto.R;

public class AcercaDe extends AppCompatActivity {

    private Toolbar miActionBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        miActionBar2 = (Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}