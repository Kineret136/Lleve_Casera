package com.anys.lleve_casera_dv.Bean;

public class ProductoMercado {
    private int id_productoMercado, imagen_productoMercado;
    private String nombreProductoMercado,nombreMercados;
    private double precioProductoMercado;

    public ProductoMercado(){

    }

    public ProductoMercado( int id_productoMercado,String nombreProductoMercado,double precioProductoMercado, String nombreMercados, int imagen_productoMercado ) {
        this.id_productoMercado = id_productoMercado;
        this.nombreProductoMercado = nombreProductoMercado;
        this.precioProductoMercado = precioProductoMercado;
        this.nombreMercados = nombreMercados;
        this.imagen_productoMercado = imagen_productoMercado;
    }

    public int getId_productoMercado() {
        return id_productoMercado;
    }

    public void setId_productoMercado(int id_productoMercado) {
        this.id_productoMercado = id_productoMercado;
    }

    public String getNombreProductoMercado() {
        return nombreProductoMercado;
    }

    public void setNombreProductoMercado(String nombreProductoMercado) {
        this.nombreProductoMercado = nombreProductoMercado;
    }

    public double getPrecioProductoMercado() {
        return precioProductoMercado;
    }

    public void setPrecioProductoMercado(double precioProductoMercado) {
        this.precioProductoMercado = precioProductoMercado;
    }

    public String getNombreMercados() {
        return nombreMercados;
    }

    public void setNombreMercados(String nombreMercados) {
        this.nombreMercados = nombreMercados;
    }

    public int getImagen_productoMercado() {
        return imagen_productoMercado;
    }

    public void setImagen_productoMercado(int imagen_productoMercado) {
        this.imagen_productoMercado = imagen_productoMercado;
    }
}
