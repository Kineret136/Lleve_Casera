package com.anys.lleve_casera_dv;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.anys.lleve_casera_dv.Adaptadores.AdaptadorProductoMercado;
import com.anys.lleve_casera_dv.Bean.ProductoMercado;

import java.util.ArrayList;

public class ProductoMercadoFragment extends Fragment {

    AdaptadorProductoMercado adaptadorProductoMercado;
    RecyclerView recyclerViewProductoMercado;
    ArrayList<ProductoMercado> listProductoMercado;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_producto_mercado, container, false);
        recyclerViewProductoMercado = vista.findViewById(R.id.recyclerProductoMercado);
        listProductoMercado = new ArrayList<>();

        //Metodos para cargar la lista de elementos y mostrar los elementos
        cargarElementos();
        mostrarElementos();

        return vista;
    }

    private void cargarElementos() {
        listProductoMercado.add(new ProductoMercado(1,"Arroz",1.25,"Unicachi",R.drawable.arroz_costeno));
        listProductoMercado.add(new ProductoMercado(2,"Arroz",2.40,"El Hueco",R.drawable.arroz_hoja));
        listProductoMercado.add(new ProductoMercado(3,"Arroz",4.20,"La Parada",R.drawable.arroz_hoja));
        listProductoMercado.add(new ProductoMercado(4,"Arroz",2.10,"Abancay",R.drawable.arroz_hoja));
        listProductoMercado.add(new ProductoMercado(5,"Arroz",1.90,"Caquetá",R.drawable.arroz_costeno));
        listProductoMercado.add(new ProductoMercado(6,"Arroz",2.00,"Ciudad de Dios",R.drawable.arroz_costeno));
        listProductoMercado.add(new ProductoMercado(7,"Arroz",2.20,"Jesus Maria",R.drawable.arroz_costeno));
        listProductoMercado.add(new ProductoMercado(8,"Arroz",2.30,"Mercado Surquillo",R.drawable.arroz_hoja));
        listProductoMercado.add(new ProductoMercado(9,"Arroz",1.40,"Mercado de Frutas",R.drawable.arroz_hoja));
        listProductoMercado.add(new ProductoMercado(10,"Arroz",3.00,"Mercado Central",R.drawable.arroz_costeno));
        listProductoMercado.add(new ProductoMercado(11,"Arroz",1.80,"Unicachi",R.drawable.arroz_hoja));
        listProductoMercado.add(new ProductoMercado(12,"Arroz",1.50,"Unicachi",R.drawable.arroz_costeno));
    }

    private void mostrarElementos() {
        recyclerViewProductoMercado.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorProductoMercado = new AdaptadorProductoMercado(getContext(),listProductoMercado);
        recyclerViewProductoMercado.setAdapter(adaptadorProductoMercado);

        adaptadorProductoMercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //La accion a realizar cuando se seleccione un elemento de la lista

                Navigation.findNavController(v).navigate(R.id.elementos_mercadoFragment);

            }
        });

    }
    @NonNull
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.principal,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adaptadorProductoMercado.getFilter().filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}