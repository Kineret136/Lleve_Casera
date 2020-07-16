package com.anys.lleve_casera_dv.Bean;

public class Carrito {
    private int id_pedidoCarrito , imagen_pedido, cantidadProdCarrito;
    private String nombreProdCarrito;
    double precioProdCarrito;

    public Carrito(){

    }

    public Carrito(int id_pedidoCarrito,String nombreProdCarrito, double precioProdCarrito,  int cantidadProdCarrito, int imagen_pedido) {
        this.id_pedidoCarrito = id_pedidoCarrito;
        this.imagen_pedido = imagen_pedido;
        this.cantidadProdCarrito = cantidadProdCarrito;
        this.nombreProdCarrito = nombreProdCarrito;
        this.precioProdCarrito = precioProdCarrito;
    }

    public int getId_pedidoCarrito() {
        return id_pedidoCarrito;
    }

    public void setId_pedidoCarrito(int id_pedidoCarrito) {
        this.id_pedidoCarrito = id_pedidoCarrito;
    }

    public int getImagen_pedido() {
        return imagen_pedido;
    }

    public void setImagen_pedido(int imagen_pedido) {
        this.imagen_pedido = imagen_pedido;
    }

    public int getCantidadProdCarrito() {
        return cantidadProdCarrito;
    }

    public void setCantidadProdCarrito(int cantidadProdCarrito) {
        this.cantidadProdCarrito = cantidadProdCarrito;
    }

    public String getNombreProdCarrito() {
        return nombreProdCarrito;
    }

    public void setNombreProdCarrito(String nombreProdCarrito) {
        this.nombreProdCarrito = nombreProdCarrito;
    }

    public double getPrecioProdCarrito() {
        return precioProdCarrito;
    }

    public void setPrecioProdCarrito(double precioProdCarrito) {
        this.precioProdCarrito = precioProdCarrito;
    }
}
