package com.anys.lleve_casera_dv.io.response;

import com.anys.lleve_casera_dv.model.RecursoId;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ComprasResponse {
    @SerializedName("estado")
    int estado;
    @SerializedName("mensaje")
    String mensaje;
    @SerializedName("idCompra")
    ArrayList<RecursoId> idCompra;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public ArrayList<RecursoId> getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(ArrayList<RecursoId> idCompra) {
        this.idCompra = idCompra;
    }
}
