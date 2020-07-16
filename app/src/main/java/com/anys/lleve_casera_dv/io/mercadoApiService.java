package com.anys.lleve_casera_dv.io;

import com.anys.lleve_casera_dv.io.response.MercadosResponse;
import com.anys.lleve_casera_dv.io.response.ProductosXMercadoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface mercadoApiService {

    @GET ("Obtener_mercado.php")
    Call<MercadosResponse> getAllMercado();

    //https://smipmec.000webhostapp.com/BuscarProductoMercado.php?codigoMercado=1
    @GET ("BuscarProductoMercado.php")
    Call<ProductosXMercadoResponse> getProductosXMercado(@Query("codigoMercado") int id);

}
