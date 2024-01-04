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

import com.developers.lactimuu_prueba.Modelos.Mesa4_Model;
import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class Mesa4_Adapter extends RecyclerView.Adapter<Mesa4_Adapter.ViewHolder> {

    Context context;
    List<Mesa4_Model> modelList;
    FirebaseFirestore firestore;

    public Mesa4_Adapter(Context context, List<Mesa4_Model> modelList) {
        this.context = context;
        this.modelList = modelList;
        firestore = FirebaseFirestore.getInstance();
    }

    @NonNull
    @Override
    public Mesa4_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mesa4_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Mesa4_Adapter.ViewHolder holder, int position) {

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
                firestore.collection("Pedidos_Mesa_4").document(modelList.get(position).getDocumentId())
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

            NombreProducto = itemView.findViewById(R.id.txt_nom_pro_4);
            PrecioProducto = itemView.findViewById(R.id.txt_pre_pro_4);
            HoraActual = itemView.findViewById(R.id.txt_ho_ac_4);
            FechaActual = itemView.findViewById(R.id.txt_fe_pe_4);
            PrecioTotal = itemView.findViewById(R.id.txt_pre_to_4);
            CantidadTotal = itemView.findViewById(R.id.txt_ca_to_4);
            mesa = itemView.findViewById(R.id.txt_mesa_4);
            btn_eliminar = itemView.findViewById(R.id.boton_eliminar_pedido_4);
        }
    }
}
