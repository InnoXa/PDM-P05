package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Principal extends AppCompatActivity implements View.OnClickListener{

    Button botonVisualizar;
    Button botonInsertar;
    Button botonBorrar;
    Button salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        // Restore preferences
        SharedPreferences settings = getPreferences(this.MODE_PRIVATE);
        String saludoOficial = settings.getString("saludo", "");
        Toast.makeText(getApplicationContext(),saludoOficial,Toast.LENGTH_SHORT).show();


        botonVisualizar = (Button) findViewById(R.id.botonVisualizar);
        botonInsertar = (Button) findViewById(R.id.botonInsertar);
        botonBorrar = (Button) findViewById(R.id.botonBorrarPrincipal);
        salir = (Button) findViewById(R.id.buttonSalir);

        botonVisualizar.setOnClickListener(this);
        botonInsertar.setOnClickListener(this);
        botonBorrar.setOnClickListener(this);
        salir.setOnClickListener(this);

        BaseDeDatos bd = new BaseDeDatos(getApplicationContext());
        //bd.borrarAlumnosDeOrdenador(1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.botonVisualizar:
                Intent visual = new Intent(Principal.this, Visualizacion.class);
                startActivity(visual);
                break;
            case R.id.botonInsertar:
                Intent inser = new Intent(Principal.this, Insertar.class);
                startActivity(inser);
                break;
            case R.id.botonBorrarPrincipal:
                Intent borrar = new Intent(Principal.this, BorrarDatos.class);
                startActivity(borrar);
                break;
            case R.id.buttonSalir:
                this.finish();
        }
    }

    @Override
    public void finish(){
        super.finish();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.FRENCH);
        String currentDateandTime = sdf.format(new Date());
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences settings = getPreferences(this.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("saludo", "Bienvenido, "+currentDateandTime);

        // Commit the edits!
        editor.commit();
    }
}
