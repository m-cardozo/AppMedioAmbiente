package com.example.appproyecto.activities;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appproyecto.R;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class ModificarObservacion extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button Siguiente;
    ArrayAdapter<String> adapterLocalidad;
    ArrayAdapter<String> adpFenomeno;
    ArrayAdapter<String> adpUsuario;

    String[] parts;

    String fecha;
    String localidad;
    String fenomeno;
    String altitud;
    String Latitud;
    String Longitud;
    String descripcion;
    String idUsuario;
    String criticidad;

    String idObservacion;

    EditText eText;

    private Toolbar miActionBar;


    Spinner cmbFenomeno;

    Spinner cmbUsuario;

    Spinner cmbLocalidad;

    String[] dataCriticidad = {"ALTO", "BAJO", "MEDIO"};
    Spinner cmbCriticidad;

    DatePickerDialog picker;

    List<String> localidades = new ArrayList<>();
    List<String> fenomenos = new ArrayList<>();
    List<String> usuarios = new ArrayList<>();
    String[] fenomenoId;
    String[] usuarioId;

    EditText txt_geoLatitud, txt_geoLongitud, txt_altitud;
    private LocationManager locManager;
    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_observacion);


        txt_geoLatitud = ((EditText) findViewById(R.id.latitud2));
        txt_geoLongitud = ((EditText) findViewById(R.id.longitud2));
        txt_altitud = ((EditText) findViewById(R.id.altitud2));



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            txt_geoLatitud.setText("No se han definido los permisos necesarios.");
            txt_geoLongitud.setText("No se han definido los permisos necesarios.");
            txt_altitud.setText("No se han definido los permisos necesarios.");


            return;
        }else
        {
            locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            txt_geoLatitud.setText(String.valueOf(loc.getLatitude()));
            txt_geoLongitud.setText(String.valueOf(loc.getLongitude()));
            txt_altitud.setText(String.valueOf(loc.getAltitude()));
        }






        cmbLocalidad = ((Spinner) findViewById(R.id.Localidad2));
        ModificarObservacion.ObtenerLocalidadesTask tarea = new ModificarObservacion.ObtenerLocalidadesTask();
        tarea.execute();
        cmbLocalidad.setPrompt("Elegir una localidad");
        cmbLocalidad.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Siguiente = (Button) findViewById(R.id.BotonSiguiente2);
        Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarObservacion(view);
            }
        });

        cmbFenomeno = ((Spinner) findViewById(R.id.Fenomeno2));
        ModificarObservacion.ObtenerFenomenosTask tareaFenomenos = new ModificarObservacion.ObtenerFenomenosTask();
        tareaFenomenos.execute();
        cmbFenomeno.setPrompt("Elegir un fenómeno");
        cmbFenomeno.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        cmbUsuario = ((Spinner) findViewById(R.id.Usuario2));
        ModificarObservacion.ObtenerUsuariosTask tareaUsuarios = new ModificarObservacion.ObtenerUsuariosTask();
        tareaUsuarios.execute();
        cmbUsuario.setPrompt("Elegir usuario");
        cmbUsuario.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


        cmbCriticidad = ((Spinner) findViewById(R.id.NivelCritico2));
        ArrayAdapter<String> adpCriticidad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataCriticidad);
        adpCriticidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbCriticidad.setPrompt("Elegir un nivel crítico");
        cmbCriticidad.setAdapter(adpCriticidad);
        adpCriticidad.notifyDataSetChanged();
        cmbCriticidad.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        eText = (EditText) findViewById(R.id.fecha2);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);


                picker = new DatePickerDialog(ModificarObservacion.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth );

                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    public void modificarObservacion(View v) {

        try {

            EditText txt_idObservacion = ((EditText) findViewById(R.id.id2));
            EditText txt_descripcion = ((EditText) findViewById(R.id.descripcion2));
            eText = ((EditText) findViewById(R.id.fecha));


            idObservacion = ((EditText) findViewById(R.id.id2)).getText().toString();
            fecha = ((EditText) findViewById(R.id.fecha2)).getText().toString();
            altitud = ((EditText) findViewById(R.id.altitud2)).getText().toString();
            Latitud = ((EditText) findViewById(R.id.latitud2)).getText().toString();
            Longitud = ((EditText) findViewById(R.id.longitud2)).getText().toString();
            descripcion = ((EditText) findViewById(R.id.descripcion2)).getText().toString();
            fenomenoId = cmbFenomeno.getSelectedItem().toString().split("-");
            fenomeno = fenomenoId[0];
            parts = cmbLocalidad.getSelectedItem().toString().split("-");
            localidad = parts[0];
            criticidad = cmbCriticidad.getSelectedItem().toString();
            usuarioId = cmbUsuario.getSelectedItem().toString().split("-");
            idUsuario = usuarioId[0];

            if (idObservacion.trim().compareTo("") != 0) {
                if (fecha.trim().compareTo("") != 0) {
                    if (descripcion.trim().compareTo("") != 0) {
                        if (Latitud.trim().compareTo("") != 0) {
                            if (Longitud.trim().compareTo("") != 0) {
                                if (fenomeno.trim().compareTo("") != 0) {
                                    if (localidad.trim().compareTo("") != 0) {
                                        if (idUsuario.trim().compareTo("") != 0) {


                                            Intent intent = new Intent(this, ObservacionModificada.class);
                                            intent.putExtra("ID_OBSERVACION", idObservacion);
                                            intent.putExtra("DESCRIPCION", descripcion);
                                            intent.putExtra("FECHA", fecha);
                                            intent.putExtra("LATITUD", Latitud);
                                            intent.putExtra("LONGITUD", Longitud);
                                            intent.putExtra("ALTITUD" , altitud);
                                            intent.putExtra("FENOMENO", fenomeno);
                                            intent.putExtra("LOCALIDAD", localidad);
                                            intent.putExtra("CRITICIDAD", criticidad);
                                            intent.putExtra("IDUSUARIO", idUsuario);

                                            startActivity(intent);


                                        } else {
                                            Toast.makeText(this, "Debe ingresar un id de usuario", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        Toast.makeText(this, "Debe ingresar una localidad de observacion.", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(this, "Debe ingresar un fenomeno de observacion.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                txt_geoLongitud.setError("Ingresar longitud");
                                Toast.makeText(this, "Debe ingresar una longitud de observacion.", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            txt_geoLatitud.setError("Ingresar latitud");
                            Toast.makeText(this, "Debe ingresar una latitud de observacion.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        txt_descripcion.setError("Ingresar descripcion");
                        Toast.makeText(this, "Debe ingresar una descripcion de observacion.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    eText.setError("Ingresar fecha");
                    Toast.makeText(this, "Debe ingresar una fecha de observacion.", Toast.LENGTH_SHORT).show();
                }
            } else {
                txt_idObservacion.setError("Ingresar id");
                Toast.makeText(this, "Debe ingresar un numero de observacion.", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            Toast.makeText(this, "Error al procesar los datos ingresados, por favor verifique los mismos.", Toast.LENGTH_SHORT).show();
        }}
    public void LLamar(View v) {
        Intent intent = new Intent(this, Telefono.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //Obtener localidades para Spinner
    private class ObtenerLocalidadesTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = true;
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                String urlServicio
                        = "http://192.168.0.131:8081/MedioAmbiente/rest/localidades/localidades";
                url = new URL(urlServicio);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("content-type",
                        "application/json");
                InputStream in = new
                        BufferedInputStream(urlConnection.getInputStream());
//Me traigo el json con las ciudades
                JSONArray respJSON = new JSONArray(getResponseText(in));
                //  List<String> localidades = new ArrayList<>();
                for (int i = 0; i < respJSON.length(); i++) {
                    JSONArray array = respJSON.getJSONArray(i);
                    String id = array.getString(0);
                    String descripcion = array.getString(1);

                    localidades.add(id + "-" + descripcion);
                }

                adapterLocalidad = new ArrayAdapter<>(ModificarObservacion.this, android.R.layout.simple_spinner_dropdown_item, localidades);


            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean == true) {
//Relacionando la lista con el adaptador
                cmbLocalidad.setAdapter(adapterLocalidad);
                adapterLocalidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            }
        }

        private String getResponseText(InputStream inStream) {
//http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
            return new Scanner(inStream).useDelimiter("\\A").next();
        }
    }

    // Obtener Fenomenos para Spinner
    private class ObtenerFenomenosTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = true;
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                String urlServicio
                        = "http://192.168.0.131:8081/MedioAmbiente/rest/fenomenos/fenomenos";
                url = new URL(urlServicio);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("content-type",
                        "application/json");
                InputStream in = new
                        BufferedInputStream(urlConnection.getInputStream());
//Me traigo el json con las ciudades
                JSONArray respJSON = new JSONArray(getResponseText(in));
                //  List<String> localidades = new ArrayList<>();
                for (int i = 0; i < respJSON.length(); i++) {
                    JSONArray array = respJSON.getJSONArray(i);
                    String id = array.getString(0);
                    String descripcion = array.getString(1);

                    fenomenos.add(id + "-" + descripcion);
                }

                adpFenomeno = new ArrayAdapter<>(ModificarObservacion.this, android.R.layout.simple_spinner_dropdown_item,fenomenos);


            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean == true) {
//Relacionando la lista con el adaptador
                cmbFenomeno.setAdapter(adpFenomeno);
                adpFenomeno.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            }
        }

        private String getResponseText(InputStream inStream) {
//http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
            return new Scanner(inStream).useDelimiter("\\A").next();
        }



    }

    // Obtener Fenomenos para Spinner
    private class ObtenerUsuariosTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = true;
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                String urlServicio
                        = "http://192.168.0.131:8081/MedioAmbiente/rest/usuarios/usuarios";
                url = new URL(urlServicio);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("content-type",
                        "application/json");
                InputStream in = new
                        BufferedInputStream(urlConnection.getInputStream());
//Me traigo el json con las ciudades
                JSONArray respJSON = new JSONArray(getResponseText(in));
                //  List<String> localidades = new ArrayList<>();
                for (int i = 0; i < respJSON.length(); i++) {
                    JSONArray array = respJSON.getJSONArray(i);
                    String id = array.getString(0);
                    String descripcion = array.getString(1);

                    usuarios.add(id + "-" + descripcion);
                }

                adpUsuario = new ArrayAdapter<>(ModificarObservacion.this, android.R.layout.simple_spinner_dropdown_item,usuarios);


            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (aBoolean == true) {
//Relacionando la lista con el adaptador
                cmbUsuario.setAdapter(adpUsuario);
                adpUsuario.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            }
        }

        private String getResponseText(InputStream inStream) {
//http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
            return new Scanner(inStream).useDelimiter("\\A").next();
        }



    }
}

