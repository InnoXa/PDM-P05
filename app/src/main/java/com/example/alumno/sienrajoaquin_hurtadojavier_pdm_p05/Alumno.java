package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Alumno {

    private int dni;
    private String nombre;
    private int curso;
    private Date fechaMatricula;
    private int IDOrdenador;

    public Alumno() {

    }

    public Alumno(int dni, String nombre, int curso, Date fechaMatricula, int IDOrdenador) {
        this.dni = dni;
        this.nombre = nombre;
        this.curso = curso;
        this.fechaMatricula = fechaMatricula;
        this.IDOrdenador = IDOrdenador;
    }

    public int getDNI() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCurso() {
        return curso;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public String getFechaMatriculaString() {
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(fechaMatricula);
        return dateformat.format(fechaMatricula);
    }

    public int getIDOrdenador() {
        return IDOrdenador;
    }
}
