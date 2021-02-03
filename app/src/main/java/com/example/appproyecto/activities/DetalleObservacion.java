package com.example.appproyecto.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appproyecto.Fenomeno;
import com.example.appproyecto.Localidad;
import com.example.appproyecto.Observacion;
import com.example.appproyecto.R;
import com.example.appproyecto.Usuario;
import com.example.appproyecto.api.APIutils;
import com.example.appproyecto.api.ObservacionAPI;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleObservacion extends AppCompatActivity {

    private static final String TAG = "DetalleObservacion" ;
    private ObservacionAPI mAPIService;


    String fecha;
    String Latitud;
    String Longitud;
    String Altitud;
    String descripcion;
    String fenomeno;
    String criticidad;
    String localidad;
    String idUsuario;



    TextView fechaValor;
    TextView LatitudValor;
    TextView LongitudValor;
    TextView AltitudValor;
    TextView descripcionValor;
    TextView fenomenoValor;
    TextView localidadValor;
    TextView criticidadValor;
    TextView idUsuarioValor;
    Button btnEditar;
    Button btnGuardar;
    private Toolbar miActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_observacion);
        miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        btnEditar = (Button) findViewById(R.id.BotonEditar);

        Intent intent = getIntent();
        idUsuario = intent.getStringExtra("IDUSUARIO");
        descripcion = intent.getStringExtra("DESCRIPCION");
        fecha = intent.getStringExtra("FECHA");
        Latitud = intent.getStringExtra("LATITUD");
        Longitud = intent.getStringExtra("LONGITUD");
        Altitud = intent.getStringExtra("ALTITUD");
        criticidad = intent.getStringExtra("CRITICIDAD");
        fenomeno = intent.getStringExtra("FENOMENO");
        localidad = intent.getStringExtra("LOCALIDAD");



        fechaValor = (TextView) findViewById(R.id.lblFechaValor);
        fechaValor.setText(fecha);

        LatitudValor = (TextView) findViewById(R.id.lblGeoLatitudValor);
        LatitudValor.setText(Latitud);

        LongitudValor = (TextView) findViewById(R.id.lblGeoLongitudValor);
        LongitudValor.setText(Longitud);

        AltitudValor = (TextView) findViewById(R.id.lblGeoAltitudValor);
        AltitudValor.setText(Altitud);


        descripcionValor = (TextView) findViewById(R.id.lblDescripcionValor);
        descripcionValor.setText(descripcion);


        fenomenoValor = (TextView) findViewById(R.id.lblFenomenoValor);
        fenomenoValor.setText(fenomeno);

        localidadValor = (TextView) findViewById(R.id.lblLocalidadValor);
        localidadValor.setText(localidad);

        criticidadValor = (TextView) findViewById(R.id.lblCriticidadValor);
        criticidadValor.setText(criticidad);

        idUsuarioValor = (TextView) findViewById(R.id.lblIdusuarioValor);
        idUsuarioValor.setText(idUsuario);



        btnGuardar = (Button) findViewById(R.id.BotonVolver);

        mAPIService = APIutils.getObservacionAPI();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String descripcion = descripcionValor.getText().toString().trim();

                String fecha = fechaValor.getText().toString().trim();

                String latitud = LatitudValor.getText().toString().trim();

                String longitud = LongitudValor.getText().toString().trim();

                String altitud = AltitudValor.getText().toString().trim();

                String nivelCritico = criticidadValor.getText().toString().trim();

                String fenomeno = fenomenoValor.getText().toString();
                Long fenomenol = Long.parseLong(fenomeno);

                String localidad = localidadValor.getText().toString().trim();
                Long localidadl = Long.parseLong(localidad);

                String usuario = idUsuarioValor.getText().toString();
                Long usuariol = Long.parseLong(usuario);

                Observacion observacion = new Observacion(descripcion,fecha, latitud, longitud,altitud, nivelCritico, new Fenomeno(fenomenol),
                        new Usuario(usuariol), new Localidad(localidadl));

                sendPost(observacion);

                Toast.makeText(getApplicationContext(), "Observacion agregada con éxito", Toast.LENGTH_LONG).show();
                }

        });

    }

    public void sendPost( Observacion observacion) {
        mAPIService.add(observacion).enqueue(new Callback<Observacion>() {

            @Override
            public void onResponse(Call<Observacion> call, Response<Observacion> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "Exito" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Observacion> call, Throwable t) {
                Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }


    public void editar(View view) {
        finish();
    }

    public void LLamar(View v) {
        Intent intent = new Intent(this, Telefono.class);
        startActivity(intent);
    }

    public void volver(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    }
