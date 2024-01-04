package com.developers.lactimuu_prueba.Modelos;

import java.io.Serializable;

public class Ver_Usuarios_Model implements Serializable {

    private String Correo, Estado, Nombre, Rol, documentId;

    public Ver_Usuarios_Model() {
    }

    public Ver_Usuarios_Model(String correo, String estado, String nombre, String rol) {
        Correo = correo;
        Estado = estado;
        Nombre = nombre;
        Rol = rol;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }
}
