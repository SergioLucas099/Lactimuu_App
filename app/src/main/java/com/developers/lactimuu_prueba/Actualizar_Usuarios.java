package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Actualizar_Usuarios extends AppCompatActivity {

    TextView Correo, Estado, Nombre, Rol, dato_rol, dato_estado;
    Spinner rolspinner, estadospinner;
    Toolbar toolbar;
    Button act;

    private FirebaseFirestore firestore;
    private String act_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_usuarios);

        Correo = findViewById(R.id.correo_user);
        Estado = findViewById(R.id.estado_user);
        Nombre = findViewById(R.id.nom_user);
        Rol = findViewById(R.id.rol_user);
        dato_rol = findViewById(R.id.rol_user_act);
        dato_estado = findViewById(R.id.act_estado_user);

        toolbar = findViewById(R.id.toolbar_act_user);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rolspinner = findViewById(R.id.spinnerroluser);
        estadospinner = findViewById(R.id.spinnerestadouser);

        act = findViewById(R.id.btn_act);

        rolspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1: dato_rol.setText("Administrador Gerente"); break;
                    case 2: dato_rol.setText("Administrador Empleado"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        estadospinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1: dato_estado.setText("Activo"); break;
                    case 2: dato_estado.setText("Inactivo"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        firestore = FirebaseFirestore.getInstance();
        act_user = getIntent().getStringExtra("act_usuario_id");

        ObtenerInf();

        act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Actualizar();
            }
        });


    }

    private void ObtenerInf() {
        firestore.collection("Usuarios").document(act_user).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()){
                    String correo = documentSnapshot.getString("Correo");
                    String estado = documentSnapshot.getString("Estado");
                    String nombre = documentSnapshot.getString("Nombre");
                    String rol = documentSnapshot.getString("Rol");

                    Correo.setText(correo);
                    Estado.setText(estado);
                    Nombre.setText(nombre);
                    Rol.setText(rol);
                }
            }
        });
    }

    private void Actualizar() {

        String datoRolUser = dato_rol.getText().toString();
        String datoEstadoUser = dato_estado.getText().toString();

        Map<String, Object> map = new HashMap<>();
        map.put("Rol", datoRolUser);
        map.put("Estado", datoEstadoUser);

        firestore.collection("Usuarios").document(act_user).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Actualizar_Usuarios.this, "El usuario ha sido actualizados correctamente", Toast.LENGTH_SHORT).show();
            }
        }). addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Actualizar_Usuarios.this, "Error al actualizar el usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}