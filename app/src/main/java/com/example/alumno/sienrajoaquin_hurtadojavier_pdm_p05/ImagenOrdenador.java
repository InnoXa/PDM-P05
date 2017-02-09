package com.example.alumno.sienrajoaquin_hurtadojavier_pdm_p05;

public class ImagenOrdenador {

    private String nombre;
    private int idDrawable;

    public ImagenOrdenador(String nombre, int idDrawable) {
        this.nombre = nombre;
        this.idDrawable = idDrawable;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public int getId() {
        return nombre.hashCode();
    }

    public static ImagenOrdenador[] ITEMS = {
            new ImagenOrdenador("Ordenador 1", R.drawable.ordenador1),
            new ImagenOrdenador("Ordenador 2", R.drawable.ordenador2),
    };

    public static ImagenOrdenador getItem(int id) {
        for (ImagenOrdenador item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
