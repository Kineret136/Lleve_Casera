package com.anys.lleve_casera_dv.io;

import com.anys.lleve_casera_dv.io.response.ProductosResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface productoApiService {

    @GET ("ListarProductoMercado.php")
    Call<ProductosResponse> getAllProducto();
}
