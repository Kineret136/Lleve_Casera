package com.anys.lleve_casera_dv.io;

import com.anys.lleve_casera_dv.io.response.UsuarioResponse;
import com.anys.lleve_casera_dv.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface usuarioApiService {


    @POST("Validar_Usuario.php")
    Call<UsuarioResponse> userLogin(@Body Usuario usuario);

     @POST("registrar_usuario.php")
     Call <UsuarioResponse> checkinLogin(@Body Usuario usuario);

    @POST("editar_usuario.php")
    Call <UsuarioResponse> updateUser(@Body Usuario usuario);

}
