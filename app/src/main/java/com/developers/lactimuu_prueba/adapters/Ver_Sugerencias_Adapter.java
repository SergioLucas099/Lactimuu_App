package com.developers.lactimuu_prueba.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developers.lactimuu_prueba.Modelos.Ver_Sugerencias_Model;
import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Ver_Sugerencias_Adapter extends RecyclerView.Adapter <Ver_Sugerencias_Adapter.ViewHolder> {

    Context context;
    List<Ver_Sugerencias_Model> modelList;
    FirebaseFirestore firestore;

    public Ver_Sugerencias_Adapter(Context context, List<Ver_Sugerencias_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public Ver_Sugerencias_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ver_sugerencias_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Ver_Sugerencias_Adapter.ViewHolder holder, int position) {

        holder.nombre.setText(modelList.get(position).getNombre());
        holder.asunto.setText(modelList.get(position).getAsunto());
        holder.fecha.setText(modelList.get(position).getHora());
        holder.hora.setText(modelList.get(position).getFecha());
        holder.mensaje.setText(modelList.get(position).getMensaje());
        holder.telefono.setText(modelList.get(position).getTelefono());

        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("BuzonRecomendaciones").document(modelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    modelList.remove(modelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Sugerencia Eliminada", Toast.LENGTH_SHORT).show();
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

        TextView nombre, asunto, fecha, hora, mensaje, telefono;
        Button eliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.txt_nombre);
            asunto = itemView.findViewById(R.id.txt_asunto);
            fecha = itemView.findViewById(R.id.txt_fecha);
            hora = itemView.findViewById(R.id.txt_hora);
            mensaje = itemView.findViewById(R.id.txt_contenido);
            telefono = itemView.findViewById(R.id.txt_tel);
            eliminar = itemView.findViewById(R.id.boton_eliminar);
        }
    }
}
