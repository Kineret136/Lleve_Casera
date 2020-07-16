package com.anys.lleve_casera_dv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CantProductoFragment2 extends DialogFragment {

    public static final String Nombre= "Cant";
    private String nombre;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        nombre = getArguments().getString(Nombre, "Algo salió mal");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_cant_producto2,null);
        final EditText cant= view.findViewById(R.id.cantidadProducto);
        final String[] cantidad = new String[1];

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setTitle("Ingrese cantidad")
                .setPositiveButton("Agregar al carrito", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cantidad[0] =cant.getText().toString();
                        Toast.makeText(getActivity(), "Se agregó "+ cantidad[0] + " unidades de " +nombre, Toast.LENGTH_SHORT).show();
                    }
                })
                . setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Se cancaló la operación ", Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }


}