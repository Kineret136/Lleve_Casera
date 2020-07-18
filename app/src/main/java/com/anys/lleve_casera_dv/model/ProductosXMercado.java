package com.anys.lleve_casera_dv.model;

import com.google.gson.annotations.SerializedName;

public class ProductosXMercado {
    @SerializedName("codigoProductoM")
    private int id;
    @SerializedName("producto")
    private String producto;
    @SerializedName("precio")
    private double precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
