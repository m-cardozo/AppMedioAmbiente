package com.example.appproyecto.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.appproyecto.R;

import java.util.List;

public class ObservacionesAdapter extends ArrayAdapter<Observaciones> {

    public ObservacionesAdapter(Context context, List<Observaciones> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
// Obtener inflater.
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
// ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_observacion,
                    parent,
                    false);
        }

        TextView id = (TextView) convertView.findViewById(R.id.tv_id);
        TextView descripcion = (TextView) convertView.findViewById(R.id.tv_descripcion);
        TextView fecha = (TextView) convertView.findViewById(R.id.tv_fecha);
        TextView nivelCritico = (TextView) convertView.findViewById(R.id.tv_nivelCritico);
        TextView fenomeno = (TextView) convertView.findViewById(R.id.tv_fenomeno);
        TextView localidad = (TextView) convertView.findViewById(R.id.tv_localidad);
        TextView latitud = (TextView)convertView.findViewById(R.id.tv_latitud);
        TextView longitud = (TextView)convertView.findViewById(R.id.tv_longitud);
        TextView altitud = (TextView)convertView.findViewById(R.id.tv_altitud);
        TextView usuario = (TextView)convertView.findViewById(R.id.tv_usuario);

        Observaciones observacion = getItem(position);
// Setup.
        id.setText(observacion.getmId());
        descripcion.setText(observacion.getmDescripcion());
        fecha.setText(observacion.getMfecha());
      nivelCritico.setText(observacion.getMnivelCritico());
     fenomeno.setText(observacion.getMfenomeno());
       localidad.setText(observacion.getmLocalidad());
        latitud.setText(observacion.getmLatitud());
        longitud.setText(observacion.getmLongitud());
        altitud.setText(observacion.getmAltitud());
        usuario.setText(observacion.getmUsuario());

        return convertView;


    }
}
