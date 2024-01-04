package com.developers.lactimuu_prueba.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developers.lactimuu_prueba.DetallesActivity;
import com.developers.lactimuu_prueba.Modelos.ViewAllModel;
import com.example.lactimuu_prueba.R;

import java.util.List;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    Context context;
    List<ViewAllModel> list;

    public ViewAllAdapter(Context context, List<ViewAllModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(list.get(position).getNombre());
        holder.price.setText(list.get(position).getPrecio()+"");

        if(list.get(position).getEstado().equals("Disponible")){
            holder.rating.setVisibility(View.VISIBLE);
            holder.rating2.setVisibility(View.INVISIBLE);
            holder.imagen.setVisibility(View.VISIBLE);
        }else if(list.get(position).getEstado().equals("Agotado")){
            holder.rating.setVisibility(View.INVISIBLE);
            holder.rating2.setVisibility(View.VISIBLE);
            holder.imagen.setVisibility(View.INVISIBLE);
        }

        if (list.get(position).getTipo().equals("Licores")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }
        if (list.get(position).getTipo().equals("Bebidas Calientes")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }
        if (list.get(position).getTipo().equals("Bebidas Frias")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }
        if (list.get(position).getTipo().equals("Lacteos")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }
        if (list.get(position).getTipo().equals("Golosinas")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }
        if (list.get(position).getTipo().equals("Carnes")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }
        if (list.get(position).getTipo().equals("Tradicional Regional")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }
        if (list.get(position).getTipo().equals("Cigarrillos")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }
        if (list.get(position).getTipo().equals("Postres")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }
        if (list.get(position).getTipo().equals("Helados")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }
        if (list.get(position).getTipo().equals("Paquetes")){
            holder.price.setText(list.get(position).getPrecio()+"");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetallesActivity.class);
                intent.putExtra("detail",list.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView, imagen;
        TextView name,price,rating, rating2;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            imageView = itemView.findViewById(R.id.view_img);
            name = itemView.findViewById(R.id.view_name);
            price = itemView.findViewById(R.id.view_price);
            rating = itemView.findViewById(R.id.view_rating);
            rating2 = itemView.findViewById(R.id.view_rating2);
            imagen = itemView.findViewById(R.id.btn_view_all_adapter);

        }
    }
}
