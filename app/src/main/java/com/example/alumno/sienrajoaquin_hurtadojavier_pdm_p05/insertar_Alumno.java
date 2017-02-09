package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class insertar_Alumno extends AppCompatActivity implements View.OnClickListener {

    BaseDeDatos bd;

    Button atras;
    Button insertar;

    EditText idOrdenador;
    EditText nombre;
    EditText curso;
    DatePicker fechaMatricula;
    EditText DNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_alumno);

        bd = new BaseDeDatos(getApplicationContext());

        atras = (Button) findViewById(R.id.button_atrás_insertar_alum);
        insertar = (Button) findViewById(R.id.button_insertar_alum);

        idOrdenador = (EditText) findViewById(R.id.editText_idOrdenador);
        nombre= (EditText) findViewById(R.id.editText_nombre);
        curso = (EditText) findViewById(R.id.editText_curso);
        fechaMatricula= (DatePicker) findViewById(R.id.datePicker_fechaMatricula);
        DNI= (EditText) findViewById(R.id.editText_dni);

        if(getIntent().getExtras().getInt("codigoBoton") ==1)
            idOrdenador.setText(String.valueOf(getIntent().getExtras().getInt("idOrdenador")));

        atras.setOnClickListener(this);
        insertar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_insertar_alum:
                if(nombre.getText().length()==0 || curso.getText().length()==0 || DNI.getText().length()==0){
                    Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    if(dniCorrecto(Integer.parseInt(DNI.getText().toString()))) {
                        int dia = fechaMatricula.getDayOfMonth();
                        int mes = fechaMatricula.getMonth();
                        int anio = fechaMatricula.getYear();

                        String fecha = dia + "-" + mes + "-" + anio;

                        bd.insertarALUMNO(Integer.parseInt(DNI.getText().toString()),
                                nombre.getText().toString(),
                                Integer.parseInt(curso.getText().toString()),
                                fecha,
                                Integer.parseInt(idOrdenador.getText().toString()));

                        Intent i = new Intent(insertar_Alumno.this, ListaAlumnos.class);
                        i.putExtra("codigoBoton", 1);
                        i.putExtra("idOrdenador", getIntent().getExtras().getInt("idOrdenador"));
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(), "DNI repetido. Inserta otro.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.button_atrás_insertar_alum:
                finish();

        }
    }

    public boolean dniCorrecto(int dni){
        for(Alumno a : bd.recuperarALUMNOS()){
            if(a.getDNI() == dni){
                return false;
            }
        }
        return true;
    }
}
