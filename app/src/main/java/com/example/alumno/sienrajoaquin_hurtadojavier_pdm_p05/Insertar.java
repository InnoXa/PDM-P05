package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class Insertar extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener{

    GridView grid;
    Button insertar;
    EditText id;
    EditText so;
    EditText aula;
    EditText estado;
    int imagen = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        insertar = (Button) findViewById(R.id.boton_Insertar);
        grid = (GridView) findViewById(R.id.grid);
        OrdenadoresAdapter orAd = new OrdenadoresAdapter(this);

        id = (EditText) findViewById(R.id.editText_id);
        so = (EditText) findViewById(R.id.editText_so);
        aula = (EditText) findViewById(R.id.editText_aula);
        estado = (EditText) findViewById(R.id.editText_estado);


        grid.setAdapter(orAd);
        grid.setOnItemClickListener(this);

        insertar.setOnClickListener(this);
        id.setOnClickListener(this);
        so.setOnClickListener(this);
        aula.setOnClickListener(this);
        estado.setOnClickListener(this);
    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.boton_Insertar:
                if(id.getText().length()==0 || so.getText().length()==0 ||
                        aula.getText().length()==0 || estado.getText().length()==0 || imagen == -1){
                    Toast.makeText(getApplicationContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        BaseDeDatos bd = new BaseDeDatos(getApplicationContext());

                        bd.insertarORDENADOR(Integer.parseInt(id.getText().toString()),
                                            so.getText().toString(),
                                            aula.getText().toString(),
                                            estado.getText().toString(),
                                            imagen);

                        finish();
                    } catch (SQLiteConstraintException sqle) {

                    }
                }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ImagenOrdenador item = (ImagenOrdenador) parent.getItemAtPosition(position);

        imagen = item.getIdDrawable();

        System.out.println(item.getIdDrawable());
    }

}
