package com.anys.lleve_casera_dv;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    public static final String STRING_PREFERENCES = "app.lleve_casera_dv";
    public static final String PREFERENCES_ESTADO_BUTTON_SESION = "estado_boton_login";
    public static final String PREFERENCES_codigoUsuario = "codigoUsuario";
    public static final String PREFERENCES_nombreUsuario = "nombreUsuario";
    public static final String PREFERENCES_apellidoUsuario = "apellidoUsuario";
    public static final String PREFERENCES_celularUsuario = "celularUsuario";
    public static final String PREFERENCES_correoUsuario = "correoUsuario";
    public static final String PREFERENCES_contrasenaUsuario = "contrasenaUsuario";

    //para guardar y para cambiar
    public static void savePreferencesBoolean(Context c, boolean b, String key){
        SharedPreferences preferences= c.getSharedPreferences(STRING_PREFERENCES, c.MODE_PRIVATE);
        preferences.edit().putBoolean(key, b).apply();
    }

    public static void savePreferencesString(Context c, String b, String key){
        SharedPreferences preferences= c.getSharedPreferences(STRING_PREFERENCES, c.MODE_PRIVATE);
        preferences.edit().putString(key, b).apply();
    }
    public static void savePreferencesInt(Context c, int b, String key){
        SharedPreferences preferences= c.getSharedPreferences(STRING_PREFERENCES, c.MODE_PRIVATE);
        preferences.edit().putInt(key, b).apply();
    }

    //obtener el estado del boton
    public static boolean obtenerPreferencesBoolean(Context c, String key){
        SharedPreferences preferences= c.getSharedPreferences(STRING_PREFERENCES, c.MODE_PRIVATE);
        return preferences.getBoolean(key, false); // si es que nunca se ha guardado nada en esta key, retorna false
    }
    public static String obtenerPreferencesString(Context c, String key){
        SharedPreferences preferences= c.getSharedPreferences(STRING_PREFERENCES, c.MODE_PRIVATE);
        return preferences.getString(key, ""); // si es que nunca se ha guardado nada en esta key, retorna cadena vacia
    }
    public static int obtenerPreferencesInt(Context c, String key){
        SharedPreferences preferences= c.getSharedPreferences(STRING_PREFERENCES, c.MODE_PRIVATE);
        return preferences.getInt(key, 0); // si es que nunca se ha guardado nada en esta key, retorna 0
    }
}
