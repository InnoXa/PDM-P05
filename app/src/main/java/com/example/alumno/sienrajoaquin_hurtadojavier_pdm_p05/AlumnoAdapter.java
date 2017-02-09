package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AlumnoAdapter extends ArrayAdapter<Alumno>{

    public AlumnoAdapter(Context context, ArrayList<Alumno> jugadores){
        super(context, 0, jugadores);
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Alumno alumno = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_lista, parent, false);
        }

        TextView textViewDNI = (TextView) convertView.findViewById(R.id.textViewDNI);
        TextView textViewNombre = (TextView) convertView.findViewById(R.id.textViewNombre);
        TextView textViewCurso = (TextView) convertView.findViewById(R.id.textViewCurso);
        TextView textViewFecha = (TextView) convertView.findViewById(R.id.textViewFecha);
        TextView textViewIDOrdenador = (TextView) convertView.findViewById(R.id.textViewIDOrdenador);

        textViewDNI.setText(String.valueOf(alumno.getDNI()));
        textViewNombre.setText(alumno.getNombre());
        textViewCurso.setText(String.valueOf(alumno.getCurso()));
        textViewFecha.setText(alumno.getFechaMatriculaString());
        textViewIDOrdenador.setText(String.valueOf(alumno.getIDOrdenador()));

        return convertView;
    }
}
