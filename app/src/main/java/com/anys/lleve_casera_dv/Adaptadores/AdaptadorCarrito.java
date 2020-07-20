package com.anys.lleve_casera_dv.Adaptadores;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.anys.lleve_casera_dv.CarritoFragment;
import com.anys.lleve_casera_dv.R;
import com.anys.lleve_casera_dv.model.Compra;
import com.anys.lleve_casera_dv.model.ProductosXMercado;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

import static com.anys.lleve_casera_dv.CantProductoFragment2.compras;

public class AdaptadorCarrito extends RecyclerView.Adapter<AdaptadorCarrito.ViewHolder>
    implements View.OnClickListener, Filterable {

    Context context;
    LayoutInflater inflater;
    ArrayList<Compra> pedidos, listaTotPedido;
    private View.OnClickListener listener;
    public AdaptadorCarrito(Context context, ArrayList<Compra> pedidos) {
        this.inflater = LayoutInflater.from(context);
        this.pedidos = pedidos;
        this.listaTotPedido = new ArrayList<>(pedidos);
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    @NonNull
    @Override
    public AdaptadorCarrito.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_pedidos,parent,false);
        ViewHolder viewHolderPedido = new ViewHolder(view);
        //Mostrar fragmento
        view.setOnClickListener(this);
        return viewHolderPedido;
    }



    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        //Ejecuta en el background
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Compra> listaFiltrada = new ArrayList<>();

            if (charSequence.toString().isEmpty()){
                listaFiltrada.addAll(listaTotPedido);
            }else {
                for (Compra todo_elem_mercado : listaTotPedido){
                    String nombres_productos = todo_elem_mercado.getNombrePrducto().toLowerCase();

                    if (nombres_productos.contains(charSequence.toString().toLowerCase())){
                        listaFiltrada.add(todo_elem_mercado);
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
            compras.clear();
            compras.addAll((Collection<? extends Compra>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewPedido;
        ImageView imageViewPedido;
        TextView nombrePedidoCarrito,precioPedidoCarrito,cantidadPedidos,precioTotal;
        ImageButton eliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewPedido = itemView.findViewById(R.id.cardview_pedidos);
            nombrePedidoCarrito = itemView.findViewById(R.id.nombreProductoCarrito);
            precioPedidoCarrito = itemView.findViewById(R.id.precioProductoCarrito);
            cantidadPedidos = itemView.findViewById(R.id.cantidadProductoCarrito);
            imageViewPedido = itemView.findViewById(R.id.imagen_pedido_carrito);
            precioTotal=itemView.findViewById(R.id.totalCantPrecio);
            eliminar=itemView.findViewById(R.id.eliminarProducto);
        }
    }

    public double precioTotal(int cantidadProducto, double precioProducto){
        return cantidadProducto*precioProducto;
    }


    @Override
    public void onBindViewHolder(@NonNull AdaptadorCarrito.ViewHolder holder, final int position) {
        int cantidad= pedidos.get(position).getCantidadProducto();
        double precio= pedidos.get(position).getPrecioProducto();
        DecimalFormat df = new DecimalFormat("#.##");
        double precioTotal= precioTotal(cantidad,precio);

        String nomImg = pedidos.get(position).getNombrePrducto();
        String url = "https://smipmec.000webhostapp.com/Proyecto/LleveCasera/recursos/img/producto/"+nomImg+".jpg";
        holder.nombrePedidoCarrito.setText(pedidos.get(position).getNombrePrducto());
        holder.precioPedidoCarrito.setText(pedidos.get(position).getPrecioProducto()+"");
        holder.cantidadPedidos.setText(pedidos.get(position).getCantidadProducto()+"");
        holder.precioTotal.setText(df.format(precioTotal)+"");
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidos.remove(pedidos.get(position));
                Navigation.findNavController(view).navigate(R.id.carritoFragment);
            }
        });
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_producto)
                .error(R.drawable.ic_producto)
                .into(holder.imageViewPedido);
        return;
    }


}
