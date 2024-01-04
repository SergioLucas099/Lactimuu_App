package com.developers.lactimuu_prueba.Modelos;

import java.io.Serializable;

public class Ver_Imagenes_Model implements Serializable {

    String nombre;
    String Url;
    String Documentoid;

    public Ver_Imagenes_Model() {
    }

    public Ver_Imagenes_Model(String nombre, String url) {
        this.nombre = nombre;
        Url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getDocumentoid() {
        return Documentoid;
    }

    public void setDocumentoid(String documentoid) {
        Documentoid = documentoid;
    }
}
