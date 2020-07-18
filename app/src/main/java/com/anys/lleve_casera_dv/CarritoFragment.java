package com.anys.lleve_casera_dv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.anys.lleve_casera_dv.Adaptadores.AdaptadorCarrito;
import com.anys.lleve_casera_dv.model.Compra;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.anys.lleve_casera_dv.Adaptadores.AdaptadorCarrito.sumaTotal;
import static com.anys.lleve_casera_dv.CantProductoFragment2.compras;

public class CarritoFragment extends Fragment {

    AdaptadorCarrito adaptadorCarrito;
    RecyclerView recyclerViewCarrito;
    ArrayList<Compra> listPedidos;
    TextView textCarrito, totalPedido;
    Button bt_confirmar , btn_cancelar;


    DecimalFormat df = new DecimalFormat("#.##");

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
        //listPedidos = new ArrayList<>();
        textCarrito= vista.findViewById(R.id.textCarrito);
        totalPedido = vista.findViewById(R.id.totalPedido);
        bt_confirmar = vista.findViewById(R.id.btn_comprar);

       if (compras.isEmpty()){
            textCarrito.setText("¡Aun no ha agregado ningun producto al carrito!");
        }else {
            textCarrito.setText("Lista de productos");
            mostrarListaProd();
            totalPedido.setText(df.format(sumaTotal)+"");
            bt_confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.agenciaSelectFragment);
                }
            });
        }
        return vista;
    }

    private void mostrarListaProd() {
        recyclerViewCarrito.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorCarrito = new AdaptadorCarrito(getContext(),compras);
        recyclerViewCarrito.setAdapter(adaptadorCarrito);
        Log.d("TotalSumaTotal", sumaTotal+"");
        //totalPedido.setText(df.format(traerPrecio())+"");
    }

    @Override
    public void onStart() {
        super.onStart();
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Carrito de Compra");
    }

    @Override
    public void onResume() {
        super.onResume();
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Carrito de Compra");
    }

}