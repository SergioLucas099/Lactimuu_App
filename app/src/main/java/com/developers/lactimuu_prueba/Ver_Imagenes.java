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

import com.developers.lactimuu_prueba.Modelos.Buscar_Img_Model;
import com.developers.lactimuu_prueba.Modelos.Ver_Imagenes_Model;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Buscar_Img_Adapter;
import com.developers.lactimuu_prueba.adapters.Ver_Imagenes_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Ver_Imagenes extends AppCompatActivity {

    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    Ver_Imagenes_Adapter adapter;
    List<Ver_Imagenes_Model> modelList;
    ImageView imageButton;
    TextView textView;
    Toolbar toolbar;

    //////////buscar//////////

    EditText editText;
    private List<Buscar_Img_Model> buscar_img_models;
    private RecyclerView recyclerViewbuscador_img;
    private Buscar_Img_Adapter buscar_img_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_imagenes);

        imageButton = findViewById(R.id.subir_img);
        textView = findViewById(R.id.ai);
        firestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.rv_ver_imagen);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelList = new ArrayList<>();
        adapter = new Ver_Imagenes_Adapter(this, modelList);
        recyclerView.setAdapter(adapter);
        toolbar = findViewById(R.id.toolbarvi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ver_Imagenes.this, SubirImagenes.class);
                startActivity(i);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Ver_Imagenes.this, SubirImagenes.class);
                startActivity(i);
            }
        });

        firestore.collection("ImagenesAplicacion").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                    String documentId = documentSnapshot.getId();
                    Ver_Imagenes_Model model = documentSnapshot.toObject(Ver_Imagenes_Model.class);
                    model.setDocumentoid(documentId);
                    modelList.add(model);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        ///////buscar///////
        recyclerViewbuscador_img = findViewById(R.id.recycler_buscar_img);
        editText = findViewById(R.id.buscar_img);
        buscar_img_models = new ArrayList<>();
        buscar_img_adapter = new Buscar_Img_Adapter(this, buscar_img_models);
        recyclerViewbuscador_img.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewbuscador_img.setAdapter(buscar_img_adapter);
        recyclerViewbuscador_img.setHasFixedSize(true);

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
                    buscar_img_models.clear();
                    buscar_img_adapter.notifyDataSetChanged();
                }else {
                    buscarimg(editable.toString());
                }
            }
        });
    }

    private void buscarimg(String Nombre) {

        if (!Nombre.isEmpty()){

            firestore.collection("ImagenesAplicacion").whereEqualTo("nombre", Nombre).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if (task.isSuccessful() && task.getResult() != null){

                                buscar_img_models.clear();
                                buscar_img_adapter.notifyDataSetChanged();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    String documentId = doc.getId();
                                    Buscar_Img_Model model = doc.toObject(Buscar_Img_Model.class);
                                    model.setDocumentoid(documentId);
                                    buscar_img_models.add(model);
                                    buscar_img_adapter.notifyDataSetChanged();
                                }
                            }
                        }
                    });
        }
    }
}