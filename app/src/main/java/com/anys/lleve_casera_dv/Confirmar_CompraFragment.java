package com.anys.lleve_casera_dv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class Confirmar_CompraFragment extends Fragment {

    Button bt_recojo,bt_envio,bt_agencia;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_confirmar_compra, container, false);

        bt_recojo = vista.findViewById(R.id.btn_recojoPresencial);
        bt_agencia = vista.findViewById(R.id.btn_agenciaEnvio);
        bt_envio = vista.findViewById(R.id.btn_agenciaEnvio);

        bt_recojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Se está procesando su solicitud",Toast.LENGTH_LONG).show();
            }
        });
        bt_agencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Se está procesando su solicitud",Toast.LENGTH_LONG).show();
            }
        });
        bt_envio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Se está procesando su solicitud",Toast.LENGTH_LONG).show();
            }
        });




        return vista;


    }
}