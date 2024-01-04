package com.developers.lactimuu_prueba.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developers.lactimuu_prueba.Crear_Producto;
import com.developers.lactimuu_prueba.Modelos.Buscar_Img_Model;
import com.developers.lactimuu_prueba.Ver_Imagenes;
import com.example.lactimuu_prueba.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Buscar_Img_Adapter extends RecyclerView.Adapter<Buscar_Img_Adapter.ViewHolder> {

    Context context;
    List<Buscar_Img_Model> modelList;
    FirebaseFirestore firestore;

    public Buscar_Img_Adapter(Ver_Imagenes context, List<Buscar_Img_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public Buscar_Img_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.buscar_img_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Buscar_Img_Adapter.ViewHolder holder, int position) {

        holder.textView.setText(modelList.get(position).getNombre());
        Picasso.get()
                .load(modelList.get(position).getUrl())
                .into(holder.imageButton);
        holder.dato.setText(modelList.get(position).getUrl());

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Crear_Producto.class);
                intent.putExtra("dato", holder.dato.getText().toString());
                context.startActivity(intent);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("ImagenesAplicacion").document(modelList.get(position).getDocumentoid())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    modelList.remove(modelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Imagen Eliminada", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Imagen Error", Toast.LENGTH_SHORT).show();
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

        TextView textView, dato;
        ImageButton imageButton;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txt_nom_img_buscar);
            imageButton = itemView.findViewById(R.id.ver_imagen_buscar);
            dato = itemView.findViewById(R.id.txt_dato_img_buscar);
            imageView = itemView.findViewById(R.id.borrar_img_buscar);
        }
    }
}
