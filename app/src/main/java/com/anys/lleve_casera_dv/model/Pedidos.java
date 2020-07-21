package com.anys.lleve_casera_dv.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.ArrayList;
//DEL EP RegistrarCompraUsuario, datos que se envian, los datos que se reciben entan en la clase comprasResponse
public class Pedidos {

    @SerializedName("codigoUsuario")
    private String codigoUsuario;
    @SerializedName("codigoAgencia")
    private int codigoAgencia;
    @SerializedName("precioTotal")
    private double precioTotal;
    @SerializedName("fechaCompra")
    private String fechaCompra;

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoAgencia() {
        return codigoAgencia;
    }

    public void setCodigoAgencia(int codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
