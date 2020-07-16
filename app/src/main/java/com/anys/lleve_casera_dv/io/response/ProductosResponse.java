package com.anys.lleve_casera_dv.io.response;

import com.anys.lleve_casera_dv.model.Producto;

import java.util.ArrayList;

public class ProductosResponse {
   ArrayList<Producto> productos;
   int estado;
   String mensaje;

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
