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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Crear_Imagen_Promocional extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    ImageView imageView;
    TextView Imagen;
    ProgressBar progressBar;
    FirebaseFirestore firestore;
    StorageReference storageReference;
    Button EscogerImagen, SubirImagen, CargarImagen;
    StorageTask UploadTask;
    Uri uri;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_imagen_promocional);

        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("Imagenes");

        EscogerImagen = findViewById(R.id.escoger_imgPromo);
        SubirImagen = findViewById(R.id.Boton_Subir_Firestore_PROMO);
        CargarImagen = findViewById(R.id.cargarimagenes_PROMO);
        imageView = findViewById(R.id.Imagen_producto_promo);
        progressBar = findViewById(R.id.ProgressBarImgPROMO);
        Imagen = findViewById(R.id.alm_img_promo_da);
        Imagen.setVisibility(View.INVISIBLE);
        toolbar = findViewById(R.id.toolbar_Crear_imgPromo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EscogerImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirGaleria();
            }
        });

        CargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CargarImagen();
            }
        });

        SubirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (UploadTask != null && UploadTask.isInProgress()){
                    Toast.makeText(Crear_Imagen_Promocional.this, "Subida en Proceso", Toast.LENGTH_SHORT).show();

                }else {
                    SubirImagen();
                    Limpiar();
                }
            }
        });
    }

    private void AbrirGaleria() {
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

    public void CargarImagen(){

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

                            Toast.makeText(Crear_Imagen_Promocional.this, "Subida exitosa", Toast.LENGTH_SHORT).show();
                            Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photoStringLink = uri.toString();
                                    Imagen.setText(photoStringLink);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Crear_Imagen_Promocional.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void SubirImagen() {

        String img = Imagen.getText().toString();

        Map<String, Object> map = new HashMap<>();

        map.put("url", img);

        if(uri == null) {

            Toast.makeText(this, "Ningún archivo seleccionado", Toast.LENGTH_SHORT).show();
        }else if(img.equals("")){

            Toast.makeText(this, "Debe cargar las imagenes antes de subirlas", Toast.LENGTH_LONG).show();
        }else{

            firestore.collection("ImagenesPromocionales").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(Crear_Imagen_Promocional.this, "Se ha creado un nuevo producto", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Crear_Imagen_Promocional.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void Limpiar (){

        Imagen.setText("");
    }

}