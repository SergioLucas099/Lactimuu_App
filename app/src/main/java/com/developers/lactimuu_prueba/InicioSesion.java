package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.developers.lactimuu_prueba.Modelos.Ver_Usuarios_Model;
import com.example.lactimuu_prueba.R;
import com.developers.lactimuu_prueba.adapters.Ver_Usuarios_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class InicioSesion extends AppCompatActivity {

    Button Entrar;
    TextInputEditText usuario, contraseña;
    Ver_Usuarios_Adapter adapter;
    List<Ver_Usuarios_Model> modelList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    CheckBox vercontraseña;
    SharedPreferences preferences;
    ImageView imageView;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        auth = FirebaseAuth.getInstance();

        Entrar = findViewById(R.id.button);
        usuario = findViewById(R.id.editText);
        contraseña = findViewById(R.id.editText2);

        vercontraseña = findViewById(R.id.ver_contraseña);

        imageView = findViewById(R.id.imgBackIS);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        modelList = new ArrayList<>();
        adapter = new Ver_Usuarios_Adapter(this, modelList);

        vercontraseña.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (b){
                    contraseña.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    contraseña.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
                limpiar();
            }
        });
    }

    public void recuperar_contraseña (View view){
        Intent recuperar_contraseña = new Intent(this, Recuperar_Password.class);
        startActivity(recuperar_contraseña);
    }

    private void loginUser() {

        String userEmail = usuario.getText().toString();
        String userContraseña = contraseña.getText().toString();

        if (TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "La casilla usuario esta vacia", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userContraseña)){
            Toast.makeText(this, "La casilla contraseña esta vacia", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userContraseña.length() < 6){
            Toast.makeText(this, "La contraseña es muy corta", Toast.LENGTH_SHORT).show();
            return;
        }

        firestore.collection("Usuarios").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                preferences = InicioSesion.this.getSharedPreferences("typeUser", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){

                    String documentId = documentSnapshot.getId();
                    Ver_Usuarios_Model model = documentSnapshot.toObject(Ver_Usuarios_Model.class);
                    model.setDocumentId(documentId);
                    modelList.add(model);
                    adapter.notifyDataSetChanged();

                    String dato1 = model.getCorreo().toString();
                    String dato2 = model.getRol();
                    String dato3 = model.getEstado();

                    if (dato1.equals(userEmail) && dato2.equals("Administrador Empleado") && dato3.equals("Activo")){

                        auth.signInWithEmailAndPassword(userEmail,userContraseña)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()){
                                            Toast.makeText(InicioSesion.this, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(InicioSesion.this,Modo_Usuario_Empleado.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            editor.putString("user", "Empleado");
                                            editor.apply();
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(InicioSesion.this, "Error "+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                    } else if (dato1.equals(userEmail) && dato2.equals("Administrador Empleado") && dato3.equals("Inactivo")){

                        auth.signInWithEmailAndPassword(userEmail,userContraseña)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task2) {

                                        if (task2.isSuccessful()){
                                            Toast.makeText(InicioSesion.this, "Usuario Inactivo", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(InicioSesion.this, "Error "+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                    }

                    if (dato1.equals(userEmail) && dato2.equals("Administrador Gerente") && dato3.equals("Activo")){

                        auth.signInWithEmailAndPassword(userEmail,userContraseña)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()){
                                            Toast.makeText(InicioSesion.this, "Inicio de Sesión Exitoso", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(InicioSesion.this,Inicio_Administrador.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            editor.putString("user", "Gerente");
                                            editor.apply();
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(InicioSesion.this, "Error "+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                    } else if (dato1.equals(userEmail) && dato2.equals("Administrador Gerente") && dato3.equals("Inactivo")){

                        auth.signInWithEmailAndPassword(userEmail,userContraseña)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task2) {

                                        if (task2.isSuccessful()){
                                            Toast.makeText(InicioSesion.this, "Usuario Inactivo", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(InicioSesion.this, "Error "+task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                    }
                }
            }
        });

    }

    private void limpiar(){
        usuario.setText("");
        contraseña.setText("");
    }

    public void contarseña (View view){
        Intent intent = new Intent(InicioSesion.this, Recuperar_Password.class);
        startActivity(intent);
    }
}