package com.example.appproyecto.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;

import com.example.appproyecto.R;
import com.example.appproyecto.api.APIClient;
import com.example.appproyecto.api.ObservacionAPI;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BorrarObservacion extends AppCompatActivity {


    private EditText editTextId;
    private Button buttonDelete;
    String identificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_borrar_observacion);
    Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       initView();
    }

   private void initView() {
        editTextId = findViewById(R.id.editTextId);

        buttonDelete = findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonDelete_onClick(view);
            }
        });
    }

    private void buttonDelete_onClick(View view) {

        identificador = ((EditText) findViewById(R.id.editTextId)).getText().toString();
        if (identificador.trim().compareTo("") == 0) {
            Toast.makeText(this, "Debe ingresar un id de observación", Toast.LENGTH_SHORT).show();
        } else {

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Confirmación");
        builder.setMessage("Se eliminará esta observación ");
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            final int id = Integer.parseInt(editTextId.getText().toString());

               ObservacionAPI observacionAPI = APIClient.getClient().create(ObservacionAPI.class);

                observacionAPI.delete(id).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        try {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),"Se eliminó la observación " + id, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "La observación " + id + " no existe" , Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }}
}