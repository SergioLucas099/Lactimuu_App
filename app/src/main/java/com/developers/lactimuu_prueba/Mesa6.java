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

import com.developers.lactimuu_prueba.Modelos.Mesa6_Model;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Mesa6_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Mesa6 extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    Mesa6_Adapter adapter;
    List<Mesa6_Model> modelList;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mesa6);

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_pedidos_mesa6);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        adapter = new Mesa6_Adapter(this, modelList);
        recyclerView.setAdapter(adapter);
        textView = findViewById(R.id.total_mesa6);
        imageView = findViewById(R.id.imgAtras_Mesa6);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mesa6.this, Modo_Usuario_Empleado.class);
                startActivity(intent);
                finish();
            }
        });

        firestore.collection("Pedidos_Mesa_6").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    String documentId = documentSnapshot.getId();
                    Mesa6_Model model = documentSnapshot.toObject(Mesa6_Model.class);
                    model.setDocumentId(documentId);
                    modelList.add(model);
                    adapter.notifyDataSetChanged();
                }
                calculartotal(modelList);
            }
        });
    }
    private void calculartotal(List<Mesa6_Model> mesa6Models) {

        int total = 0;
        for (Mesa6_Model model : mesa6Models){
            total += model.getPrecio_Total();
        }
        textView.setText("Total a Pagar: $" + total);
    }
}