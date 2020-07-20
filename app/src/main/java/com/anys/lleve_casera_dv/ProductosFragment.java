package com.anys.lleve_casera_dv;

import android.os.Bundle;

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
import android.widget.TextView;
import android.widget.Toast;

import com.anys.lleve_casera_dv.Adaptadores.AdaptadorProductos;
import com.anys.lleve_casera_dv.Bean.Productos;
import com.anys.lleve_casera_dv.io.productoApiAdapter;
import com.anys.lleve_casera_dv.io.response.ProductosResponse;
import com.anys.lleve_casera_dv.model.Producto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductosFragment extends Fragment {
    AdaptadorProductos adaptadorProductos;
    RecyclerView recyclerViewProductos;
    ArrayList<Producto> listProductos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Productos");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_productos, container, false);
        recyclerViewProductos = view.findViewById(R.id.recyclerview_producto);

        if (Conexion.conexInter(getContext())==true){
            //Cargar la List
            cargarListaProductos();
        }
        return view;
    }

    public void cargarListaProductos(){
        Call<ProductosResponse> call = productoApiAdapter.getApiService().getAllProducto();
        call.enqueue(new productosCallback());

    }

        class productosCallback implements Callback<ProductosResponse>{

            @Override
            public void onResponse(Call<ProductosResponse> call, Response<ProductosResponse> response) {
                if (response.isSuccessful()){
                    ProductosResponse productosResponse = response.body();
                    if(productosResponse.getEstado() == 1){
                        listProductos = productosResponse.getProductos();
                        mostrarElementos();
                    }
                }else{
                    Toast.makeText(getContext(), "Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductosResponse> call, Throwable t) {

            }
        }


    public void mostrarElementos(){
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorProductos = new AdaptadorProductos(getContext(),listProductos);
        recyclerViewProductos.setAdapter(adaptadorProductos);

        adaptadorProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int codigoProductoM = listProductos.get(recyclerViewProductos.getChildAdapterPosition(v)).getCodigoMercado();
                String nombreMercado = listProductos.get(recyclerViewProductos.getChildAdapterPosition(v)).getNombreMercado();

                //Usamos Bundle para enviar los datos al ElementosMercadoFragment.java
                Bundle mercadoId = new Bundle();
                mercadoId.putInt("codigoProductoM", codigoProductoM);
                mercadoId.putString("nombreMercado", nombreMercado);
                /*Prueba
                String mostrarNomProd = listProductos.get(recyclerViewProductos.getChildAdapterPosition(v)).getNombreProd();
                Toast.makeText(getContext(),""+mostrarNomProd,Toast.LENGTH_SHORT).show();*/
                Navigation.findNavController(v).navigate(R.id.elementos_mercadoFragment,mercadoId);
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
                adaptadorProductos.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);

    }
}