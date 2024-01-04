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

public class Actualizar_Imagenes_Promocionales extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    Button EscogerImg, CargarImg, ActualizarImg;
    TextView textView;
    ImageView imageView;
    ProgressBar progressBar;
    FirebaseFirestore firestore;
    StorageReference storageReference;
    StorageTask UploadTask;
    Toolbar toolbar;
    Uri uri;
    String actimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_imagenes_promocionales);

        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference("Imagenes");

        EscogerImg = findViewById(R.id.boton_escoger_img_promo);
        CargarImg = findViewById(R.id.cargarimagenespromo);
        ActualizarImg = findViewById(R.id.Boton_actualizar_ImgPromo);
        imageView = findViewById(R.id.Imagen_producto_promo);
        progressBar = findViewById(R.id.ProgressBarImgPromo);
        textView = findViewById(R.id.alm_img_promo);
        textView.setVisibility(View.INVISIBLE);

        toolbar = findViewById(R.id.toolbar_escoger_img_promo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actimg = getIntent().getStringExtra("ActualizarImagen");

        ObtenerDatos();

        EscogerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AbrirGaleria();
            }
        });

        CargarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CargarImagenen();
            }
        });

        ActualizarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (UploadTask != null && UploadTask.isInProgress()){
                    Toast.makeText(Actualizar_Imagenes_Promocionales.this, "Subida en Proceso", Toast.LENGTH_SHORT).show();

                }else {
                    ActualizarImagen();
                }
            }
        });
    }

    private void ObtenerDatos (){

        firestore.collection("ImagenesPromocionales").document(actimg).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    String img = documentSnapshot.getString("url");

                    Picasso.get().load(img).into(imageView);
                    textView.setText(img);
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

    private void CargarImagenen() {

        if(uri != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis()
                    + "." + getFileExtension(uri));

            UploadTask = fileReference.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(0);
                                }
                            }, 500);

                            Toast.makeText(Actualizar_Imagenes_Promocionales.this, "Subida exitosa", Toast.LENGTH_SHORT).show();
                            Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                            result.addOnSuccessListener(new OnSuccessListener<Uri>()
                            {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String photoStringLink = uri.toString();
                                    textView.setText(photoStringLink);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Actualizar_Imagenes_Promocionales.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void ActualizarImagen(){

        String img = textView.getText().toString();

        Map<String, Object> map = new HashMap<>();

        map.put("url", img);

        firestore.collection("ImagenesPromocionales").document(actimg).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Actualizar_Imagenes_Promocionales.this, "La imagen a sido actualizada correctamente", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Actualizar_Imagenes_Promocionales.this, "Error al actualizar la imagen", Toast.LENGTH_SHORT).show();
            }
        });
    }
}