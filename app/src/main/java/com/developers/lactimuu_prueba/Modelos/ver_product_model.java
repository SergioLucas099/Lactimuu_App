package com.developers.lactimuu_prueba.Modelos;

import java.io.Serializable;

public class ver_product_model implements Serializable {

    private String Descripcion, img_url, Nombre, Estado, Tipo, Estado_Clima, documentId;
    private int Precio;

    public ver_product_model() {
    }

    public ver_product_model(String Descripcion, String img_url, String Nombre, int Precio, String Estado, String Tipo, String Estado_Clima) {
        this.Descripcion = Descripcion;
        this.img_url = img_url;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Estado = Estado;
        this.Tipo = Tipo;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
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
