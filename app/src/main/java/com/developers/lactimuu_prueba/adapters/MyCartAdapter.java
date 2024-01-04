package com.developers.lactimuu_prueba.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developers.lactimuu_prueba.Modelos.MyCartModel;
import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MyCartAdapter extends RecyclerView.Adapter <MyCartAdapter.ViewHolder> {

    Context context;
    List<MyCartModel> cartModelList;
    int totalPrice = 0;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public MyCartAdapter(Context context, List<MyCartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

       holder.nombre.setText(cartModelList.get(position).getNombre_Producto());
       holder.precio.setText(cartModelList.get(position).getPrecio());
       holder.fecha.setText(cartModelList.get(position).getFecha_Actual());
       holder.hora.setText(cartModelList.get(position).getHora_Pedido());
       holder.cantidad.setText(cartModelList.get(position).getCantidad());
       holder.preciototal.setText(String.valueOf(cartModelList.get(position).getPrecio_Total()));

       holder.deleteItem.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               firestore.collection("CarritoCompras").document(cartModelList.get(position).getDocumentId())
                       .delete()
                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {

                               if (task.isSuccessful()){
                                   cartModelList.remove(cartModelList.get(position));
                                   notifyDataSetChanged();
                                   Toast.makeText(context, "Item Eliminado", Toast.LENGTH_SHORT).show();
                               } else {
                                   Toast.makeText(context, "Error "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
           }
       });
    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, precio, fecha, hora, cantidad, preciototal;
        ImageView deleteItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.product_name);
            precio = itemView.findViewById(R.id.product_price);
            fecha = itemView.findViewById(R.id.current_date);
            hora = itemView.findViewById(R.id.current_time);
            cantidad = itemView.findViewById(R.id.total_quantity);
            preciototal = itemView.findViewById(R.id.total_price);
            deleteItem = itemView.findViewById(R.id.borrar);
        }
    }
}
