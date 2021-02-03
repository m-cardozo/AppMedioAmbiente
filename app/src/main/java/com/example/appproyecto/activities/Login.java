package com.example.appproyecto.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appproyecto.R;
import com.example.appproyecto.activities.BorrarObservacion;
import com.example.appproyecto.activities.MainActivity;
import com.example.appproyecto.api.APIClient;
import com.example.appproyecto.api.APIutils;
import com.example.appproyecto.api.ObservacionAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private ObservacionAPI mAPIService;
    private EditText UsuarioEditText;
    private EditText PasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UsuarioEditText = (EditText) findViewById(R.id.editTextTextPersonName);
        PasswordEditText = (EditText) findViewById(R.id.editTextTextPassword);
         mAPIService = APIutils.getObservacionAPI();
        Button btnGuardar = (Button) findViewById(R.id.login);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

    }

    public void Ingresar(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void login(final View view) {
        final String usuario = UsuarioEditText.getText().toString().trim();
        String password = PasswordEditText.getText().toString().trim();

        mAPIService.login(usuario, password).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        try {
                            if (response.body().equals("true")) {
                                Ingresar(view);
                                Toast.makeText(getApplicationContext(),"Bienvenid@ " + usuario , Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Usuario o Password incorrectos", Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Ingresar Usuario y Password", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
    }
