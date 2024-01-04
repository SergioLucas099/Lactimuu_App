package com.developers.lactimuu_prueba.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developers.lactimuu_prueba.ActualizarProductos;
import com.developers.lactimuu_prueba.Modelos.Buscar_Producto_Model;
import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Buscar_Producto_Adapter extends RecyclerView.Adapter<Buscar_Producto_Adapter.ViewHolder> {

    Context context;
    List<Buscar_Producto_Model> modelList;
    FirebaseFirestore firestore;

    public Buscar_Producto_Adapter(Context context, List<Buscar_Producto_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public Buscar_Producto_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.buscar_producto_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Buscar_Producto_Adapter.ViewHolder holder, int position) {

        holder.nombre.setText(modelList.get(position).getNombre());
        Picasso.get()
                .load(modelList.get(position).getImg_url())
                .into(holder.imageView);
        holder.descripcion.setText(modelList.get(position).getDescripcion());
        holder.estado.setText(modelList.get(position).getEstado());
        holder.precio.setText(String.valueOf(modelList.get(position).getPrecio()));
        holder.tipo.setText(modelList.get(position).getTipo());
        holder.clima.setText(modelList.get(position).getEstado_Clima());

        holder.bactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ActualizarProductos.class);
                intent.putExtra("actualizarid", modelList.get(position).getDocumentId());
                context.startActivity(intent);
            }
        });

        holder.beliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("TodosLosProductos").document(modelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    modelList.remove(modelList.get(position));
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
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, descripcion, precio, estado, tipo, clima;
        ImageView imageView;
        Button bactualizar, beliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txt_nam_pro_bus);
            imageView = itemView.findViewById(R.id.vimg_bus);
            descripcion = itemView.findViewById(R.id.txt_des_pro_bus);
            precio = itemView.findViewById(R.id.txt_pre_pro_bus);
            estado = itemView.findViewById(R.id.txt_est_pro_bus);
            tipo = itemView.findViewById(R.id.txt_typ_pro_bus);
            clima = itemView.findViewById(R.id.txt_cli_pro_bus);
            bactualizar = itemView.findViewById(R.id.btn_act_pro_bus);
            beliminar = itemView.findViewById(R.id.btn_elm_pro_bus);
        }
    }
}
