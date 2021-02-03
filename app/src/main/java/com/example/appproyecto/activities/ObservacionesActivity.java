package com.example.appproyecto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.appproyecto.fragments.ObservacionesFragment;
import com.example.appproyecto.R;

public class ObservacionesActivity extends AppCompatActivity {

    private Toolbar miActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observaciones);



        miActionBar = (Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ObservacionesFragment observacionesFragment = (ObservacionesFragment)
                getSupportFragmentManager().findFragmentById(R.id.observaciones_container);
        if (observacionesFragment == null) {
            observacionesFragment = ObservacionesFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.observaciones_container, observacionesFragment)
                    .commit();
        }
    }
}
