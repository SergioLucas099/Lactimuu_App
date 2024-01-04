package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lactimuu_prueba.R;
import com.google.firebase.auth.FirebaseAuth;

public class Inicio_Administrador extends AppCompatActivity {

    FirebaseAuth auth;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_administrador);

        toolbar = findViewById(R.id.toolbar_modo_gerente);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        auth = FirebaseAuth.getInstance();

    }

    public void VerProductos (View view){
        Intent siguiente2 = new Intent(this, VerProductos.class);
        startActivity(siguiente2);
    }

    public void VerSugerenicas (View view){
        Intent siguiente = new Intent(this, Ver_Sugerencias.class);
        startActivity(siguiente);
    }

    public void Imagenes_Promocionales (View view){
        Intent siguiente5 = new Intent(this, Imagenes_Promocionales.class);
        startActivity(siguiente5);
    }

    public void Crear_Usuario (View view){
        Intent siguiente6 = new Intent(this, Crear_Usuario.class);
        startActivity(siguiente6);
    }

    public void Ver_Usuarios (View view){
        Intent siguiente7 = new Intent(this, Ver_Usuarios.class);
        startActivity(siguiente7);
    }

    public void Manual_Usuario (View view){
        Intent Manual_Usuario = new Intent(this, Manual_Usuario_Gerente.class);
        startActivity(Manual_Usuario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gerente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cerrar_cliente_gerente) {
            Salir();
        }
        return super.onOptionsItemSelected(item);
    }

    void Salir (){
        auth.signOut();
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
        finish();
    }
}