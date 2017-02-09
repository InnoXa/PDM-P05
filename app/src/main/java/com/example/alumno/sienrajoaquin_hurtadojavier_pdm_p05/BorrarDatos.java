package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BorrarDatos extends AppCompatActivity implements View.OnClickListener {

    EditText editTextIDBorrar, editTextDNIBorrar;
    Button borrar, atras;

    BaseDeDatos bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_datos);

        bd = new BaseDeDatos(getApplicationContext());

        borrar = (Button) findViewById(R.id.botonBorrarPrincipal);
        atras = (Button) findViewById(R.id.botonAtrasBorrado);

        editTextDNIBorrar = (EditText)findViewById(R.id.editTextDNIBorrar);
        editTextIDBorrar = (EditText)findViewById(R.id.editTextIDBorrar);


        borrar.setOnClickListener(this);
        atras.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.botonBorrarPrincipal:
                // Si no se ha escrito en ninguno de los campos
                if(editTextDNIBorrar.getText().length() == 0 && editTextIDBorrar.getText().length() == 0){
                    Toast.makeText(getApplicationContext(),"Rellene uno de los dos campos.", Toast.LENGTH_SHORT).show();
                }
                //Si ambos campos tienen textos escritos
                else if(editTextDNIBorrar.getText().length() > 0 && editTextIDBorrar.getText().length() > 0){
                    Toast.makeText(getApplicationContext(),"Rellene SOLO uno de los dos campos", Toast.LENGTH_SHORT).show();
                    editTextDNIBorrar.setText("");
                    editTextIDBorrar.setText("");
                }
                //Si vamos a borrar un alumno
                else if(editTextDNIBorrar.getText().length() > 0 && editTextIDBorrar.getText().length() == 0){
                    //Si el alumno existe
                    if(bd.borrarAlumno(Integer.parseInt(editTextDNIBorrar.getText().toString()))){
                        Toast.makeText(getApplicationContext(),"El alumno ha sido borrado", Toast.LENGTH_SHORT).show();
                    }
                    //Si el alumno no existe
                    else{
                        Toast.makeText(getApplicationContext(),"El alumno no existe", Toast.LENGTH_SHORT).show();
                    }
                    editTextDNIBorrar.setText("");
                }
                //Si vamos a borrar un ordenador
                else{
                    //Si el ordenador tiene alumno
                    if(bd.contarAlumnosPorOrdenador(Integer.parseInt(editTextIDBorrar.getText().toString()))>0){
                        //Si tiene alumnos, los borramos primeramente y luego borramos el ordenador
                        if(bd.borrarAlumnosDeOrdenador(Integer.parseInt(editTextIDBorrar.getText().toString()))
                                &&
                                bd.borrarOrdenador(Integer.parseInt(editTextIDBorrar.getText().toString()))){
                            Toast.makeText(getApplicationContext(),"Ordenador borrado junto con alumnos", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //Si el ordenador no tiene ningun alumno
                    else{
                        //Si existe el ordenador lo borramos
                        if(bd.borrarOrdenador(Integer.parseInt(editTextIDBorrar.getText().toString()))){
                            Toast.makeText(getApplicationContext(),"Ordenador borrado", Toast.LENGTH_SHORT).show();
                        }
                        //Si no existe el ordenador, se lo comunicamos al usuario
                        else{
                            Toast.makeText(getApplicationContext(),"El Ordenador no existe", Toast.LENGTH_SHORT).show();
                        }
                    }
                    editTextIDBorrar.setText("");
                }
                break;
            case R.id.botonAtrasBorrado:
                finish();
                break;
        }
    }
}
