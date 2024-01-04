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

import com.developers.lactimuu_prueba.Modelos.Para_Llevar_Model;
import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Para_Llevar_Adapter extends RecyclerView.Adapter<Para_Llevar_Adapter.ViewHolder> {

    Context context;
    List<Para_Llevar_Model> modelList;
    FirebaseFirestore firestore;

    public Para_Llevar_Adapter(Context context, List<Para_Llevar_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public Para_Llevar_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.para_llevar_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Para_Llevar_Adapter.ViewHolder holder, int position) {


        holder.NombreProducto.setText(modelList.get(position).getNombre_Producto());
        holder.PrecioProducto.setText(modelList.get(position).getPrecio());
        holder.HoraActual.setText(modelList.get(position).getHora_Pedido());
        holder.FechaActual.setText(modelList.get(position).getFecha_Actual());
        holder.PrecioTotal.setText(String.valueOf(modelList.get(position).getPrecio_Total()));
        holder.CantidadTotal.setText(modelList.get(position).getCantidad());
        holder.mesa.setText(modelList.get(position).getPedido());

        holder.btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestore.collection("Pedidos_Para_Llevar").document(modelList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()){
                                    modelList.remove(modelList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Pedido Eliminado", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
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

        TextView NombreProducto, PrecioProducto, HoraActual, FechaActual, PrecioTotal, CantidadTotal, mesa;
        Button btn_eliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            NombreProducto = itemView.findViewById(R.id.txt_nom_pro_pl);
            PrecioProducto = itemView.findViewById(R.id.txt_pre_pro_pl);
            HoraActual = itemView.findViewById(R.id.txt_ho_ac_pl);
            FechaActual = itemView.findViewById(R.id.txt_fe_pe_pl);
            PrecioTotal = itemView.findViewById(R.id.txt_pre_to_pl);
            CantidadTotal = itemView.findViewById(R.id.txt_ca_to_pl);
            mesa = itemView.findViewById(R.id.txt_mesa_pl);
            btn_eliminar = itemView.findViewById(R.id.boton_eliminar_pedido_pl);
        }
    }
}
