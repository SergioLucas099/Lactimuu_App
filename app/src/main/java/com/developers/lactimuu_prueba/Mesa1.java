package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.lactimuu_prueba.Modelos.Mesa1_Model;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Mesa1_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Mesa1 extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    Mesa1_Adapter adapter;
    List<Mesa1_Model> modelList;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa1);

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_pedidos_mesa1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        adapter = new Mesa1_Adapter(this, modelList);
        recyclerView.setAdapter(adapter);
        textView = findViewById(R.id.total_mesa1);
        imageView = findViewById(R.id.imgAtras_Mesa1);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mesa1.this, Modo_Usuario_Empleado.class);
                startActivity(intent);
                finish();
            }
        });

        firestore.collection("Pedidos_Mesa_1").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    String documentId = documentSnapshot.getId();
                    Mesa1_Model model = documentSnapshot.toObject(Mesa1_Model.class);
                    model.setDocumentId(documentId);
                    modelList.add(model);
                    adapter.notifyDataSetChanged();
                }
                calculartotal(modelList);
            }
        });
    }

    private void calculartotal(List<Mesa1_Model> mesa1Models) {

        int total = 0;
        for (Mesa1_Model model : mesa1Models){
            total += model.getPrecio_Total();
        }
        textView.setText("Total a Pagar: $" + total);
    }
}