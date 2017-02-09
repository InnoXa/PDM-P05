package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListaAlumnos extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alumnos);

        final BaseDeDatos bd = new BaseDeDatos(getApplicationContext());

        ArrayList<Alumno> datos = bd.recuperarALUMNOS();
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ListaAdapter(this, R.layout.layout_lista, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView textoDNI = (TextView) view.findViewById(R.id.textViewDNI);
                    if (textoDNI != null)
                        textoDNI.setText(((Alumno) entrada).getDNI());

                    TextView textoNombre = (TextView) view.findViewById(R.id.textViewNombre);
                    if (textoNombre != null)
                        textoNombre.setText(((Alumno) entrada).getNombre());

                    TextView textoCurso = (TextView) view.findViewById(R.id.textViewCurso);
                    if (textoCurso != null)
                        textoCurso.setText(((Alumno) entrada).getCurso());

                    TextView textoFecha = (TextView) view.findViewById(R.id.textViewFecha);
                    if (textoFecha != null)
                        textoFecha.setText(((Alumno) entrada).getFechaMatriculaString());

                }
            }
        });
        /*lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView textoContacto = (TextView) view.findViewById(R.id.textView_contacto);
                TextView textoTelefono = (TextView) view.findViewById(R.id.textView_telefono);
                TextView textoEmail = (TextView) view.findViewById(R.id.textView_email);
                TextView textoID = (TextView) view.findViewById(R.id.textView_ID);
                CharSequence texto = "Seleccionado: " + textoContacto.getText();
                Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("nombre", textoContacto.getText());
                returnIntent.putExtra("telefono", textoTelefono.getText());
                returnIntent.putExtra("email", textoEmail.getText());
                returnIntent.putExtra("ID", textoID.getText());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });*/
    }
}
