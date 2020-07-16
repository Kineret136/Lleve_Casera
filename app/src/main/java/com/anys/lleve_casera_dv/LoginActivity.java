package com.anys.lleve_casera_dv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.anys.lleve_casera_dv.io.response.UsuarioResponse;
import com.anys.lleve_casera_dv.io.usuarioApiAdapter;
import com.anys.lleve_casera_dv.model.Usuario;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout celu_validacion,contra_validacion;
    private EditText txt_cel, txt_psw;
    private Button btn_login_star,btn_login_registrar;
    private RadioButton RBSesion;

    private boolean isActivateRadioButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Preferences.obtenerPreferencesBoolean(LoginActivity.this, Preferences.PREFERENCES_ESTADO_BUTTON_SESION)){
            startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
        }


        /*Son del TextInputLayout*/
        celu_validacion = findViewById(R.id.txt_celular);
        contra_validacion = findViewById(R.id.txt_contra);

        /*Son de TextInputEditText*/
        txt_cel= findViewById(R.id.txt_cel_i);
        txt_psw= findViewById(R.id.txt_psw_i);

        celu_validacion.setErrorTextColor(ColorStateList.valueOf(Color.WHITE));
        contra_validacion.setErrorTextColor(ColorStateList.valueOf(Color.WHITE));

        RBSesion = findViewById(R.id.RBSesion);

        isActivateRadioButton = RBSesion.isActivated(); //Esta variable guarda "desactivado"

        RBSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isActivateRadioButton){
                    RBSesion.setChecked(false);
                }
                isActivateRadioButton=RBSesion.isChecked(); //Esta variable cambia de valor
            }
        });

        /*Para validar .. .debe ir como cond antes de dar funcion al boton iniciar sesion */


        /*Boton: iniciar sesión*/
        btn_login_star= findViewById(R.id.btn_login_start);
        btn_login_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(confirmar()){
                    autenticarLoginUsuario();  //autenticar el usuario con la base de datos
                }
            }
        });

        /*Boton: registrarse*/
        btn_login_registrar = findViewById(R.id.btn_redi_registro);
        btn_login_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SingInActivity.class);
                startActivity(intent);
            }
        });

    }




    //funcion para validar la existencia del usuario que se loguea
    private void autenticarLoginUsuario(){

        String ps = txt_psw.getText().toString().trim();
        String ph = txt_cel.getText().toString().trim();

        Usuario user= new Usuario();
        user.setContrasenaUsuario(ps);
        user.setCelularUsuario(ph);

        Call<UsuarioResponse> call= usuarioApiAdapter.getApiService().userLogin(user);
        call.enqueue(new UsuariosCallback());

    }

    class UsuariosCallback implements Callback<UsuarioResponse> {

        @Override
        public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
            if(response.isSuccessful()){
                UsuarioResponse usuarioResponse= response.body();
                assert usuarioResponse != null;
                //si estado = 1 -> ps && ph son correctos, user logueado correctamente
                if(usuarioResponse.getEstado()==1){
                    Preferences.savePreferencesBoolean(LoginActivity.this, RBSesion.isChecked(), Preferences.PREFERENCES_ESTADO_BUTTON_SESION); //guardamos el estado del boton solo cuando el usuario se halla logueado correctamente
                    //Extraer los datos del usuario para su posterior uso
                    obtenerDatosUsuario(usuarioResponse.getUsuario());
                    Toast.makeText(getApplicationContext(), R.string.bienvenido, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
                 }else{
                    String msgError = usuarioResponse.getMensaje();
                    Toast.makeText(getApplicationContext(), msgError, Toast.LENGTH_SHORT).show();
                    //Falta realizar el response de errores
                    Toast.makeText(getApplicationContext(), R.string.login_error, Toast.LENGTH_SHORT).show();
                    txt_cel.setText("");
                    txt_psw.setText("");
                }
            }else{
                Toast.makeText(getApplicationContext(),"Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<UsuarioResponse> call, Throwable t) {
            Toast.makeText(getApplicationContext(), t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
        }
    }


    private void obtenerDatosUsuario(ArrayList<Usuario> usuariolist){

        List<String> list = new ArrayList<>();

        for (Usuario u : usuariolist) {
            list.add( u.getCodigoUsuario());
            list.add( u.getNombreUsuario());
            list.add( u.getApellidoUsuario());
            list.add( u.getCelularUsuario());
            list.add( u.getCorreoUsuario());
            list.add( u.getContrasenaUsuario());
        }
        String[] val_user = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            val_user[i] = list.get(i);
        }

        Preferences.savePreferencesString(LoginActivity.this, val_user[0], Preferences.PREFERENCES_codigoUsuario);
        Preferences.savePreferencesString(LoginActivity.this, val_user[1], Preferences.PREFERENCES_nombreUsuario);
        Preferences.savePreferencesString(LoginActivity.this, val_user[2], Preferences.PREFERENCES_apellidoUsuario);
        Preferences.savePreferencesString(LoginActivity.this, val_user[3], Preferences.PREFERENCES_celularUsuario);
        Preferences.savePreferencesString(LoginActivity.this, val_user[4], Preferences.PREFERENCES_correoUsuario);
        Preferences.savePreferencesString(LoginActivity.this, val_user[5], Preferences.PREFERENCES_contrasenaUsuario);

    }

    /*Para validar los datos con el TextInputLayout*/
    private boolean validarCelular(){
        String num = celu_validacion.getEditText().getText().toString().trim();

        if (num.isEmpty()){
            celu_validacion.setError("Este espacio no puede estar en blanco");
            
            return false;
        }else if(num.length()>9) {
            celu_validacion.setError("Ingrese un número de celular válido");
            return false;
        }else {
            celu_validacion.setError(null);
            return true;
        }
    }
    private boolean validarPass(){
        String pass = contra_validacion.getEditText().getText().toString().trim();

        if (pass.isEmpty()){
            contra_validacion.setError("Este espacio no puede estar en blanco");
            return false;
        }else {
            contra_validacion.setError(null);
            return true;
        }
    }
    public boolean confirmar(){
        if (!validarCelular() | !validarPass()){
            return false;
        }
        return true;
    }

}
