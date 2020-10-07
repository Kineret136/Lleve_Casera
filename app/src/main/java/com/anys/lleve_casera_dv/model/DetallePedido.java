package com.anys.lleve_casera_dv.model;

import com.google.gson.annotations.SerializedName;

public class DetallePedido {

    @SerializedName("codigoCompra")
    private int codigoCompra;
    @SerializedName("codigoProductoM")
    private int codigoProductoM;
    @SerializedName("cantidadCompra")
    private int cantidadCompra;

    public int getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(int codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public int getCodigoProductoM() {
        return codigoProductoM;
    }

    public void setCodigoProductoM(int codigoProductoM) {
        this.codigoProductoM = codigoProductoM;
    }

    public int getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(int cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }
}
