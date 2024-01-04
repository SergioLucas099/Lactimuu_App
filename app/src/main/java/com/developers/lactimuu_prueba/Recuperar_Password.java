package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Recuperar_Password extends AppCompatActivity {

    EditText editText;
    Button button;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_password);

        editText = findViewById(R.id.editTextCorreo);
        button = findViewById(R.id.button_reestablecer);

        toolbar = findViewById(R.id.toolbar_recuperar_contraseña);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar_correo();
            }
        });

    }

    private void validar_correo() {

        String Email = editText.getText().toString().trim();

        if(Email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editText.setError("Correo Invalido");
            return;
        }

        sendEmail(Email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Recuperar_Password.this, InicioSesion.class);
        startActivity(intent);
        finish();
    }

    public void sendEmail(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = email;

        auth.sendPasswordResetEmail(emailAddress).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Recuperar_Password.this, "Correo Enviado con Exito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Recuperar_Password.this, InicioSesion.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Recuperar_Password.this, "Correo Invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}