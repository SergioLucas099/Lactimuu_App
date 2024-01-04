package com.developers.lactimuu_prueba.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developers.lactimuu_prueba.Actualizar_Imagenes_Promocionales;
import com.developers.lactimuu_prueba.Modelos.Imagenes_Promocionales_Modell;
import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Imagenes_Promocionales_Adapter extends RecyclerView.Adapter<Imagenes_Promocionales_Adapter.ViewHolder> {

    Context context;
    List<Imagenes_Promocionales_Modell> modells;
    FirebaseFirestore firestore;

    public Imagenes_Promocionales_Adapter(Context context, List<Imagenes_Promocionales_Modell> modells) {
        this.context = context;
        this.modells = modells;
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public Imagenes_Promocionales_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.imagenes_promocionales_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Imagenes_Promocionales_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.get()
                .load(modells.get(position).getUrl())
                .into(holder.imageButton);

        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Actualizar_Imagenes_Promocionales.class);
                intent.putExtra("ActualizarImagen", modells.get(position).getDocumentoid());
                context.startActivity(intent);
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("ImagenesPromocionales").document(modells.get(position).getDocumentoid())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){
                                    modells.remove(modells.get(position));
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
        return modells.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageButton imageButton;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageButton = itemView.findViewById(R.id.ver_imagen_promocional);
            imageView = itemView.findViewById(R.id.borrar_img);
        }
    }
}
