package com.anys.lleve_casera_dv.model;

import com.google.gson.annotations.SerializedName;

public class RecursoId {
    @SerializedName("ultimoCodigoCompra")
    int ultimoCodigocompra;

    public int getUltimoCodigocompra() {
        return ultimoCodigocompra;
    }

    public void setUltimoCodigocompra(int ultimoCodigocompra) {
        this.ultimoCodigocompra = ultimoCodigocompra;
    }
}
