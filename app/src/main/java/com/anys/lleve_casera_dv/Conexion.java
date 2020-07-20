package com.anys.lleve_casera_dv;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;

public class Conexion {
     public static boolean conexInter(Context con){
        //Para verificar conexion a internet
        ConnectivityManager connectivityManager = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
        //Obtener información sobre si está activada la red o no
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //Comprobar
        if (networkInfo==null||!networkInfo.isConnected()||!networkInfo.isAvailable()){
            //Cuando internet no está activado se muestra el cuadro fragment_conexion
            final Dialog dialog = new Dialog(con);
            dialog.setContentView(R.layout.fragment_conexion);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
            //función al botón
            Button boton_refresh = dialog.findViewById(R.id.btn_refresh);
            boton_refresh.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
            //mostrar el fragment
            dialog.show();
        }else {
            //Cuando hay conectividad retorna true
            return true;
        }//False : no hay conexion a internet
        return false;
    }


}
