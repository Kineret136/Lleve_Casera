package com.anys.lleve_casera_dv.io;

import com.anys.lleve_casera_dv.io.response.ComprasResponse;
import com.anys.lleve_casera_dv.io.response.DetalleCompraResponse;
import com.anys.lleve_casera_dv.model.DetallePedido;
import com.anys.lleve_casera_dv.model.Pedidos;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface compraApiService {

    @POST("RegistrarCompraUsuario.php")
    Call<ComprasResponse> registrarPedido(@Body Pedidos pedidos);

    @POST("RegistrarDetalleCompra.php")
    Call<DetalleCompraResponse> registrarDetallePedido(@Body DetallePedido detallePedido);

}
