package com.anys.lleve_casera_dv.model;

import com.google.gson.annotations.SerializedName;

public class Producto {

    @SerializedName("nombreProducto")
    private String nombreProducto;
    @SerializedName("precioProductoM")
    private String precioProductoM;
    @SerializedName("codigoMercado")
    private int codigoMercado;
    @SerializedName("nombreMercado")
    private String nombreMercado;
    @SerializedName("distritoMercado")
    private String distritoMercado;

    public Producto(String nombreProducto, String precioProductoM, int codigoMercado, String nombreMercado, String distritoMercado) {
        this.nombreProducto = nombreProducto;
        this.precioProductoM = precioProductoM;
        this.codigoMercado = codigoMercado;
        this.nombreMercado = nombreMercado;
        this.distritoMercado = distritoMercado;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getPrecioProductoM() {
        return precioProductoM;
    }

    public void setPrecioProductoM(String precioProductoM) {
        this.precioProductoM = precioProductoM;
    }

    public int getCodigoMercado() {
        return codigoMercado;
    }

    public void setCodigoMercado(int codigoMercado) {
        this.codigoMercado = codigoMercado;
    }

    public String getNombreMercado() {
        return nombreMercado;
    }

    public void setNombreMercado(String nombreMercado) {
        this.nombreMercado = nombreMercado;
    }

    public String getDistritoMercado() {
        return distritoMercado;
    }

    public void setDistritoMercado(String distritoMercado) {
        this.distritoMercado = distritoMercado;
    }
}
