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

import com.developers.lactimuu_prueba.Modelos.Para_Llevar_Model;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Para_Llevar_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Para_Llevar extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    Para_Llevar_Adapter adapter;
    List<Para_Llevar_Model> modelList;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_para_llevar);

        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_pedidos_mesapl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        adapter = new Para_Llevar_Adapter(this, modelList);
        recyclerView.setAdapter(adapter);
        textView = findViewById(R.id.total_mesapl);

        imageView = findViewById(R.id.imgAtras_pl);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Para_Llevar.this, Modo_Usuario_Empleado.class);
                startActivity(intent);
                finish();
            }
        });

        firestore.collection("Pedidos_Para_Llevar").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    String documentId = documentSnapshot.getId();
                    Para_Llevar_Model model = documentSnapshot.toObject(Para_Llevar_Model.class);
                    model.setDocumentId(documentId);
                    modelList.add(model);
                    adapter.notifyDataSetChanged();
                }
                calculartotal(modelList);
            }
        });
    }
    private void calculartotal(List<Para_Llevar_Model> ParaLlevarModels) {

        int total = 0;
        for (Para_Llevar_Model model : ParaLlevarModels){
            total += model.getPrecio_Total();
        }
        textView.setText("Total a Pagar: $" + total);
    }
}