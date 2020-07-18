package com.anys.lleve_casera_dv;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anys.lleve_casera_dv.Adaptadores.AdaptadorElementosMercado;
import com.anys.lleve_casera_dv.io.mercadoApiAdapter;
import com.anys.lleve_casera_dv.io.response.ProductosXMercadoResponse;
import com.anys.lleve_casera_dv.model.ProductosXMercado;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ElementosMercadoFragment extends Fragment{
    AdaptadorElementosMercado adaptadorElementosMercado;
    RecyclerView recyclerViewElemMerc;
    ArrayList<ProductosXMercado> listElemMerc;
    DialogFragment cantidadProducto = new CantProductoFragment2();
    String nomProduct="";
    int idProducto;
    double precioProducto;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        String nombreMercado= getArguments().getString("nombreMercado");
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Productos de "+nombreMercado);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_elementos_mercado, container, false);
        recyclerViewElemMerc = vista.findViewById(R.id.recyclerElementosMercado);
        listElemMerc = new ArrayList<>();

        //Cargamos los elementos
        cargarListaElementosMercado();
        return vista;
    }

    private void cargarListaElementosMercado() {

        //llamamos a los argumentos guardados en el Bundle segun su key
        int mercado = getArguments().getInt("codigoProductoM");
        Call<ProductosXMercadoResponse> call = mercadoApiAdapter.getApiService().getProductosXMercado(mercado);
        call.enqueue(new productosXMercadoCallback());

    }

    public void mostrarElemMercado() {
        recyclerViewElemMerc.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorElementosMercado = new AdaptadorElementosMercado(getContext(),listElemMerc);
        recyclerViewElemMerc.setAdapter(adaptadorElementosMercado);

        adaptadorElementosMercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Aqui se coloca
                * Navigation.findNavController(v).navigate(R.id.ID_DE_LA_VISTA_PARA_AÑADIR_PRODUCTOS_AL_CARRITO);
                * */
                nomProduct = listElemMerc.get(recyclerViewElemMerc.getChildAdapterPosition(v)).getProducto();
                idProducto = listElemMerc.get(recyclerViewElemMerc.getChildAdapterPosition(v)).getId();
                precioProducto = listElemMerc.get(recyclerViewElemMerc.getChildAdapterPosition(v)).getPrecio();


                cantidadProducto.show(getFragmentManager(), "cantidadProducto");
                Bundle bundle = new Bundle();
                bundle.putString(CantProductoFragment2.Nombre, ""+ nomProduct);

                bundle.putInt("idProducto", idProducto);
                bundle.putDouble("precioProducto",precioProducto);
                cantidadProducto.setArguments(bundle);

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.principal, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adaptadorElementosMercado.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }

    private class productosXMercadoCallback implements Callback<ProductosXMercadoResponse> {
        @Override
        public void onResponse(Call<ProductosXMercadoResponse> call, Response<ProductosXMercadoResponse> response) {
            if(response.isSuccessful()){
                ProductosXMercadoResponse productosXMercadoResponse= response.body();
                if(productosXMercadoResponse.getEstado() == 1){
                    listElemMerc = productosXMercadoResponse.getProductosXMercados();
                    mostrarElemMercado();
                }
            }else{
                Toast.makeText(getContext(), "Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ProductosXMercadoResponse> call, Throwable t) {

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Productos");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}