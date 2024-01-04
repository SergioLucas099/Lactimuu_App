package com.developers.lactimuu_prueba.Modelos;

import java.io.Serializable;

public class Para_Llevar_Model implements Serializable {

    private String Fecha_Actual;
    private String Hora_Pedido;
    private String Nombre_Producto;
    private String Precio;
    private int Precio_Total;
    private String Cantidad;
    private String Pedido;
    private String DocumentId;

    public Para_Llevar_Model() {
    }

    public Para_Llevar_Model(String fecha_Actual, String hora_Pedido, String nombre_Producto, String precio, int precio_Total, String cantidad, String pedido) {
        Fecha_Actual = fecha_Actual;
        Hora_Pedido = hora_Pedido;
        Nombre_Producto = nombre_Producto;
        Precio = precio;
        Precio_Total = precio_Total;
        Cantidad = cantidad;
        Pedido = pedido;
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

    public int getPrecio_Total() {
        return Precio_Total;
    }

    public void setPrecio_Total(int precio_Total) {
        Precio_Total = precio_Total;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getPedido() {
        return Pedido;
    }

    public void setPedido(String pedido) {
        Pedido = pedido;
    }

    public String getDocumentId() {
        return DocumentId;
    }

    public void setDocumentId(String documentId) {
        DocumentId = documentId;
    }
}
