package com.developers.lactimuu_prueba.Modelos;

import java.io.Serializable;

public class Ver_Sugerencias_Model implements Serializable {

    String Asunto, Fecha, Hora, Mensaje, Nombre, Telefono, documentId;

    public Ver_Sugerencias_Model() {
    }

    public Ver_Sugerencias_Model(String asunto, String fecha, String hora, String mensaje, String nombre, String telefono) {
        Asunto = asunto;
        Fecha = fecha;
        Hora = hora;
        Mensaje = mensaje;
        Nombre = nombre;
        Telefono = telefono;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String asunto) {
        Asunto = asunto;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String hora) {
        Hora = hora;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
}
