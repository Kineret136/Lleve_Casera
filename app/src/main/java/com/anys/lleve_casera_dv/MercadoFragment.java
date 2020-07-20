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


import com.anys.lleve_casera_dv.Adaptadores.AdaptadorMercados;
import com.anys.lleve_casera_dv.io.mercadoApiAdapter;
import com.anys.lleve_casera_dv.io.response.MercadosResponse;
import com.anys.lleve_casera_dv.model.Mercado;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MercadoFragment extends Fragment {
    AdaptadorMercados adaptadorMercados;
    RecyclerView recyclerViewMercados;
    ArrayList<Mercado> listMercados;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Mercados");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewVista = inflater.inflate(R.layout.fragment_mercado, container, false);
        recyclerViewMercados = viewVista.findViewById(R.id.recyclerMercado);

        if (Conexion.conexInter(getContext())==true){
            //Cargar Lista
            cargarListaMercados();
        }


        return viewVista;
    }

    public void cargarListaMercados(){

        Call<MercadosResponse> call = mercadoApiAdapter.getApiService().getAllMercado();
        call.enqueue(new mercadosCallback());

    }
    class mercadosCallback implements Callback<MercadosResponse>{

        @Override
        public void onResponse(Call<MercadosResponse> call, Response<MercadosResponse> response) {
            if(response.isSuccessful()){
                MercadosResponse mercadosResponse= response.body();
                if(mercadosResponse.getEstado() == 1){
                    listMercados = mercadosResponse.getMercados();
                    mostrarElem();
                }
            }else{
                Toast.makeText(getContext(), "Error en el formato de respuesta", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<MercadosResponse> call, Throwable t) {

        }
    }

    public void mostrarElem(){
        recyclerViewMercados.setHasFixedSize(true);
        recyclerViewMercados.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorMercados = new AdaptadorMercados(getContext(),listMercados);
        recyclerViewMercados.setAdapter(adaptadorMercados);
        adaptadorMercados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Enviar 2 parametros: nombre del Mercado y el id del mercado
                //El NOMBRE del MERCADO es para mostrarlo en la barra superior, x ejemplo: Productos del Mercado Unicachi
                // el ID del MERCADO es un parametro necesario para el consumo del endpoint BuscarProductomercado

                //Primero extraemos los datos del adapter
                int codigoProductoM = listMercados.get(recyclerViewMercados.getChildAdapterPosition(v)).getCodigoMercado();
                String nombreMercado = listMercados.get(recyclerViewMercados.getChildAdapterPosition(v)).getNombreMercado();

                //Usamos Bundle para enviar los datos al ElementosMercadoFragment.java
                Bundle mercadoId = new Bundle();
                mercadoId.putInt("codigoProductoM", codigoProductoM);
                mercadoId.putString("nombreMercado", nombreMercado);

                //infamos la vista con el fragment de ElementosMercadoFragment.java


                Navigation.findNavController(v).navigate(R.id.elementos_mercadoFragment, mercadoId );
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
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
                adaptadorMercados.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}