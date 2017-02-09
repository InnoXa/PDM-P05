package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

public class Ordenador {

    private int id;
    private String sistemaOperativo;
    private String aula;
    private String estado;
    private int idimagen;

    public Ordenador() {

    }

    public Ordenador(int id, String sistemaOperativo, String aula, String estado, int idimagen) {
        this.id = id;
        this.sistemaOperativo = sistemaOperativo;
        this.aula = aula;
        this.estado = estado;
        this.idimagen = idimagen;
    }

    public int getId() {
        return id;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public String getAula() {
        return aula;
    }

    public String getEstado() {
        return estado;
    }

    public int getImagen() {
        return idimagen;
    }

    public String toString(){
        return "ID: "+id+"\n"+
                "SO: "+sistemaOperativo+"\n"+
                "Aula: "+aula+"\n"+
                "Estado: "+estado+"\n";
    }
}
