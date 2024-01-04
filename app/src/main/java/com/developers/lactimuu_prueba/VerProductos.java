package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.lactimuu_prueba.Modelos.Buscar_Producto_Model;
import com.developers.lactimuu_prueba.Modelos.ver_product_model;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Buscar_Producto_Adapter;
import com.developers.lactimuu_prueba.adapters.ver_product_adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class VerProductos extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ver_product_adapter product_adapter;
    List<ver_product_model> modelList;
    Toolbar toolbar;

    //////////buscar//////////

    EditText editText;
    private List<Buscar_Producto_Model> buscar_producto_models;
    private RecyclerView recyclerViewbuscador;
    private Buscar_Producto_Adapter buscar_producto_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_productos);

        imageView = findViewById(R.id.cp);
        textView = findViewById(R.id.tap);
        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_productos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        product_adapter = new ver_product_adapter(this, modelList);
        recyclerView.setAdapter(product_adapter);
        toolbar = findViewById(R.id.toolbar_ver_productos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerProductos.this, Crear_Producto.class);
                startActivity(i);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerProductos.this, Crear_Producto.class);
                startActivity(i);
            }
        });

        firestore.collection("TodosLosProductos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                    String documentId = documentSnapshot.getId();
                    ver_product_model model = documentSnapshot.toObject(ver_product_model.class);
                    model.setDocumentId(documentId);
                    modelList.add(model);
                    product_adapter.notifyDataSetChanged();

                }
            }
        });

        ///////buscar///////
        recyclerViewbuscador = findViewById(R.id.rv_buscar_p);
        editText = findViewById(R.id.buscar_todos_productos);
        buscar_producto_models = new ArrayList<>();
        buscar_producto_adapter = new Buscar_Producto_Adapter(this, buscar_producto_models);
        recyclerViewbuscador.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewbuscador.setAdapter(buscar_producto_adapter);
        recyclerViewbuscador.setHasFixedSize(true);


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.toString().isEmpty()){
                    buscar_producto_models.clear();
                    buscar_producto_adapter.notifyDataSetChanged();
                }else {
                    buscarproducto(editable.toString());
                }
            }
        });
    }

    private void buscarproducto(String Nombre) {

        if (!Nombre.isEmpty()){

            firestore.collection("TodosLosProductos").whereEqualTo("Nombre", Nombre).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful() && task.getResult() != null){

                                buscar_producto_models.clear();
                                buscar_producto_adapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    String documentId = doc.getId();
                                    Buscar_Producto_Model model = doc.toObject(Buscar_Producto_Model.class);
                                    model.setDocumentId(documentId);
                                    buscar_producto_models.add(model);
                                    buscar_producto_adapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
    }
}