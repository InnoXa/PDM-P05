package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Visualizacion extends AppCompatActivity implements View.OnClickListener {

    Button botonAtras;
    ImageButton botonListarOrdenadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacion);

        botonAtras = (Button) findViewById(R.id.button_atras);
        botonListarOrdenadores = (ImageButton) findViewById(R.id.imageButton_verOrdenadores);

        botonListarOrdenadores.setOnClickListener(this);
        botonAtras.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButton_verOrdenadores:
                Intent visual = new Intent(Visualizacion.this, ListaOrdenadores.class);
                startActivity(visual);
                break;
            case R.id.button_atras:
                finish();
        }
    }
}
