package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity implements View.OnClickListener{

    Button botonVisualizar;
    Button botonInsertar;
    Button botonBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        botonVisualizar = (Button) findViewById(R.id.botonVisualizar);
        botonInsertar = (Button) findViewById(R.id.botonInsertar);
        botonBorrar = (Button) findViewById(R.id.botonBorrarPrincipal);

        botonVisualizar.setOnClickListener(this);
        botonInsertar.setOnClickListener(this);
        botonBorrar.setOnClickListener(this);

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

        }
    }
}
