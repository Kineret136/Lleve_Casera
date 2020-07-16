package com.anys.lleve_casera_dv.Adaptadores;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.anys.lleve_casera_dv.Bean.Mercados;
import com.anys.lleve_casera_dv.R;
import com.anys.lleve_casera_dv.model.Mercado;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

public class AdaptadorMercados extends RecyclerView.Adapter<AdaptadorMercados.ViewHolder>
        implements OnClickListener, Filterable {
    //Parametros
    LayoutInflater inflater;
    ArrayList<Mercado> mercados,listaTotMercados;

    //Listener
    private View.OnClickListener listener;

    /*Constructor*/
    public AdaptadorMercados(Context context, ArrayList<Mercado> mercados) {
        this.inflater = LayoutInflater.from(context);
        this.mercados = mercados;
        this.listaTotMercados = new ArrayList<>(mercados);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Vista del CardView de los mercados + Enlace con adaptador del mercado
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_mercado,parent,false);
        ViewHolder vhMercado = new ViewHolder(vista);
        //Evento onClick para que lo muestre en ese fragment
        vista.setOnClickListener(this);
        return  vhMercado;
    }

    //Implementamos el fragmento
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }



    //Muestra los items según el tamaño del arrayList Mercados
    @Override
    public int getItemCount() {
        return mercados.size();
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Mercado> listaFiltrada = new ArrayList<>();

            if (charSequence.toString().isEmpty()){
                listaFiltrada.addAll(listaTotMercados);
            }else {
                for (Mercado todo_mercado : listaTotMercados){
                    String nombre_mercado = todo_mercado.getNombreMercado().toLowerCase();

                    if (nombre_mercado.contains(charSequence.toString().toLowerCase())){
                        listaFiltrada.add(todo_mercado);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mercados.clear();
            mercados.addAll((Collection<?extends Mercado>)results.values);
            notifyDataSetChanged();
        }
    };



    //Llamamos a los elementos de la clase Mercados
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewMercado;
        ImageView imageViewMercado;
        TextView nombremercado,distritomercado,provinciamercado,regionMercado,celularmercado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewMercado = itemView.findViewById(R.id.cardview_mercado);
            imageViewMercado = itemView.findViewById(R.id.imagen_mercado);
            nombremercado = itemView.findViewById(R.id.nombreMercado);
            distritomercado = itemView.findViewById(R.id.nombreDistrito);
            provinciamercado = itemView.findViewById(R.id.nombreProvicia);
            regionMercado = itemView.findViewById(R.id.nombreRegion);
            celularmercado = itemView.findViewById(R.id.nroCelularMercado);
        }
    }
    /*Mantiene la vista*/
    @Override
    public void onBindViewHolder(@NonNull AdaptadorMercados.ViewHolder holder, int position) {
        //Colocando valores de al cardw
        int id = mercados.get(position).getCodigoMercado();
        String url= "https://smipmec.000webhostapp.com/Proyecto/LleveCasera/recursos/img/mercado/"+id+".png";
        holder.nombremercado.setText(mercados.get(position).getNombreMercado());
        holder.distritomercado.setText(mercados.get(position).getDistritoMercado());
        holder.provinciamercado.setText(mercados.get(position).getProvinciaMercado());
        holder.regionMercado.setText(mercados.get(position).getRegionMercado());
        holder.celularmercado.setText(mercados.get(position).getCelularMercado());
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_mercado)
                .error(R.drawable.ic_mercado)
                .into(holder.imageViewMercado);

    }
}
