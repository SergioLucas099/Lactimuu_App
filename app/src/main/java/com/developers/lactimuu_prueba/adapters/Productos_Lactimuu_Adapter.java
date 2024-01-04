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
import com.developers.lactimuu_prueba.Modelos.Productos_Lactimuu_Model;
import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Productos_Lactimuu_Adapter extends RecyclerView.Adapter<Productos_Lactimuu_Adapter.ViewHolder> {

    Context context;
    List<Productos_Lactimuu_Model> list;
    FirebaseFirestore firestore;

    public Productos_Lactimuu_Adapter(Context context, List<Productos_Lactimuu_Model> list) {
        this.context = context;
        this.list = list;
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_recomendados,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.nombre.setText(list.get(position).getNombre());
        holder.precio.setText("$"+list.get(position).getPrecio());
        holder.dato.setText(list.get(position).getEstado());
        holder.dato.setVisibility(View.INVISIBLE);

        if (holder.dato.getText().toString().equals("Agotado")){

            holder.sum.setVisibility(View.INVISIBLE);
            holder.res.setVisibility(View.INVISIBLE);
            holder.carrito.setVisibility(View.INVISIBLE);
            holder.num.setText("Agotado");
        }

        holder.sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.totalQuantity < 99){
                    holder.totalQuantity++;
                    holder.num.setText(String.valueOf(holder.totalQuantity));
                    holder.totalPrice = list.get(position).getPrecio() * holder.totalQuantity;
                }
            }
        });

        holder.res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.totalQuantity > 1){
                    holder.totalQuantity--;
                    holder.num.setText(String.valueOf(holder.totalQuantity));
                    holder.totalPrice = list.get(position).getPrecio() * holder.totalQuantity;
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

                cartMap.put("Nombre_Producto",list.get(position).getNombre());
                cartMap.put("Precio","$"+list.get(position).getPrecio());
                cartMap.put("Fecha_Actual",saveCurrentDate);
                cartMap.put("Hora_Pedido",saveCurrentTime);
                cartMap.put("Cantidad",holder.num.getText().toString());
                cartMap.put("Precio_Total",holder.totalPrice = list.get(position).getPrecio() * holder.totalQuantity);

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
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, precio, estado, num, dato;
        ImageView imageView, sum, res, carrito;
        int totalQuantity = 1;
        int totalPrice = 0;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.rec_img);
            nombre = itemView.findViewById(R.id.rec_name);
            precio = itemView.findViewById(R.id.rec_pre);
            estado = itemView.findViewById(R.id.est_pro_dis);
            sum = itemView.findViewById(R.id.maspro);
            res = itemView.findViewById(R.id.menospro);
            num = itemView.findViewById(R.id.contadorpro);
            carrito = itemView.findViewById(R.id.carrito_agregar);
            dato = itemView.findViewById(R.id.d);
        }
    }
}
