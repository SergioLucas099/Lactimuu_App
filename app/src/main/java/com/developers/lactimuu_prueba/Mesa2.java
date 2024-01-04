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

import com.developers.lactimuu_prueba.Modelos.Mesa2_Model;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Mesa2_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Mesa2 extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    Mesa2_Adapter adapter;
    List<Mesa2_Model> modelList;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa2);

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_pedidos_mesa2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        adapter = new Mesa2_Adapter(this, modelList);
        recyclerView.setAdapter(adapter);
        textView = findViewById(R.id.total_mesa2);
        imageView = findViewById(R.id.imgAtras_Mesa2);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mesa2.this, Modo_Usuario_Empleado.class);
                startActivity(intent);
                finish();
            }
        });

        firestore.collection("Pedidos_Mesa_2").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    String documentId = documentSnapshot.getId();
                    Mesa2_Model model = documentSnapshot.toObject(Mesa2_Model.class);
                    model.setDocumentId(documentId);
                    modelList.add(model);
                    adapter.notifyDataSetChanged();
                }
                calculartotal(modelList);
            }
        });
    }
    private void calculartotal(List<Mesa2_Model> mesa2Models) {

        int total = 0;
        for (Mesa2_Model model : mesa2Models){
            total += model.getPrecio_Total();
        }
        textView.setText("Total a Pagar: $" + total);
    }
}