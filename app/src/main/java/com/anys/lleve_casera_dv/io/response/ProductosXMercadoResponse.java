package com.anys.lleve_casera_dv.io.response;

import com.anys.lleve_casera_dv.model.ProductosXMercado;

import java.util.ArrayList;

public class ProductosXMercadoResponse {
    ArrayList<ProductosXMercado> ProductosPorMercado;
    int estado;
    String mensaje;

    public ArrayList<ProductosXMercado> getProductosXMercados() {
        return ProductosPorMercado;
    }

    public void setProductosXMercados(ArrayList<ProductosXMercado> productosXMercados) {
        this.ProductosPorMercado = productosXMercados;
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
