package com.developers.lactimuu_prueba.Modelos;

import java.io.Serializable;

public class MyCartModel implements Serializable {

    String Nombre_Producto, Precio, Fecha_Actual, Hora_Pedido, Cantidad, documentId, mesa;
    int Precio_Total;


    public MyCartModel() {
    }

    public MyCartModel(String nombre_Producto, String precio, String fecha_Actual, String hora_Pedido, String cantidad, String documentId, String mesa, int precio_Total) {
        Nombre_Producto = nombre_Producto;
        Precio = precio;
        Fecha_Actual = fecha_Actual;
        Hora_Pedido = hora_Pedido;
        Cantidad = cantidad;
        this.documentId = documentId;
        this.mesa = mesa;
        Precio_Total = precio_Total;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getNombre_Producto() {
        return Nombre_Producto;
    }

    public void setNombre_Producto(String nombre_Producto) {
        Nombre_Producto = nombre_Producto;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getFecha_Actual() {
        return Fecha_Actual;
    }

    public void setFecha_Actual(String fecha_Actual) {
        Fecha_Actual = fecha_Actual;
    }

    public String getHora_Pedido() {
        return Hora_Pedido;
    }

    public void setHora_Pedido(String hora_Pedido) {
        Hora_Pedido = hora_Pedido;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public int getPrecio_Total() {
        return Precio_Total;
    }

    public void setPrecio_Total(int precio_Total) {
        Precio_Total = precio_Total;
    }
}
