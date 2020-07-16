package com.anys.lleve_casera_dv.Bean;

public class ElementosMercado {
    private int id_elementoMercado, imagen_elem ;
    private String nombreElem;
    double precioElem;

    public ElementosMercado(){}

    public ElementosMercado(int id_elementoMercado, String nombreElem, double precioElem, int imagen_elem) {
        this.id_elementoMercado = id_elementoMercado;
        this.imagen_elem = imagen_elem;
        this.nombreElem = nombreElem;
        this.precioElem = precioElem;
    }

    public int getId_elementoMercado() {
        return id_elementoMercado;
    }

    public void setId_elementoMercado(int id_elementoMercado) {
        this.id_elementoMercado = id_elementoMercado;
    }

    public int getImagen_elem() {
        return imagen_elem;
    }

    public void setImagen_elem(int imagen_elem) {
        this.imagen_elem = imagen_elem;
    }

    public String getNombreElem() {
        return nombreElem;
    }

    public void setNombreElem(String nombreElem) {
        this.nombreElem = nombreElem;
    }

    public double getPrecioElem() {
        return precioElem;
    }

    public void setPrecioElem(double precioElem) {
        this.precioElem = precioElem;
    }
}
