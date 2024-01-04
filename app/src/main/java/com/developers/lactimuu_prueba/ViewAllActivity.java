package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.lactimuu_prueba.Modelos.ViewAllModel;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.ViewAllAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    TextView textView;
    ViewAllAdapter viewAllAdapter;
    List<ViewAllModel> viewAllModelList;
    ImageView imageView, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        firestore = FirebaseFirestore.getInstance();
        String type = getIntent().getStringExtra("type");
        textView = findViewById(R.id.txtviewall);
        recyclerView = findViewById(R.id.view_all_rec);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageView = findViewById(R.id.imgViewAll);
        back = findViewById(R.id.imgBack);
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this,viewAllModelList);
        recyclerView.setAdapter(viewAllAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Llamando la categoria de Lacteos
        if (type != null && type.equalsIgnoreCase("Lacteos")) {
            textView.setText("Lacteos");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes_Categorias%2Fproductos-lacteos.png?alt=media&token=41f2e562-db12-4651-9e5f-c46fb06a7d0d";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo", "Lacteos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Llamando la categoria de Paquetes
        if (type != null && type.equalsIgnoreCase("Paquetes")){
            textView.setText("Paquetes");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes_Categorias%2Fpatatas-fritas.png?alt=media&token=4e5711fb-47bf-43a3-b9dc-b551a9ea26a2";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo","Paquetes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        //Llamando la categoria de Golosinas
        if (type != null && type.equalsIgnoreCase("Golosinas")){
            textView.setText("Golosinas");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes_Categorias%2Fgolosinas.png?alt=media&token=900fe093-208f-45ae-bbab-d1c79f6703cb";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo","Golosinas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }
                }
            });
        }

        //Llamando la categoria de Licores
        if (type != null && type.equalsIgnoreCase("Licores")){
            textView.setText("Licores");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes_Categorias%2Flicor-de-hierbas.png?alt=media&token=4f463215-3a13-4218-9cac-4535aa6af22b";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo","Licores").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }

        //Llamando la categoria de Bebidas Calientes
        if (type != null && type.equalsIgnoreCase("Bebidas Calientes")){
            textView.setText("Bebidas Calientes");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes_Categorias%2Ftaza-de-cafe.png?alt=media&token=1f70325d-6051-40d2-9700-28d7548c188b";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo","Bebidas Calientes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }

        //Llamando la categoria de Bebidas Frias
        if (type != null && type.equalsIgnoreCase("Bebidas Frias")){
            textView.setText("Bebidas Frias");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes_Categorias%2Fbebidas-sin-alcohol.png?alt=media&token=1c355f19-6cc3-4fd1-831e-21c7103b2672";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo","Bebidas Frias").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }

        //Llamando la categoria de Carnes
        if (type != null && type.equalsIgnoreCase("Carnes")){
            textView.setText("Carnes");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes_Categorias%2Fcarne.png?alt=media&token=bc8b5720-2ac2-4345-9adf-2c1a267ca821";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo","Carnes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }

        //Llamando la categoria de Tradicional Regional
        if (type != null && type.equalsIgnoreCase("Tradicional Regional")){
            textView.setText("Tradicional");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes%2Ftradicion%20regional.png?alt=media&token=cd14dc1f-3ab1-4cb3-b06a-8ab2ed858e7f";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo","Tradicional Regional").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }

        //Llamando la categoria de Cigarrillos
        if (type != null && type.equalsIgnoreCase("Cigarrillos")){
            textView.setText("Cigarrillos");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes_Categorias%2Fcigarrillo.png?alt=media&token=cba3a55d-9387-419f-984e-ce15c7cb34dd";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo","Cigarrillos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }

        //Llamando la categoria de Postres
        if (type != null && type.equalsIgnoreCase("Postres")){
            textView.setText("Postres");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes_Categorias%2Fpastel.png?alt=media&token=38191ef8-29a9-4c37-b11c-56c10940d4e1";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo","Postres").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }

        //Llamando la categoria de Helados
        if (type != null && type.equalsIgnoreCase("Helados")){
            textView.setText("Helados");
            String url = "https://firebasestorage.googleapis.com/v0/b/lactimuu-13c11.appspot.com/o/Imagenes_Categorias%2Fcopa-de-helado.png?alt=media&token=d723c3d9-6dbd-4130-b18c-e4582827262f";
            Picasso.get().load(url).into(imageView);
            firestore.collection("TodosLosProductos").whereEqualTo("Tipo","Helados").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                        ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                        viewAllModelList.add(viewAllModel);
                        viewAllAdapter.notifyDataSetChanged();

                    }

                }
            });
        }

    }
}