package com.anys.lleve_casera_dv.model;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("codigoUsuario")
    private String codigoUsuario;
    @SerializedName("nombreUsuario")
    private String nombreUsuario;
    @SerializedName("apellidoUsuario")
    private String apellidoUsuario;
    @SerializedName("celularUsuario")
    private String celularUsuario;
    @SerializedName("correoUsuario")
    private String correoUsuario;
    @SerializedName("contrasenaUsuario")
    private String contrasenaUsuario;

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getCelularUsuario() {
        return celularUsuario;
    }

    public void setCelularUsuario(String celularUsuario) {
        this.celularUsuario = celularUsuario;
    }


    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }
}
