package com.anys.lleve_casera_dv.model;

import java.util.ArrayList;

public class Compra {

    int codigoProducto;
    String nombrePrducto;
    int cantidadProducto;
    double precioProducto;

    public Compra(int codigoProducto, String nombrePrducto, int cantidadProducto, double precioProducto) {
        this.codigoProducto = codigoProducto;
        this.nombrePrducto = nombrePrducto;
        this.cantidadProducto = cantidadProducto;
        this.precioProducto = precioProducto;
    }

    public Compra() {

    }

/*    public Compra(int codigoProducto, int cantidadProducto) {
        this.codigoProducto = codigoProducto;
        this.cantidadProducto = cantidadProducto;
    }*/

    public String getNombrePrducto() {
        return nombrePrducto;
    }

    public void setNombrePrducto(String nombrePrducto) {
        this.nombrePrducto = nombrePrducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }




    @Override
    public String toString() {
        return "Compra{" +
                "codigoProducto=" + codigoProducto +
                ", nombrePrducto='" + nombrePrducto + '\'' +
                ", cantidadProducto=" + cantidadProducto +
                ", precioProducto=" + precioProducto +
                '}';
    }
}
