package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ActualizarProductos extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText Etxt_desc, Etxt_nom, Etxt_pre;
    TextView Etxt_tip, Etxt_img, txt_est, txt_clima, txt_dato;
    Button actualizar, cargar_img, escoger_img;
    ProgressBar progressBar;
    ImageView imageView;
    Spinner spinner, spinner2, spinner3;
    StorageReference storageReference;
    StorageTask UploadTask;
    Toolbar toolbar;
    Uri uri;

    private String actualizarid, dato;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_productos);

        toolbar = findViewById(R.id.toolbar_act_pro);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        storageReference = FirebaseStorage.getInstance().getReference("Imagenes");

        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        Etxt_desc = findViewById(R.id.act_des);
        Etxt_img = findViewById(R.id.act_img);
        Etxt_nom = findViewById(R.id.act_nom);
        Etxt_pre = findViewById(R.id.act_pre);
        txt_est = findViewById(R.id.act_est);
        txt_clima = findViewById(R.id.act_cli);
        Etxt_tip = findViewById(R.id.act_tip);
        imageView = findViewById(R.id.img_act);
        txt_dato = findViewById(R.id.txt_dato_img_act);

        actualizar = findViewById(R.id.btn_act);
        escoger_img = findViewById(R.id.escoger_imagen_act_img);
        cargar_img = findViewById(R.id.cargar_img_act_btn);

        progressBar = findViewById(R.id.Progress_act_img);

        escoger_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EscogerImagen();
            }
        });

        cargar_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CargarImg();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1: Etxt_tip.setText("Carnes"); break;
                    case 2: Etxt_tip.setText("Golosinas"); break;
                    case 3: Etxt_tip.setText("Lacteos"); break;
                    case 4: Etxt_tip.setText("Paquetes"); break;
                    case 5: Etxt_tip.setText("Bebidas Calientes"); break;
                    case 6: Etxt_tip.setText("Bebidas Frias"); break;
                    case 7: Etxt_tip.setText("Licores"); break;
                    case 8: Etxt_tip.setText("Helados"); break;
                    case 9: Etxt_tip.setText("Postres"); break;
                    case 10: Etxt_tip.setText("Cigarrillos"); break;
                    case 11: Etxt_tip.setText("Tradicional Regional"); break;
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
                    case 1: txt_est.setText("Disponible");
                        break;

                    case 2: txt_est.setText("Agotado");
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
                    case 1: txt_clima.setText("Caliente"); break;
                    case 2: txt_clima.setText("Templado"); break;
                    case 3: txt_clima.setText("Frio"); break;
                    case 4: txt_clima.setText("Sin Recomendación"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Etxt_img.setVisibility(View.INVISIBLE);

        firestore = FirebaseFirestore.getInstance();
        actualizarid = getIntent().getStringExtra("actualizarid");

        ObtenerDatos();

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarDatos();
            }
        });
    }

    private void ObtenerDatos (){
        firestore.collection("TodosLosProductos").document(actualizarid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String descripcion = documentSnapshot.getString("Descripcion");
                    String img = documentSnapshot.getString("img_url");
                    String nombre = documentSnapshot.getString("Nombre");
                    String precio = String.valueOf(documentSnapshot.get("Precio"));
                    String estado = documentSnapshot.getString("Estado");
                    String tipo = documentSnapshot.getString("Tipo");
                    String clima = documentSnapshot.getString("Estado_Clima");

                    Etxt_desc.setText(descripcion);
                    Etxt_img.setText(img);
                    Picasso.get().load(img).into(imageView);
                    Etxt_nom.setText(nombre);
                    Etxt_pre.setText(precio);
                    txt_est.setText(estado);
                    Etxt_tip.setText(tipo);
                    txt_clima.setText(clima);
                }
            }
        });
    }

    private void EscogerImagen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri = data.getData();

            Picasso.get().load(uri).into(imageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver CR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(CR.getType(uri));
    }

    private void CargarImg() {

        if(uri != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(uri));

            UploadTask = fileReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<com.google.firebase.storage.UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(ActualizarProductos.this, "Subida exitosa", Toast.LENGTH_SHORT).show();
                            Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photoStringLink = uri.toString();
                                    txt_dato.setText(photoStringLink);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ActualizarProductos.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progreso = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            progressBar.setProgress((int)progreso);
                        }
                    });
        } else{
            Toast.makeText(this, "Ningún archivo seleccionado", Toast.LENGTH_SHORT).show();
        }
    }

    private void ActualizarDatos() {

        String inf = txt_dato.getText().toString();

        if (inf.equals("")){

            String descripcion = Etxt_desc.getText().toString();
            String img = Etxt_img.getText().toString();
            String nombre = Etxt_nom.getText().toString();
            int precio = Integer.parseInt(Etxt_pre.getText().toString());
            String estado = txt_est.getText().toString();
            String tipo = Etxt_tip.getText().toString();
            String clima = txt_clima.getText().toString();

            Map<String, Object> map = new HashMap<>();
            map.put("Descripcion", descripcion);
            map.put("img_url", img);
            map.put("Nombre",nombre);
            map.put("Precio", precio);
            map.put("Estado",estado);
            map.put("Tipo",tipo);
            map.put("Estado_Clima",clima);

            firestore.collection("TodosLosProductos").document(actualizarid).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(ActualizarProductos.this, "Los datos han sido actualizados correctamente", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ActualizarProductos.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
                }
            });

        }else{

            String descripcion = Etxt_desc.getText().toString();
            String img = txt_dato.getText().toString();
            String nombre = Etxt_nom.getText().toString();
            int precio = Integer.parseInt(Etxt_pre.getText().toString());
            String estado = txt_est.getText().toString();
            String tipo = Etxt_tip.getText().toString();
            String clima = txt_clima.getText().toString();

            Map<String, Object> map = new HashMap<>();
            map.put("Descripcion", descripcion);
            map.put("img_url", img);
            map.put("Nombre",nombre);
            map.put("Precio", precio);
            map.put("Estado",estado);
            map.put("Tipo",tipo);
            map.put("Estado_Clima",clima);

            firestore.collection("TodosLosProductos").document(actualizarid).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(ActualizarProductos.this, "Los datos han sido actualizados correctamente", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ActualizarProductos.this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}