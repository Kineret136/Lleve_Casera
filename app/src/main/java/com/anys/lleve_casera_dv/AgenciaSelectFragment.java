package com.anys.lleve_casera_dv;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anys.lleve_casera_dv.model.Pedidos;

import java.sql.Date;
import java.util.ArrayList;

public class AgenciaSelectFragment extends Fragment {

    CardView cv_ubereats,cv_glovo,cv_rappi,cv_envioDomicilio;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Selecciona Agencia");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_agencia_select, container, false);
        cv_ubereats = vista.findViewById(R.id.cv_ubereats);
        cv_glovo= vista.findViewById(R.id.cv_glovo);
        cv_rappi= vista.findViewById(R.id.cv_rappi);
        cv_envioDomicilio= vista.findViewById(R.id.cv_envioDomicilio);

        final String codigoUsuario= Preferences.obtenerPreferencesString(getActivity(),Preferences.PREFERENCES_codigoUsuario);
        final Pedidos pedidos = new Pedidos();
        final Date fechaCompra;
        final double PrecioTotal;

        cv_ubereats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidos.setCodigoAgencia(2);
                pedidos.setCodigoUsuario(codigoUsuario);
                //pedidos.setFechaCompra(fechaCompra);
                //pedidos.setPrecioTotal(PrecioTotal);



            }
        });
        cv_glovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        cv_rappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        cv_envioDomicilio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        // Inflate the layout for this fragment

        return vista;
    }
}