package com.developers.lactimuu_prueba.Modelos;

import java.io.Serializable;

public class Imagenes_Promocionales_Modell implements Serializable {

    String url, documentoid;

    public Imagenes_Promocionales_Modell() {
    }

    public Imagenes_Promocionales_Modell(String url) {
        this.url = url;
    }

    public String getDocumentoid() {
        return documentoid;
    }

    public void setDocumentoid(String documentoid) {
        this.documentoid = documentoid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
