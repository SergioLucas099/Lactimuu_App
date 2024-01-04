package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.developers.lactimuu_prueba.Modelos.Ver_Usuarios_Model;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Ver_Usuarios_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Ver_Usuarios extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    Ver_Usuarios_Adapter adapter;
    List<Ver_Usuarios_Model> modelList;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_usuarios);

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_usuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        adapter = new Ver_Usuarios_Adapter(this, modelList);
        recyclerView.setAdapter(adapter);
        imageView = findViewById(R.id.imgAtras_VUG);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        firestore.collection("Usuarios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                    String documentId = documentSnapshot.getId();
                    Ver_Usuarios_Model model = documentSnapshot.toObject(Ver_Usuarios_Model.class);
                    model.setDocumentId(documentId);
                    modelList.add(model);
                    adapter.notifyDataSetChanged();

                }
            }
        });

    }
}