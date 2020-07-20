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

import com.anys.lleve_casera_dv.Bean.Productos;
import com.anys.lleve_casera_dv.R;
import com.anys.lleve_casera_dv.model.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder>
        implements OnClickListener, Filterable {
    Context context;
    LayoutInflater inflater;
    ArrayList<Producto> productos, listaTotProductos;

    //Listener - Cuando se seleccione un cardView
    private View.OnClickListener listener;


    //Constructor
    public AdaptadorProductos(Context context,ArrayList<Producto> productos){
        this.inflater = LayoutInflater.from(context);
        this.productos = productos;
        this.listaTotProductos = new ArrayList<>(productos);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Definir la vista - Enlaza el adapter con el RecyclerView ( Listar productos)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_productos,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        //Llamamos al evento onClick
        view.setOnClickListener(this);

        return viewHolder;
    }

    //setOnClickListener para implementar en el fragmento
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }



    @Override
    public int getItemCount() {
        return productos.size();
    }

    //Evento OnClickListener
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

        //Ejecuta en el background
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Producto> listaFiltrada = new ArrayList<>();

            if (charSequence.toString().isEmpty()){
                listaFiltrada.addAll(listaTotProductos);
            }else {
                for (Producto todo_producto : listaTotProductos){
                    String nombre_producto = todo_producto.getNombreProducto().toLowerCase();

                    if (nombre_producto.contains(charSequence.toString().toLowerCase())){
                        listaFiltrada.add(todo_producto);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listaFiltrada;
            return filterResults;
        }

        //Ejecuta en la UI
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            productos.clear();
            productos.addAll((Collection<? extends Producto>) filterResults.values);
            notifyDataSetChanged();
        }
    };



    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imagenView;
        TextView nombreproducto,precioproducto,mercadoproducto,distritomercado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardview_producto);
            imagenView = itemView.findViewById(R.id.imagen_producto);
            nombreproducto = itemView.findViewById(R.id.nombreProducto);
            precioproducto = itemView.findViewById(R.id.precioProducto);
            mercadoproducto = itemView.findViewById(R.id.nombreMercado);
            distritomercado = itemView.findViewById(R.id.nombreDistrito);

        }

    }

    //Mantiene la vista
    @Override
    public void onBindViewHolder(@NonNull AdaptadorProductos.ViewHolder holder, int position) {
        String nomImg = productos.get(position).getNombreProducto();
        String url = "https://smipmec.000webhostapp.com/Proyecto/LleveCasera/recursos/img/producto/"+nomImg+".jpg";
        holder.nombreproducto.setText(productos.get(position).getNombreProducto());
        holder.precioproducto.setText(productos.get(position).getPrecioProductoM());
        holder.mercadoproducto.setText(productos.get(position).getNombreMercado());
        holder.distritomercado.setText(productos.get(position).getDistritoMercado());
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_productos_default)
                .error(R.drawable.ic_productos_default)
                .into(holder.imagenView);
    }

}
