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
import com.developers.lactimuu_prueba.Modelos.Categorias;
import com.developers.lactimuu_prueba.ViewAllActivity;
import com.example.lactimuu_prueba.R;

import java.util.List;

public class Home_Categorias_Adapter extends RecyclerView.Adapter<Home_Categorias_Adapter.ViewHolder> {

    Context context;
    List<Categorias> categoriasList;

    public Home_Categorias_Adapter(Context context, List<Categorias> categoriasList) {
        this.context = context;
        this.categoriasList = categoriasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(categoriasList.get(position).getImg_url()).into(holder.Catimg);
        holder.name.setText(categoriasList.get(position).getNombre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",categoriasList.get(position).getTipo());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriasList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView Catimg;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Catimg = itemView.findViewById(R.id.cat_home_cat_img);
            name = itemView.findViewById(R.id.cat_home_name);
        }
    }
}
