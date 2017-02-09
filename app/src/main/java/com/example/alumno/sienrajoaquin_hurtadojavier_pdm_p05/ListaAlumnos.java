package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaAlumnos extends AppCompatActivity implements View.OnClickListener{

    private ListView listView;
    Button botonInsertarAlumno, botonAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alumnos);

        botonInsertarAlumno = (Button) findViewById(R.id.button_insertar);

        botonInsertarAlumno.setEnabled(false);
        botonInsertarAlumno.setClickable(false);
        botonInsertarAlumno.setVisibility(View.INVISIBLE);

        botonAtras = (Button) findViewById(R.id.button_Atras);

        final BaseDeDatos bd = new BaseDeDatos(getApplicationContext());
        ArrayList<Alumno> datos;

        switch(getIntent().getExtras().getInt("codigoBoton")){
            case 0:
                datos = bd.recuperarALUMNOS();

                break;
            default:
                datos = bd.recuperarALUMNOSporID(getIntent().getExtras().getInt("idOrdenador"));

                botonInsertarAlumno.setEnabled(true);
                botonInsertarAlumno.setClickable(true);
                botonInsertarAlumno.setVisibility(View.VISIBLE);
        }
        if(datos.isEmpty()){
            Toast.makeText(this, "La lista está vacía.", Toast.LENGTH_SHORT).show();
        }

        botonAtras.setOnClickListener(this);
        botonInsertarAlumno.setOnClickListener(this);

        AlumnoAdapter alumAd= new AlumnoAdapter(this, datos);

        listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(alumAd);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_insertar:
                Intent alum = new Intent(ListaAlumnos.this, insertar_Alumno.class);
                alum.putExtra("idOrdenador", getIntent().getExtras().getInt("idOrdenador"));
                if(getIntent().getExtras().getInt("codigoBoton")==1)
                    alum.putExtra("codigoBoton", 1);
                startActivity(alum);
                break;
            case R.id.button_Atras:
                finish();
        }
    }
}
