package com.developers.lactimuu_prueba.Modelos;

public class Productos_Rec_Clima_Model {
    String Nombre;
    String Estado_Clima;
    String img_url;
    String Estado;
    int Precio;

    public Productos_Rec_Clima_Model() {
    }


    public Productos_Rec_Clima_Model(String nombre, String estado_Clima, String img_url, String estado, int precio) {
        Nombre = nombre;
        Estado_Clima = estado_Clima;
        this.img_url = img_url;
        Estado = estado;
        Precio = precio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEstado_Clima() {
        return Estado_Clima;
    }

    public void setEstado_Clima(String estado_Clima) {
        Estado_Clima = estado_Clima;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }
}
