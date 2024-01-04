package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.developers.lactimuu_prueba.Modelos.Imagenes_Promocionales_Modell;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Imagenes_Promocionales_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Imagenes_Promocionales extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ImageView imageView;
    Imagenes_Promocionales_Adapter adapter;
    List<Imagenes_Promocionales_Modell> modelList;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes_promocionales);

        imageView = findViewById(R.id.cimg);

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.rv_img_promo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        adapter = new Imagenes_Promocionales_Adapter(this, modelList);
        recyclerView.setAdapter(adapter);
        imageView2 = findViewById(R.id.imgAtras_IPG);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Imagenes_Promocionales.this, Crear_Imagen_Promocional.class);
                startActivity(i);
            }
        });

        firestore.collection("ImagenesPromocionales").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                    String documentId = documentSnapshot.getId();
                    Imagenes_Promocionales_Modell model = documentSnapshot.toObject(Imagenes_Promocionales_Modell.class);
                    model.setDocumentoid(documentId);
                    modelList.add(model);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
}