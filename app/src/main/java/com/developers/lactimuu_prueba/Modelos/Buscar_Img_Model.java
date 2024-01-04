package com.developers.lactimuu_prueba.Modelos;

import java.io.Serializable;

public class Buscar_Img_Model implements Serializable {

    String nombre;
    String Url;
    String Documentoid;

    public Buscar_Img_Model() {
    }

    public Buscar_Img_Model(String nombre, String url, String documentoid) {
        this.nombre = nombre;
        Url = url;
        Documentoid = documentoid;
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
