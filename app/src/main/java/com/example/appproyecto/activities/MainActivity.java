package com.example.appproyecto.activities;

import android.Manifest;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.appproyecto.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar miActionBar;
    private Button botonBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

       botonBorrar= (Button) findViewById(R.id.button3);
    }

    public void LLamar(View v){
        Intent intent = new Intent(this, Telefono.class);
        startActivity(intent);
    }

    public void Borrar(View v){
        Intent intent = new Intent(this, BorrarObservacion.class);
        startActivity(intent);
    }

    public void Modificar(View v){
        Intent intent = new Intent(this, ModificarObservacion.class);
        startActivity(intent);
    }

    public void AgregarObservacion(View v){
        Intent intent = new Intent(this, AgregarObservacion.class);
        startActivity(intent);
    }

    public void VerLista(View v){
        Intent intent = new Intent(this, ObservacionesActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){

            case R.id.mAcercaDe:
                Intent intent = new Intent (this, AcercaDe.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}