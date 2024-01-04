package com.developers.lactimuu_prueba.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developers.lactimuu_prueba.Actualizar_Usuarios;
import com.developers.lactimuu_prueba.Modelos.Ver_Usuarios_Model;
import com.example.lactimuu_prueba.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Ver_Usuarios_Adapter extends RecyclerView.Adapter<Ver_Usuarios_Adapter.ViewHolder> {

    Context context;
    List<Ver_Usuarios_Model> modelList;
    FirebaseFirestore firestore;

    public Ver_Usuarios_Adapter(Context context, List<Ver_Usuarios_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public Ver_Usuarios_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ver_usuarios_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Ver_Usuarios_Adapter.ViewHolder holder, int position) {

        holder.correo.setText(modelList.get(position).getCorreo());
        holder.estado.setText(modelList.get(position).getEstado());
        holder.nombre.setText(modelList.get(position).getNombre());
        holder.rol.setText(modelList.get(position).getRol());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Actualizar_Usuarios.class);
                intent.putExtra("act_usuario_id", modelList.get(position).getDocumentId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView correo, estado, nombre, rol;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            correo = itemView.findViewById(R.id.correo_user);
            estado = itemView.findViewById(R.id.estado_user);
            nombre = itemView.findViewById(R.id.nombre_user);
            rol = itemView.findViewById(R.id.rol_user);
            button = itemView.findViewById(R.id.Actualizar_usuario);

        }
    }
}
