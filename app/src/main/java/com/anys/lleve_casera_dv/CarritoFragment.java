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
import com.anys.lleve_casera_dv.model.Compra;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import static com.anys.lleve_casera_dv.CantProductoFragment2.compras;

public class CarritoFragment extends Fragment {

    AdaptadorCarrito adaptadorCarrito;
    RecyclerView recyclerViewCarrito;
    ArrayList<Compra> listPedidos;
    TextView textCarrito, totalPedido;
    Button bt_confirmar , btn_cancelar;
    double suma= 0;

    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Carrito de Compra");
        //Ver la conexion
        Conexion.conexInter(getContext());

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
           Iterator<Compra> it= compras.iterator();

           double total = 0;

           while(it.hasNext()){
               Compra item=it.next();
               double precio = item.getPrecioProducto();
               int cant= item.getCantidadProducto();
               double subtotal= precioTotal(cant,precio);
               total= precioFinal(subtotal);


           }
            totalPedido.setText(df.format(total)+"");
           final double finalTotal = total;


           bt_confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Obtener el total de la compra para insertar en el endpoint CompraUsuario
                    Bundle tot = new Bundle();
                    tot.putDouble("precioTotal", finalTotal);
                    Navigation.findNavController(v).navigate(R.id.agenciaSelectFragment,tot);

                }
            });
        }
        return vista;
    }

    public double precioTotal(int cantidadProducto, double precioProducto){
        return cantidadProducto*precioProducto;
    }

    public double precioFinal(double precioTotal){
        suma = suma +precioTotal;
        return suma;
    }

    private void mostrarListaProd() {
        recyclerViewCarrito.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorCarrito = new AdaptadorCarrito(getContext(),compras);
        recyclerViewCarrito.setAdapter(adaptadorCarrito);
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