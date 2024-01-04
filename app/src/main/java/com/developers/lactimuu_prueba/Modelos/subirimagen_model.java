package com.developers.lactimuu_prueba.Modelos;

public class subirimagen_model {
    private String Nombre, ImgURL;

    public subirimagen_model() {

    }

    public subirimagen_model(String nombre, String imgurl) {
        if (nombre.trim().equals("")){
            nombre = "No Nombre";
        }

        Nombre = nombre;
        ImgURL = imgurl;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getImgURL() {
        return ImgURL;
    }

    public void setImgURL(String imgURL) {
        ImgURL = imgURL;
    }
}
