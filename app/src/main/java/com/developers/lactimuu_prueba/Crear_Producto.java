package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Crear_Producto extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText ETdes, ETnom, ETpri, ETimg;
    TextView Etest, ETtip, Etcli;
    Button btn_Crear, ver_imagen;
    ImageView imageView, img2;
    Spinner spinner, spinner2, spinner3;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    FirebaseFirestore firestore;
    Toolbar toolbar;
    StorageTask UploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);

        toolbar = findViewById(R.id.toolbar_crear_producto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("Imagenes");
        databaseReference = FirebaseDatabase.getInstance().getReference("Imagenes");

        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        ETdes = findViewById(R.id.crear_desc);
        ETnom = findViewById(R.id.crear_nombre);
        ETpri = findViewById(R.id.crear_precio);
        Etest = findViewById(R.id.crear_estado);
        ETtip = findViewById(R.id.crear_tipo);
        Etcli = findViewById(R.id.crear_clima);
        ETimg = findViewById(R.id.crear_img);

        btn_Crear = findViewById(R.id.btn_crear);
        ver_imagen = findViewById(R.id.btn_vimg);

        img2 = findViewById(R.id.img_mostrar);

        ETimg.setVisibility(View.INVISIBLE);

        String dato = getIntent().getStringExtra("dato");
        ETimg.setText(dato);

        Picasso.get()
                .load(dato)
                .into(img2);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1: ETtip.setText("Carnes"); break;
                    case 2: ETtip.setText("Golosinas"); break;
                    case 3: ETtip.setText("Lacteos"); break;
                    case 4: ETtip.setText("Paquetes"); break;
                    case 5: ETtip.setText("Bebidas Calientes"); break;
                    case 6: ETtip.setText("Bebidas Frias"); break;
                    case 7: ETtip.setText("Licores"); break;
                    case 8: ETtip.setText("Helados"); break;
                    case 9: ETtip.setText("Postres"); break;
                    case 10: ETtip.setText("Cigarrillos"); break;
                    case 11: ETtip.setText("Tradicional Regional"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1: Etest.setText("Disponible");
                        break;

                    case 2: Etest.setText("Agotado");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1: Etcli.setText("Caliente"); break;
                    case 2: Etcli.setText("Templado"); break;
                    case 3: Etcli.setText("Frio"); break;
                    case 4: Etcli.setText("Sin Recomendación"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_Crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                crear_datos();
            }
        });

        ver_imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Crear_Producto.this, Ver_Imagenes.class);
                startActivity(intent);
            }
        });
    }

    private void crear_datos(){

        String descripcion = ETdes.getText().toString();
        String img = ETimg.getText().toString();
        String nombre = ETnom.getText().toString();
        int precio = Integer.parseInt(ETpri.getText().toString());
        String est = Etest.getText().toString();
        String tipo = ETtip.getText().toString();
        String clima = Etcli.getText().toString();

        Map<String, Object> map = new HashMap<>();

        map.put("Descripcion", descripcion);
        map.put("img_url", img);
        map.put("Nombre", nombre);
        map.put("Precio", precio);
        map.put("Estado", est);
        map.put("Tipo", tipo);
        map.put("Estado_Clima", clima);

        if(descripcion.equals("") || img.equals("") || nombre.equals("") || String.valueOf(precio).equals("") || est.equals("") || tipo.equals("") || clima.equals("")){
            Toast.makeText(Crear_Producto.this, "Ingresar todos los datos para crear el nuevo producto", Toast.LENGTH_SHORT).show();
        }else{

            firestore.collection("TodosLosProductos").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(Crear_Producto.this, "Se ha creado un nuevo producto", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Crear_Producto.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void Limpiar(){

        ETdes.setText("");
        ETimg.setText("");
        ETnom.setText("");
        ETpri.setText("");
        Etest.setText("");
        ETtip.setText("");
        Etcli.setText("");
    }
}