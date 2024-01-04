package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.developers.lactimuu_prueba.Modelos.Ver_Sugerencias_Model;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Ver_Sugerencias_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Ver_Sugerencias extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    Ver_Sugerencias_Adapter adapter;
    List<Ver_Sugerencias_Model> modelList;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_sugerencias);

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_sugerencias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        adapter = new Ver_Sugerencias_Adapter(this, modelList);
        recyclerView.setAdapter(adapter);
        imageView = findViewById(R.id.imgAtras_VerSug);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        firestore.collection("BuzonRecomendaciones").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()){
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                        String documentId = documentSnapshot.getId();

                        Ver_Sugerencias_Model model = documentSnapshot.toObject(Ver_Sugerencias_Model.class);

                        model.setDocumentId(documentId);

                        modelList.add(model);
                        adapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }
}