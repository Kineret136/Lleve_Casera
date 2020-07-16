package com.anys.lleve_casera_dv.Adaptadores;

import android.content.Context;
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

import com.anys.lleve_casera_dv.Bean.ProductoMercado;
import com.anys.lleve_casera_dv.Bean.Productos;
import com.anys.lleve_casera_dv.R;

import java.util.ArrayList;
import java.util.Collection;

public class AdaptadorProductoMercado extends RecyclerView.Adapter<AdaptadorProductoMercado.ViewHolder>
    implements OnClickListener, Filterable {
    //Parametros
    Context context;
    LayoutInflater inflater;
    ArrayList<ProductoMercado> productoMercado, listTotProdMerc;

    private View.OnClickListener listener;

    public AdaptadorProductoMercado(Context context, ArrayList<ProductoMercado> productoMercado) {
        this.inflater = LayoutInflater.from(context);
        this.productoMercado = productoMercado;
        this.listTotProdMerc = new ArrayList<>(productoMercado);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //VistaCardView
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_producto_mercado,parent,false);
        ViewHolder viewHolder = new ViewHolder(vista);
        //Evento onClick para que lo muestre en ese fragment
        vista.setOnClickListener(this);
        return  viewHolder;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Filter getFilter() {

        return filter;
    }

    Filter filter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<ProductoMercado> listaFiltrada = new ArrayList<>();

            if (constraint.toString().isEmpty()){
                listaFiltrada.addAll(listTotProdMerc);
            }else {
                for (ProductoMercado todo_prod_merc : listTotProdMerc){
                    String nombres_prod_merc = todo_prod_merc.getNombreMercados().toLowerCase();
                    if (nombres_prod_merc.contains(constraint.toString().toLowerCase())){
                        listaFiltrada.add(todo_prod_merc);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values=listaFiltrada;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productoMercado.clear();
            productoMercado.addAll((Collection<?extends ProductoMercado>)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardviewProductoMercado;
        ImageView imageViewProductoMercado;
        TextView nombreProductoMercado,nombreMercados,precioProductoMercado;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardviewProductoMercado = itemView.findViewById(R.id.cardview_productoMercado);
            imageViewProductoMercado = itemView.findViewById(R.id.imagen_producto_mercado);
            nombreProductoMercado = itemView.findViewById(R.id.nombreProductosMercado);
            precioProductoMercado = itemView.findViewById(R.id.precioProductoMercado);
            nombreMercados = itemView.findViewById(R.id.nombreMercados);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorProductoMercado.ViewHolder holder, int position) {
        holder.nombreProductoMercado.setText(productoMercado.get(position).getNombreProductoMercado());
        holder.precioProductoMercado.setText(productoMercado.get(position).getPrecioProductoMercado()+"");
        holder.nombreMercados.setText(productoMercado.get(position).getNombreMercados());
        holder.imageViewProductoMercado.setImageResource(productoMercado.get(position).getImagen_productoMercado());

    }


    @Override
    public int getItemCount() {
        return productoMercado.size();
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }


}
