package com.anys.lleve_casera_dv;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.anys.lleve_casera_dv.io.compraApiAdapter;
import com.anys.lleve_casera_dv.io.response.ComprasResponse;
import com.anys.lleve_casera_dv.io.response.DetalleCompraResponse;
import com.anys.lleve_casera_dv.model.Compra;
import com.anys.lleve_casera_dv.model.DetallePedido;
import com.anys.lleve_casera_dv.model.Pedidos;
import com.anys.lleve_casera_dv.model.RecursoId;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.anys.lleve_casera_dv.CantProductoFragment2.compras;

public class AgenciaSelectFragment extends Fragment {

    CardView cv_ubereats,cv_glovo,cv_rappi,cv_envioDomicilio;
    Button bt_recojo;
    int estadoCompra= 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView toolbar = getActivity().findViewById(R.id.toolbar_title);
        toolbar.setText("Selecciona Agencia");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_agencia_select, container, false);
        cv_ubereats = vista.findViewById(R.id.cv_ubereats);
        cv_glovo= vista.findViewById(R.id.cv_glovo);
        cv_rappi= vista.findViewById(R.id.cv_rappi);
        cv_envioDomicilio= vista.findViewById(R.id.cv_envioDomicilio);

        bt_recojo = vista.findViewById(R.id.btn_recojoPresencial);
        bt_recojo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.fragment_recojo);
                dialog.setCanceledOnTouchOutside(false);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                //función al botón
                Button boton_finish = dialog.findViewById(R.id.btn_cerrarDialog);
                boton_finish.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                        compras.clear();
                    }
                });
                //mostrar el fragment
                dialog.show();
            }
        });

        final String codigoUsuario= Preferences.obtenerPreferencesString(getActivity(),Preferences.PREFERENCES_codigoUsuario);
        final Pedidos pedidos = new Pedidos();
        //final Date fechaCompra;
        final Calendar c = Calendar.getInstance();
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String fechaCompra = df.format(c.getTime());

        //llamar al precio total de la compra
        final double precioTotal = getArguments().getDouble("precioTotal");



        cv_ubereats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidos.setCodigoAgencia(2);
                pedidos.setCodigoUsuario(codigoUsuario);
                pedidos.setFechaCompra(fechaCompra);
                pedidos.setPrecioTotal(precioTotal);

                cerrarProceso(view, pedidos);


            }
        });
        cv_glovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidos.setCodigoAgencia(1);
                pedidos.setCodigoUsuario(codigoUsuario);
                pedidos.setFechaCompra(fechaCompra);
                pedidos.setPrecioTotal(precioTotal);
                cerrarProceso(view, pedidos);
            }
        });
        cv_rappi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidos.setCodigoAgencia(3);
                pedidos.setCodigoUsuario(codigoUsuario);
                pedidos.setFechaCompra(fechaCompra);
                pedidos.setPrecioTotal(precioTotal);
                cerrarProceso(view, pedidos);
            }
        });
        cv_envioDomicilio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedidos.setCodigoAgencia(4);
                pedidos.setCodigoUsuario(codigoUsuario);
                pedidos.setFechaCompra(fechaCompra);
                try {
                    pedidos.setPrecioTotal(precioTotal);
                    cerrarProceso(view, pedidos);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        // Inflate the layout for this fragment

        return vista;
    }

    class pedidoCallBack implements Callback<ComprasResponse>{
        String msjError;
        int codigoCompra;

        @Override
        public void onResponse(Call<ComprasResponse> call, Response<ComprasResponse> response) {
            if (response.isSuccessful()){
                ComprasResponse comprasResponse = response.body();
                assert comprasResponse != null;
                if (comprasResponse.getEstado() == 1){ //Se registró la compra
                    msjError = comprasResponse.getMensaje();
                    //obtenemos el id de la compra
                    codigoCompra = obtenerIdUltimaCompra(comprasResponse.getIdCompra());
                    registraDetalle(codigoCompra);
                }else{
                    msjError = comprasResponse.getMensaje();
                }
            }else {
                Toast.makeText(getContext(),"Error en el formato de respuesta",Toast.LENGTH_SHORT).show();
            }

        }


        @Override
        public void onFailure(Call<ComprasResponse> call, Throwable t) {
            Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
        }
    }

    private int obtenerIdUltimaCompra(ArrayList<RecursoId> id) {
        List<Integer> list = new ArrayList<>();

        for (RecursoId u : id) {
            list.add( u.getUltimoCodigocompra());
        }
        Integer[] val_user = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            val_user[i] = list.get(i);
        }
        return val_user[0] ;
    }

    public void registraDetalle(int codigoCompra){
        final DetallePedido detallePedido = new DetallePedido();
        Iterator<Compra> iterator = compras.iterator();
        while (iterator.hasNext()){
            Compra itemCompra =iterator.next();
            int codProd = itemCompra.getCodigoProducto();
            int cantProd = itemCompra.getCantidadProducto();

            detallePedido.setCodigoCompra(codigoCompra);
            detallePedido.setCantidadCompra(cantProd);
            detallePedido.setCodigoProductoM(codProd);

            Call<DetalleCompraResponse> call = compraApiAdapter.getApiService().registrarDetallePedido(detallePedido);
            call.enqueue(new detalleCallBack());
        }
    }

    class detalleCallBack implements Callback<DetalleCompraResponse>{
        String msj;

        @Override
        public void onResponse(Call<DetalleCompraResponse> call, Response<DetalleCompraResponse> response) {
            if(response.isSuccessful()){
                DetalleCompraResponse detalleCompraResponse = response.body();
                assert detalleCompraResponse != null;
                if (detalleCompraResponse.getEstado()==1){
                    estadoCompra=+estadoCompra;
                    if(estadoCompra==compras.size()){
                        cuadro();
                    }
                    /*msj = detalleCompraResponse.getMensaje();
                    msj = "Se ha realizado el pedido con éxito. En breve se comunicará el personal de la agencia para enviar su pedido.";
                    Toast.makeText(getContext(),msj,Toast.LENGTH_LONG).show();
                    compras.clear();*/

                }else {
                    msj=detalleCompraResponse.getMensaje();
                    Toast.makeText(getContext(),msj,Toast.LENGTH_SHORT).show();

                }
            }else {
                Toast.makeText(getContext(),"Error en el formato de respuesta",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<DetalleCompraResponse> call, Throwable t) {

        }
    }

    public void cuadro(){
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.fragment_compra_realizada);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
        //función al botón
        Button boton_closeD = dialog.findViewById(R.id.btn_closeDialog);
        boton_closeD.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dialog.cancel();
                compras.clear();
            }
        });
        //mostrar el fragment
        dialog.show();
    }

    public void cerrarProceso(View v, Pedidos pedidos){
        Call<ComprasResponse> call = compraApiAdapter.getApiService().registrarPedido(pedidos);
        call.enqueue(new pedidoCallBack());
        getFragmentManager().beginTransaction().remove(this).commit();
        //compras.clear();

        Navigation.findNavController(v).navigate(R.id.carritoFragment);
    }


}