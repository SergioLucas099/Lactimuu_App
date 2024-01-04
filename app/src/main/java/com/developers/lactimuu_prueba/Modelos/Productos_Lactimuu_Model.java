package com.developers.lactimuu_prueba.Modelos;

public class Productos_Lactimuu_Model {
    String Nombre;
    String Descripcion;
    String Estado;
    String img_url;
    int Precio;

    public Productos_Lactimuu_Model() {
    }

    public Productos_Lactimuu_Model(String nombre, String descripcion, String estado, String img_url, int precio) {
        Nombre = nombre;
        Descripcion = descripcion;
        Estado = estado;
        this.img_url = img_url;
        Precio = precio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }
}
