package com.example.appproyecto.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.appproyecto.adapters.Observaciones;
import com.example.appproyecto.adapters.ObservacionesAdapter;
import com.example.appproyecto.R;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ObservacionesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ObservacionesFragment extends Fragment {

    ListView mObservacionesList;
    EditText filtro;
    ArrayAdapter<Observaciones> mObservacionesAdapter;


    public ObservacionesFragment() {
        // Required empty public constructor
    }

    public static ObservacionesFragment newInstance() {
        ObservacionesFragment fragment = new ObservacionesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_observaciones, container, false);
        mObservacionesList = (ListView) root.findViewById(R.id.observaciones_list);
        ObtenerObservacionesTask tarea = new ObtenerObservacionesTask();
        tarea.execute();

        filtro = (EditText) root.findViewById(R.id.observaciones_buscar);


        filtro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (ObservacionesFragment.this).mObservacionesAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

// Inicializar el adaptador con la fuente de datos.
        return root;
    }




    private class ObtenerObservacionesTask extends AsyncTask<Void, Void, Boolean> {
        //No tenemos parámetro de entrada ni de progreso. Si de salida que
//nos indica mediante un booleano si todo salió Ok


        @Override
        protected Boolean doInBackground(Void... params) {
            boolean result = true;
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                String urlServicio
                        = "http://192.168.0.131:8081/MedioAmbiente/rest/observaciones/observaciones";
                url = new URL(urlServicio);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("content-type",
                        "application/json");
                InputStream in = new
                        BufferedInputStream(urlConnection.getInputStream());
//Me traigo el json con las ciudades
                JSONArray respJSON = new JSONArray(getResponseText(in));
                List<Observaciones> observaciones = new ArrayList<>();
                for (int i = 0; i < respJSON.length(); i++) {
                    JSONArray array = respJSON.getJSONArray(i);
                    String id = array.getString(0);
                    String descripcion = array.getString(1);
                    String fecha = array.getString(2);
                    String nivel = array.getString(3);
                    String fenomeno = array.getString(4);
                    String localidad = array.getString(5);
                    String latitud  = array.getString(6);
                    String longitud  = array.getString(7);
                    String altitud  = array.getString(8);
                    String usuario = array.getString(9);


                    observaciones.add(new Observaciones(id, descripcion, fecha, nivel, fenomeno, localidad, latitud, longitud, altitud, usuario));
                }
                mObservacionesAdapter = new ObservacionesAdapter(getActivity(), observaciones);
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
                mObservacionesList.setAdapter(mObservacionesAdapter);
            }
        }

        private String getResponseText(InputStream inStream) {
//http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
            return new Scanner(inStream).useDelimiter("\\A").next();
        }
    }
}
