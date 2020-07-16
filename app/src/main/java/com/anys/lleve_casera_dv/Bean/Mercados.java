package com.anys.lleve_casera_dv.Bean;

public class Mercados {

    private int id_mercado , imagen_merc,celularMerc;
    private String nombreMerc;
    private String nombreDistr;
    private String nombreProv;
    private String nombreReg;


    public Mercados(int id_mercado, int imagen_merc, int celularMerc, String nombreMerc, String nombreDistr,
                    String nombreProv,String nombreReg) {

        this.id_mercado = id_mercado;
        this.imagen_merc = imagen_merc;
        this.celularMerc = celularMerc;
        this.nombreMerc = nombreMerc;
        this.nombreDistr = nombreDistr;
        this.nombreProv = nombreProv;
        this.nombreReg = nombreReg;

    }

    public Mercados(){

    }

    public int getId_mercado() {
        return id_mercado;
    }

    public void setId_mercado(int id_mercado) {
        this.id_mercado = id_mercado;
    }

    public int getImagen_merc() {
        return imagen_merc;
    }

    public void setImagen_merc(int imagen_merc) {
        this.imagen_merc = imagen_merc;
    }

    public int getCelularMerc() {
        return celularMerc;
    }

    public void setCelularMerc(int celularMerc) {
        this.celularMerc = celularMerc;
    }

    public String getNombreMerc() {
        return nombreMerc;
    }

    public void setNombreMerc(String nombreMerc) {
        this.nombreMerc = nombreMerc;
    }

    public String getNombreDistr() {
        return nombreDistr;
    }

    public void setNombreDistr(String nombreDistr) {
        this.nombreDistr = nombreDistr;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public String getNombreReg() {
        return nombreReg;
    }

    public void setNombreReg(String nombreReg) {
        this.nombreReg = nombreReg;

    }

}
