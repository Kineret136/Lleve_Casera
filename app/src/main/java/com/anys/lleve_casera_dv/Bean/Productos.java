package com.anys.lleve_casera_dv.Bean;

public class Productos {

    private int id_producto, imagen_prod ;
    private String nombreProd,precioProd,nombreMerc,nombreDistr;


    public Productos(int id_producto,String nombreProd,String precioProd,String nombreMerc,String nombreDistr,int imagen_prod){
        this.id_producto = id_producto;
        this.nombreProd = nombreProd;
        this.precioProd = precioProd;
        this.nombreMerc = nombreMerc;
        this.nombreDistr = nombreDistr;
        this.imagen_prod = imagen_prod;
    }

    public Productos(){

    }

    public int getId() {
        return id_producto;
    }

    public void setId(int id) {
        this.id_producto = id;
    }

    public String getNombreProd() {
        return nombreProd;
    }

    public void setNombreProd(String nombreProd) {
        this.nombreProd = nombreProd;
    }

    public String getPrecioProd() {
        return precioProd;
    }

    public void setPrecioProd(String precioProd) {
        this.precioProd = precioProd;
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

    public int getImagen_prod() { return imagen_prod;    }

    public void setImagen_prod(int image) {
        this.imagen_prod = image;
    }



}
