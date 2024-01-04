package com.developers.lactimuu_prueba.Modelos;

import java.io.Serializable;

public class ViewAllModel implements Serializable {

    String Nombre;
    String Descripcion;
    String Estado;
    String img_url;
    String Tipo;
    String Estado_Clima;
    int Precio;

    public ViewAllModel() {
    }

    public ViewAllModel(String nombre, String descripcion, String estado, String img_url, String tipo, String estado_Clima, int precio) {
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Estado = estado;
        this.img_url = img_url;
        this.Tipo = tipo;
        this.Estado_Clima = estado_Clima;
        this.Precio = precio;
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

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public String getEstado_Clima() {
        return Estado_Clima;
    }

    public void setEstado_Clima(String estado_Clima) {
        Estado_Clima = estado_Clima;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int precio) {
        Precio = precio;
    }
}
