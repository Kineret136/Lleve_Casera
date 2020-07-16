package com.anys.lleve_casera_dv.model;

import com.google.gson.annotations.SerializedName;

public class Mercado {
    @SerializedName("codigoMercado")
    private int codigoMercado;
    @SerializedName("nombreMercado")
    private String nombreMercado;
    @SerializedName("distritoMercado")
    private String distritoMercado;
    @SerializedName("provinciaMercado")
    private String provinciaMercado;
    @SerializedName("regionMercado")
    private String regionMercado;
    @SerializedName("celularMercado")
    private String celularMercado;

    public Mercado() {
    }

    public Mercado(int codigoMercado, String nombreMercado, String distritoMercado, String provinciaMercado, String regionMercado, String celularMercado) {
        this.codigoMercado = codigoMercado;
        this.nombreMercado = nombreMercado;
        this.distritoMercado = distritoMercado;
        this.provinciaMercado = provinciaMercado;
        this.regionMercado = regionMercado;
        this.celularMercado = celularMercado;
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

    public String getProvinciaMercado() {
        return provinciaMercado;
    }

    public void setProvinciaMercado(String provinciaMercado) {
        this.provinciaMercado = provinciaMercado;
    }

    public String getRegionMercado() {
        return regionMercado;
    }

    public void setRegionMercado(String regionMercado) {
        this.regionMercado = regionMercado;
    }

    public String getCelularMercado() {
        return celularMercado;
    }

    public void setCelularMercado(String celularMercado) {
        this.celularMercado = celularMercado;
    }
}
