package com.anys.lleve_casera_dv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anys.lleve_casera_dv.io.response.UsuarioResponse;
import com.anys.lleve_casera_dv.io.usuarioApiAdapter;
import com.anys.lleve_casera_dv.model.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PerfilFragment extends Fragment {

    String nomUser,ApeUser, emaiuser, celular_, contrasena, codigo;
    String nombre, apellido,celular,correo,password;
    EditText txt_nom_a,txt_ape_a,txt_cel_a,txt_psw_a,txt_email_a;
    private Button btn_actualizar_datos;
    View vista;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Perfil");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista =inflater.inflate(R.layout.fragment_perfil, container, false);
        obterPreferencias();

        txt_nom_a = vista.findViewById(R.id.txt_nom_a);
        txt_ape_a = vista.findViewById(R.id.txt_ape_a);
        txt_cel_a = vista.findViewById(R.id.txt_cel_a);
        txt_psw_a = vista.findViewById(R.id.txt_psw_a);
        txt_email_a = vista.findViewById(R.id.txt_email_a);

        setDatosUser();

        btn_actualizar_datos = vista.findViewById(R.id.btn_actualizar_a);
        btn_actualizar_datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = txt_nom_a.getText().toString();
                apellido = txt_ape_a.getText().toString();
                celular = txt_cel_a.getText().toString();
                correo = txt_email_a.getText().toString();
                password = txt_psw_a.getText().toString();

                Usuario user= new Usuario();
                user.setNombreUsuario(nombre);
                user.setApellidoUsuario(apellido);
                user.setCelularUsuario(celular);
                user.setCorreoUsuario(correo);
                user.setContrasenaUsuario(password);
                user.setCodigoUsuario(codigo);



                Call<UsuarioResponse> call= usuarioApiAdapter.getApiService().updateUser(user);
                call.enqueue(new updateCallback());
            }
        });
        // Inflate the layout for this fragment
        return vista;
    }

    public void obterPreferencias(){
        nomUser = Preferences.obtenerPreferencesString(vista.getContext(), Preferences.PREFERENCES_nombreUsuario);
        ApeUser= Preferences.obtenerPreferencesString(vista.getContext(), Preferences.PREFERENCES_apellidoUsuario);
        emaiuser = Preferences.obtenerPreferencesString(vista.getContext(), Preferences.PREFERENCES_correoUsuario);
        celular_ = Preferences.obtenerPreferencesString(vista.getContext(), Preferences.PREFERENCES_celularUsuario);
        contrasena = Preferences.obtenerPreferencesString(vista.getContext(), Preferences.PREFERENCES_contrasenaUsuario);
        codigo = Preferences.obtenerPreferencesString(vista.getContext(), Preferences.PREFERENCES_codigoUsuario);
    }
    public void cambiarPreferencias(){
        Preferences.savePreferencesString(vista.getContext(), nombre, Preferences.PREFERENCES_nombreUsuario);
        Preferences.savePreferencesString(vista.getContext(), apellido, Preferences.PREFERENCES_apellidoUsuario);
        Preferences.savePreferencesString(vista.getContext(), correo, Preferences.PREFERENCES_correoUsuario);
        Preferences.savePreferencesString(vista.getContext(), celular, Preferences.PREFERENCES_celularUsuario);
        Preferences.savePreferencesString(vista.getContext(), password,Preferences.PREFERENCES_contrasenaUsuario);
    }

    public void setDatosUser(){
        txt_nom_a.setText(nomUser);
        txt_ape_a.setText(ApeUser);
        txt_cel_a.setText(celular_);
        txt_psw_a.setText(contrasena);
        txt_email_a.setText(emaiuser);
    }

    class updateCallback implements Callback<UsuarioResponse>{
        String msgError;
        @Override
        public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
            if(response.isSuccessful()){
                UsuarioResponse usuarioResponse= response.body();
                assert usuarioResponse != null;
                //si estado = 1 -> usuario correctamente añadido
                if(usuarioResponse.getEstado()==1){
                    msgError = usuarioResponse.getMensaje();
                    Toast.makeText(vista.getContext(),msgError,Toast.LENGTH_LONG).show();
                    //actualizamos los datos de shared
                    cambiarPreferencias();
                    obterPreferencias();
                    //mostramos los nuevos datos
                    setDatosUser();
                }else{
                    msgError = usuarioResponse.getMensaje();
                    Toast.makeText(vista.getContext(), msgError, Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(vista.getContext(),"Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<UsuarioResponse> call, Throwable t) {

        }
    }

}