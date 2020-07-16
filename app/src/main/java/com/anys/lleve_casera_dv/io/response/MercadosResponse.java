package com.anys.lleve_casera_dv.io.response;


import com.anys.lleve_casera_dv.model.Mercado;

import java.util.ArrayList;

public class MercadosResponse {

    ArrayList<Mercado> mercados;
    int estado;
    String mensaje;

    public ArrayList<Mercado> getMercados() {
        return mercados;
    }

    public void setMercados(ArrayList<Mercado> mercados) {
        this.mercados = mercados;
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
