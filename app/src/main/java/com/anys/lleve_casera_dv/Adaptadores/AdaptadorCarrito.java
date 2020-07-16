package com.anys.lleve_casera_dv.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.anys.lleve_casera_dv.Bean.Carrito;
import com.anys.lleve_casera_dv.R;

import java.util.ArrayList;

public class AdaptadorCarrito extends RecyclerView.Adapter<AdaptadorCarrito.ViewHolder>
    implements View.OnClickListener{

    Context context;
    LayoutInflater inflater;
    ArrayList<Carrito> pedidos;

    private View.OnClickListener listener;

    public AdaptadorCarrito(Context context, ArrayList<Carrito> pedidos) {
        this.inflater = LayoutInflater.from(context);
        this.pedidos = pedidos;
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


    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardViewPedido;
        ImageView imageViewPedido;
        TextView nombrePedidoCarrito,precioPedidoCarrito,cantidadPedidos;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewPedido = itemView.findViewById(R.id.cardview_pedidos);
            nombrePedidoCarrito = itemView.findViewById(R.id.nombreProductoCarrito);
            precioPedidoCarrito = itemView.findViewById(R.id.precioProductoCarrito);
            cantidadPedidos = itemView.findViewById(R.id.cantidadProductoCarrito);
            imageViewPedido = itemView.findViewById(R.id.imagen_pedido_carrito);

        }
    }


    @Override
    public void onBindViewHolder(@NonNull AdaptadorCarrito.ViewHolder holder, int position) {
        holder.nombrePedidoCarrito.setText(pedidos.get(position).getNombreProdCarrito());
        holder.precioPedidoCarrito.setText(pedidos.get(position).getPrecioProdCarrito()+"");
        holder.cantidadPedidos.setText(pedidos.get(position).getCantidadProdCarrito()+"");
        holder.imageViewPedido.setImageResource(pedidos.get(position).getImagen_pedido());

    }


}
