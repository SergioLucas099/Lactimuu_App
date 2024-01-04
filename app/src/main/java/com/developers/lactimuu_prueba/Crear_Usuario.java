package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Crear_Usuario extends AppCompatActivity {

    EditText nombre, correo, contraseña;
    Button registar;
    TextView textView;
    Spinner spinner;
    String nom, cor, con, estado, rol;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        nombre = findViewById(R.id.texto_nombre_user);
        correo = findViewById(R.id.texto_email_user);
        contraseña = findViewById(R.id.texto_contraseña_user);
        registar = findViewById(R.id.registrarbtn);
        spinner = findViewById(R.id.spinner_crear_usuario);
        textView = findViewById(R.id.dato_user);
        imageView = findViewById(R.id.imgAtras_AU);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1: textView.setText("Administrador Gerente"); break;
                    case 2: textView.setText("Administrador Empleado"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth = FirebaseAuth.getInstance();
                firestore = FirebaseFirestore.getInstance();

                nom = nombre.getText().toString();
                cor = correo.getText().toString();
                con = contraseña.getText().toString();
                estado = "Activo";
                rol = textView.getText().toString();

                if (!nom.isEmpty() && !cor.isEmpty() && !con.isEmpty() && !rol.isEmpty()){

                    if (con.length() >= 6){
                        
                        RegistrarUsuario();

                    }else {
                        Toast.makeText(Crear_Usuario.this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Crear_Usuario.this, "Debe completar todos los campos para continuar", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void RegistrarUsuario() {

        auth.createUserWithEmailAndPassword(cor, con).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    FirebaseUser user = auth.getCurrentUser();
                    String userID = user.getUid();
                    DocumentReference documentReference = firestore.collection("Usuarios").document(userID);

                    Map<String, Object> map = new HashMap<>();

                    map.put("Nombre", nom);
                    map.put("Correo", cor);
                    map.put("Estado", estado);
                    map.put("Rol", rol);

                    documentReference.set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Crear_Usuario.this, "Usuario Creado", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Crear_Usuario.this, "Error al crear usuario", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else {
                    Toast.makeText(Crear_Usuario.this, "Error al registrar este usuario", Toast.LENGTH_SHORT).show();
                }
            }
        });
        finish();
    }
}