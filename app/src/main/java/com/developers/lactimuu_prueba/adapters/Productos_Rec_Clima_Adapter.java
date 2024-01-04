package com.developers.lactimuu_prueba.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developers.lactimuu_prueba.Modelos.Productos_Rec_Clima_Model;
import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Productos_Rec_Clima_Adapter extends RecyclerView.Adapter<Productos_Rec_Clima_Adapter.ViewHolder> {

    private Context context;
    private List<Productos_Rec_Clima_Model> productosRecomendadosList;
    FirebaseFirestore firestore;

    public Productos_Rec_Clima_Adapter(Context context, List<Productos_Rec_Clima_Model> productosRecomendadosList) {
        this.context = context;
        this.productosRecomendadosList = productosRecomendadosList;
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_rec_clima_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(productosRecomendadosList.get(position).getImg_url()).into(holder.popImg);
        holder.nombre.setText(productosRecomendadosList.get(position).getNombre());
        holder.estadoclima.setText(productosRecomendadosList.get(position).getEstado_Clima());
        holder.precio.setText("$"+productosRecomendadosList.get(position).getPrecio());
        holder.dato.setText(productosRecomendadosList.get(position).getEstado());
        holder.dato.setVisibility(View.INVISIBLE);

        if (holder.dato.getText().toString().equals("Agotado")){
            holder.sumar.setVisibility(View.INVISIBLE);
            holder.restar.setVisibility(View.INVISIBLE);
            holder.carrito.setVisibility(View.INVISIBLE);
            holder.numero.setText("Agotado");
        }

        holder.sumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.totalQuantity < 99){
                    holder.totalQuantity++;
                    holder.numero.setText(String.valueOf(holder.totalQuantity));
                    holder.totalPrice = productosRecomendadosList.get(position).getPrecio() * holder.totalQuantity;
                }
            }
        });

        holder.restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.totalQuantity > 1){
                    holder.totalQuantity--;
                    holder.numero.setText(String.valueOf(holder.totalQuantity));
                    holder.totalPrice = productosRecomendadosList.get(position).getPrecio() * holder.totalQuantity;
                }
            }
        });

        holder.carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String saveCurrentDate,saveCurrentTime;
                Calendar calForDate =  Calendar.getInstance();

                SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
                saveCurrentDate = currentDate.format(calForDate.getTime());

                SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime = currentTime.format(calForDate.getTime());

                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("Nombre_Producto",productosRecomendadosList.get(position).getNombre());
                cartMap.put("Precio","$"+productosRecomendadosList.get(position).getPrecio());
                cartMap.put("Fecha_Actual",saveCurrentDate);
                cartMap.put("Hora_Pedido",saveCurrentTime);
                cartMap.put("Cantidad",holder.numero.getText().toString());
                cartMap.put("Precio_Total",holder.totalPrice = productosRecomendadosList.get(position).getPrecio() * holder.totalQuantity);

                firestore.collection("CarritoCompras").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(context, "Agregado al Carrito de Compras", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return productosRecomendadosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView popImg, sumar, restar, carrito;
        TextView nombre, estadoclima, precio, numero, dato;
        int totalQuantity = 1;
        int totalPrice = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_img);
            nombre = itemView.findViewById(R.id.pop_nombre);
            estadoclima = itemView.findViewById(R.id.pop_estado_clima);
            precio = itemView.findViewById(R.id.pop_precio);
            sumar = itemView.findViewById(R.id.masproduct);
            restar = itemView.findViewById(R.id.menosproduc);
            numero = itemView.findViewById(R.id.contadorproduct);
            carrito = itemView.findViewById(R.id.carrito_compras);
            dato = itemView.findViewById(R.id.estado_pro);
        }
    }
}