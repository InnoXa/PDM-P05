package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by alumno on 2/02/17.
 */

public class BaseDeDatos extends SQLiteOpenHelper {

    private static final int VERSION_BASEDATOS = 1;
    private static final String NOMBRE_BASEDATOS = "mibasedatos.db";
    private static final String TABLA_ORDENADORES = "CREATE TABLE IF NOT EXISTS ordenadores " +
            "(id INTEGER PRIMARY KEY, sistemaOperativo TEXT, aula TEXT, estado TEXT, imagen INTEGER)";

    private static final String TABLA_ALUMNOS = "CREATE TABLE IF NOT EXISTS alumnos " +
            "(dni INTEGER PRIMARY KEY, nombre TEXT, curso INTEGER, fecha_matricula TEXT, id_ordenador INTEGER," +
            "FOREIGN KEY(id_ordenador) references ordenadores(id))";

    public BaseDeDatos(Context context){
        super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_ORDENADORES);
        db.execSQL(TABLA_ALUMNOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE ordenadores IF EXISTS; " + TABLA_ORDENADORES);
        db.execSQL("DROP TABLE alumnos IF EXISTS; " + TABLA_ALUMNOS);
        onCreate(db);
    }


    public void limpiarTablaOrdenadores(){
        SQLiteDatabase db = getWritableDatabase();

        if(db!=null){
            db.execSQL("DELETE FROM ordenadores;");
            db.execSQL("VACUUM;");
        }
        db.close();
    }

    public void limpiarTablaAlumnos(){
        SQLiteDatabase db = getWritableDatabase();

        if(db!=null){
            db.execSQL("DELETE FROM alumnos;");
            db.execSQL("VACUUM;");
        }
        db.close();
    }

    public int contarOrdenadores(){
        SQLiteDatabase db = getWritableDatabase();
        int cantidad = 0;
        if(db!=null){
            Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM ordenadores", null);
            mCount.moveToFirst();
            cantidad = mCount.getInt(0);
            mCount.close();
        }
        db.close();
        return cantidad;
    }

    public int contarAlumnos(){
        SQLiteDatabase db = getWritableDatabase();
        int cantidad = 0;
        if(db!=null){
            Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM alumnos", null);
            mCount.moveToFirst();
            cantidad = mCount.getInt(0);
            mCount.close();
        }
        db.close();
        return cantidad;
    }

    public int contarAlumnosPorOrdenador(int idOrdenador){
        SQLiteDatabase db = getWritableDatabase();
        int cantidad = 0;
        if(db!=null){
            Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM alumnos where id_ordenador="+idOrdenador, null);
            mCount.moveToFirst();
            cantidad = mCount.getInt(0);
            mCount.close();
        }
        db.close();
        return cantidad;
    }



    public boolean insertarALUMNO(int dni, String nombre, int curso, String fecha_matricula, int id_ordenador){
        long salida = 0;
        SQLiteDatabase db = getWritableDatabase();

        if(db!=null){
            ContentValues valores = new ContentValues();

            if(dni!=0){
                valores.put("dni", dni);
            }
            valores.put("nombre", nombre);
            valores.put("curso", curso);
            valores.put("fecha_matricula", fecha_matricula);
            valores.put("id_ordenador", id_ordenador);

            salida = db.insert("alumnos", null, valores);

        }
        db.close();
        //Devolvemos un TRUE si ha sido insertado, FALSE si no lo ha hecho
        return(salida>0);
    }


    public boolean insertarORDENADOR(int id, String sistemaOperativo, String aula, String estado, int idimagen){
        long salida = 0;
        SQLiteDatabase db = getWritableDatabase();

        if(db!=null){
            ContentValues valores = new ContentValues();
            if(id!=0){
                valores.put("id", id);
            }
            valores.put("sistemaOperativo", sistemaOperativo);
            valores.put("aula", aula);
            valores.put("estado", estado);
            valores.put("imagen", idimagen);
            salida = db.insert("ordenadores", null, valores);
        }
        db.close();
        //Devolvemos un TRUE si ha sido insertado, FALSE si no lo ha hecho
        return(salida>0);
    }


    public boolean borrarOrdenador(int id){
        SQLiteDatabase db = getWritableDatabase();
        long salida = 0;
        if(db!=null) {
            salida = db.delete("ordenadores", "id="+id, null);
        }
        db.close();
        return(salida>0);
    }

    public boolean borrarAlumno(int dni){
        SQLiteDatabase db = getWritableDatabase();
        long salida = 0;
        if(db!=null) {
            salida = db.delete("alumnos", "dni="+dni, null);
        }
        db.close();
        return(salida>0);
    }

    public boolean borrarAlumnosDeOrdenador(int idOrdenador){
        SQLiteDatabase db = getWritableDatabase();
        long salida = 0;
        if(db!=null) {
            salida = db.delete("alumnos", "id_ordenador="+idOrdenador, null);
        }
        db.close();
        return(salida>0);
    }


    public Ordenador recuperarORDENADOR(int id){
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"id", "sistemaOperativo", "aula", "estado", "imagen"};
        Cursor c = db.query("ordenadores", valores_recuperar, "id="+ id, null, null, null, null, null);

        if(c!=null){
            c.moveToFirst();
        }

        Ordenador ordenador = new Ordenador(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4));

        db.close();
        c.close();
        return ordenador;
    }


    public Alumno recuperarALUMNO(int dni){
        SQLiteDatabase db = getReadableDatabase();
        String[] valores_recuperar = {"dni", "nombre", "curso", "fecha_matricula", "id_ordenador"};
        Cursor c = db.query("alumnos", valores_recuperar, "dni="+ dni, null, null, null, null, null);

        if(c!=null){
            c.moveToFirst();
        }

        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        Alumno alumno = null;
        try {
            alumno = new Alumno(c.getInt(0), c.getString(1), c.getInt(2), dateformat.parse(c.getString(3)), c.getInt(4));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        db.close();
        c.close();
        return alumno;
    }

    public ArrayList<Ordenador> recuperarORDENADORES(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Ordenador> listaOrdenadores = new ArrayList<>();
        String[] valores_recuperar = {"id", "sistemaOperativo", "aula", "estado", "imagen"};
        Cursor c = db.query("ordenadores", valores_recuperar, null, null, null, null, null, null);
        c.moveToFirst();

        do {
            listaOrdenadores.add(new Ordenador(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getInt(4)));
        }while(c.moveToNext());

        db.close();
        c.close();
        return listaOrdenadores;
    }


    public ArrayList<Alumno> recuperarALUMNOS(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        String[] valores_recuperar = {"dni", "nombre", "curso", "fecha_matricula", "id_ordenador"};
        Cursor c = db.query("alumnos", valores_recuperar, null, null, null, null, null, null);
        if(c != null && c.moveToFirst()) {

            do {
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    listaAlumnos.add(new Alumno(c.getInt(0), c.getString(1), c.getInt(2), dateformat.parse(c.getString(3)), c.getInt(4)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (c.moveToNext());

            db.close();
            c.close();
        }
        return listaAlumnos;
    }

    public ArrayList<Alumno> recuperarALUMNOSporID(int id){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        String[] valores_recuperar = {"dni", "nombre", "curso", "fecha_matricula", "id_ordenador"};
        Cursor c = db.query("alumnos", valores_recuperar, "id_ordenador = "+id, null, null, null, null, null);
        if(c != null && c.moveToFirst()) {

            do {
                SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    listaAlumnos.add(new Alumno(c.getInt(0), c.getString(1), c.getInt(2), dateformat.parse(c.getString(3)), c.getInt(4)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } while (c.moveToNext());

            db.close();
            c.close();
        }
        return listaAlumnos;
    }
}
