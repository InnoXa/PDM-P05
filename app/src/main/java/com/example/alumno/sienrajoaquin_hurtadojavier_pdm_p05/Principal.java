package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Principal extends AppCompatActivity implements View.OnClickListener{

    Button botonVisualizar;
    Button botonInsertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        botonVisualizar = (Button) findViewById(R.id.botonVisualizar);
        botonInsertar = (Button) findViewById(R.id.botonInsertar);

        botonVisualizar.setOnClickListener(this);
        botonInsertar.setOnClickListener(this);

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
        }
    }
}
