package com.anys.lleve_casera_dv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anys.lleve_casera_dv.Adaptadores.AdaptadorCarrito;
import com.anys.lleve_casera_dv.Adaptadores.AdaptadorMercados;
import com.anys.lleve_casera_dv.Bean.Carrito;

import java.util.ArrayList;

public class CarritoFragment extends Fragment {

    AdaptadorCarrito adaptadorCarrito;
    RecyclerView recyclerViewCarrito;
    ArrayList<Carrito> listPedidos;

    Button bt_confirmar , btn_cancelar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Carrito de Compra");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_carrito, container, false);
        recyclerViewCarrito = vista.findViewById(R.id.recyclerCarrito);
        listPedidos = new ArrayList<>();

        bt_confirmar = vista.findViewById(R.id.btn_comprar);

        bt_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.confirmar_compraFragment);
            }
        });




        datosListaProd();
        mostrarListaProd();


        return vista;
    }

    private void datosListaProd() {
        listPedidos.add(new Carrito(1,"Arroz Hoja",1.20,3,R.drawable.arroz_hoja));
        listPedidos.add(new Carrito(2,"Chorizo Otto Kun",4.20,2,R.drawable.chorizo_ottok));
        listPedidos.add(new Carrito(3,"Azúcar Rubia",1.70,4,R.drawable.azucar_rubia));
        listPedidos.add(new Carrito(4,"Arroz Hoja",2.30,2,R.drawable.arroz_hoja));
    }

    private void mostrarListaProd() {
        recyclerViewCarrito.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorCarrito = new AdaptadorCarrito(getContext(),listPedidos);
        recyclerViewCarrito.setAdapter(adaptadorCarrito);

    }


}