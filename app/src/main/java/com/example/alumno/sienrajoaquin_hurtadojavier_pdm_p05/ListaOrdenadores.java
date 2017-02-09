package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;


import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;


public class ListaOrdenadores extends AppCompatActivity implements View.OnClickListener{

    EditText listaOrdenadores;
    BaseDeDatos bd;
    ImageView imagenOrdenador;
    int contador = 0;
    ArrayList <Ordenador> lista;
    Button siguiente;
    Button anterior;
    Button verHija;
    Button atras;

    ImageView img_ord;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ordenadores);

        siguiente = (Button) findViewById(R.id.botonSiguiente);
        anterior = (Button) findViewById(R.id.botonAnterior);
        verHija = (Button) findViewById(R.id.botonVerHijas);
        atras = (Button) findViewById(R.id.botonAtras);
        img_ord = (ImageView) findViewById(R.id.imageView_img_ordenador);

        siguiente.setOnClickListener(this);
        anterior.setOnClickListener(this);
        verHija.setOnClickListener(this);
        atras.setOnClickListener(this);


        listaOrdenadores = (EditText) findViewById(R.id.editText_Ordenador);

        bd = new BaseDeDatos(getApplicationContext());
        lista = bd.recuperarORDENADORES();

        anterior.setEnabled(false);

        if(lista.size()>1){
            siguiente.setEnabled(true);
        }else{
            siguiente.setEnabled(false);
        }

        if(lista.size()>0) {


            listaOrdenadores.setText(lista.get(contador).toString());
            img_ord.setImageResource(lista.get(contador).getImagen());

        }else{
            listaOrdenadores.setText("LISTA VACIA");
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.botonSiguiente:
                listaOrdenadores.setText(lista.get(++contador).toString());
                img_ord.setImageResource(lista.get(contador).getImagen());

                if(contador == lista.size()){
                    siguiente.setEnabled(false);
                    anterior.setEnabled(true);
                }
                if(contador == 1){
                    siguiente.setEnabled(false);
                    anterior.setEnabled(true);
                }
                break;
            case R.id.botonAnterior:
                listaOrdenadores.setText(lista.get(--contador).toString());
                img_ord.setImageResource(lista.get(contador).getImagen());

                if(contador < lista.size()){
                    siguiente.setEnabled(true);
                }
                if(contador == 0){
                    anterior.setEnabled(false);
                }else{
                    anterior.setEnabled(true);
                    siguiente.setEnabled(false);
                }
        }
    }
}
