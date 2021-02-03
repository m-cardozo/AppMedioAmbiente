package com.example.appproyecto.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appproyecto.Fenomeno;
import com.example.appproyecto.Localidad;
import com.example.appproyecto.Observacion;
import com.example.appproyecto.R;
import com.example.appproyecto.Usuario;
import com.example.appproyecto.activities.MainActivity;
import com.example.appproyecto.activities.Telefono;
import com.example.appproyecto.api.APIutils;
import com.example.appproyecto.api.ObservacionAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ObservacionModificada extends AppCompatActivity {


    private static final String TAG = "Modificar";
    private ObservacionAPI mAPIService;

    String idObservacion;
    String fecha;
    String Latitud;
    String Longitud;
    String Altitud;
    String descripcion;
    String fenomeno;
    String criticidad;
    String localidad;
    String idUsuario;


    TextView idObservacionValor;
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
    private Toolbar miActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observacion_modificada);

        miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        btnEditar = (Button) findViewById(R.id.BotonEditar);

        Intent intent = getIntent();
        idUsuario = intent.getStringExtra("IDUSUARIO");
        idObservacion = intent.getStringExtra("ID_OBSERVACION");
        descripcion = intent.getStringExtra("DESCRIPCION");
        fecha = intent.getStringExtra("FECHA");
        Latitud = intent.getStringExtra("LATITUD");
        Longitud = intent.getStringExtra("LONGITUD");
        Altitud = intent.getStringExtra("ALTITUD");
        criticidad = intent.getStringExtra("CRITICIDAD");
        fenomeno = intent.getStringExtra("FENOMENO");
        localidad = intent.getStringExtra("LOCALIDAD");


        idObservacionValor = (TextView) findViewById(R.id.lblIdObservacionValor);
        idObservacionValor.setText(idObservacion);

        fechaValor = (TextView) findViewById(R.id.lblFechaValor2);
        fechaValor.setText(fecha);

        LatitudValor = (TextView) findViewById(R.id.lblGeoLatitudValor2);
        LatitudValor.setText(Latitud);

        LongitudValor = (TextView) findViewById(R.id.lblGeoLongitudValor2);
        LongitudValor.setText(Longitud);

        AltitudValor = (TextView) findViewById(R.id.lblGeoAltitudValor2);
        AltitudValor.setText(Altitud);


        descripcionValor = (TextView) findViewById(R.id.lblDescripcionValor2);
        descripcionValor.setText(descripcion);


        fenomenoValor = (TextView) findViewById(R.id.lblFenomenoValor2);
        fenomenoValor.setText(fenomeno);

        localidadValor = (TextView) findViewById(R.id.lblLocalidadValor2);
        localidadValor.setText(localidad);

        criticidadValor = (TextView) findViewById(R.id.lblCriticidadValor2);
        criticidadValor.setText(criticidad);

        idUsuarioValor = (TextView) findViewById(R.id.lblIdusuarioValor2);
        idUsuarioValor.setText(idUsuario);

        Button btnGuardar = (Button) findViewById(R.id.BotonVolver2);

        mAPIService = APIutils.getObservacionAPI();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String id = idObservacionValor.getText().toString().trim();
                Long idObs = Long.parseLong(id);

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

                Observacion observacion = new Observacion(idObs, descripcion,fecha, latitud, longitud,altitud, nivelCritico, new Fenomeno(fenomenol),
                        new Usuario(usuariol), new Localidad(localidadl));

                modificar(observacion);

                Toast.makeText(getApplicationContext(), "Observacion modificada con éxito", Toast.LENGTH_LONG).show();
            }

        });


    }
    public void modificar( Observacion observacion) {
        mAPIService.edit(observacion).enqueue(new Callback<Observacion>() {

            @Override
            public void onResponse(Call<Observacion> call, Response<Observacion> response) {

                if(response.isSuccessful()) {
                    Log.i(TAG, "Exito" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Observacion> call, Throwable t) {
                Log.e(TAG, "Unable to submit to API.");
            }
        });
    }


    public void editarObservacionModificada(View view) {
        finish();
    }

    public void LLamar(View v) {
        Intent intent = new Intent(this, Telefono.class);
        startActivity(intent);
    }

    public void volverMenu(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
