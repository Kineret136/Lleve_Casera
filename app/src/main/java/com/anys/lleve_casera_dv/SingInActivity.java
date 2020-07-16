package com.anys.lleve_casera_dv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anys.lleve_casera_dv.io.response.UsuarioResponse;
import com.anys.lleve_casera_dv.io.usuarioApiAdapter;
import com.anys.lleve_casera_dv.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingInActivity extends AppCompatActivity {

    EditText txt_nom_reg,txt_ape_reg,txt_cel_reg,txt_email_reg,txt_pass_reg;
    Button btn_registro,btn_back;
    String nombre, apellido,celular,correo,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        txt_nom_reg = findViewById(R.id.txt_nom_r);
        txt_ape_reg = findViewById(R.id.txt_ape_r);
        txt_cel_reg = findViewById(R.id.txt_cel_r);
        txt_email_reg = findViewById(R.id.txt_email_r);
        txt_pass_reg = findViewById(R.id.txt_psw_r);

        btn_registro = findViewById(R.id.btn_registro_r);
        btn_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nombre = txt_nom_reg.getText().toString();
                apellido = txt_ape_reg.getText().toString();
                celular = txt_cel_reg.getText().toString();
                correo = txt_email_reg.getText().toString();
                password = txt_pass_reg.getText().toString();

                Usuario user= new Usuario();
                user.setNombreUsuario(nombre);
                user.setApellidoUsuario(apellido);
                user.setCelularUsuario(celular);
                user.setCorreoUsuario(correo);
                user.setContrasenaUsuario(password);

                Call<UsuarioResponse> call= usuarioApiAdapter.getApiService().checkinLogin(user);
                call.enqueue(new CheckinCallback());
            }
        });

        btn_back = findViewById(R.id.btn_back_r);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    class CheckinCallback implements Callback<UsuarioResponse>{
        String msgError;

        @Override
        public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
            if(response.isSuccessful()){
                UsuarioResponse usuarioResponse= response.body();
                assert usuarioResponse != null;
                //si estado = 1 -> usuario correctamente añadido
                if(usuarioResponse.getEstado()==1){
                    msgError = usuarioResponse.getMensaje();
                    Toast.makeText(getApplicationContext(),msgError,Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SingInActivity.this, LoginActivity.class));
                    finish();
                }else{
                    msgError = usuarioResponse.getMensaje();
                    Toast.makeText(getApplicationContext(), msgError, Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<UsuarioResponse> call, Throwable t) {

        }
    }


}
