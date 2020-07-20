package com.anys.lleve_casera_dv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.anys.lleve_casera_dv.model.Compra;

import java.util.ArrayList;
import java.util.Iterator;


public class CantProductoFragment2 extends DialogFragment {

    public static final String Nombre= "Cant";
    private String nombreProducto;
    private int idProducto;
    private double preProducto;
    public static ArrayList<Compra> compras = new ArrayList<>();
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        nombreProducto = getArguments().getString(Nombre, "Algo salió mal");
        idProducto=getArguments().getInt("idProducto");
        preProducto= getArguments().getDouble("precioProducto");

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_cant_producto2,null);
        final EditText cant= view.findViewById(R.id.cantidadProducto);
        final String[] cantidad = new String[1];
        final int[] cantida = new int[1];

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setTitle("Ingrese cantidad")
                .setPositiveButton("Agregar al carrito", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cantidad[0] =cant.getText().toString();
                        if(!cantidad[0].equalsIgnoreCase("")){
                            cantida[0] =Integer.parseInt(cant.getText().toString());
                            compras.add(new Compra(idProducto,nombreProducto,cantida[0],preProducto));
                            Iterator<Compra> nombreIterator = compras.iterator();
                            while(nombreIterator.hasNext()){
                                Compra elemento = nombreIterator.next();
                                Log.d("PedidoAgregado", elemento.toString()+" / " );
                            }
                            Toast.makeText(getActivity(), "Se agregó "+ cantidad[0] + " unidades de " +nombreProducto, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "Ingrese la cantidad que desea comprar " , Toast.LENGTH_SHORT).show();
                        }

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