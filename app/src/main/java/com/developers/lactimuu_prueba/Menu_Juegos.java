package com.developers.lactimuu_prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.developers.lactimuu_prueba.Clases_Ahorcado.GameActivity;
import com.developers.lactimuu_prueba.Clases_Flappy_Bird.Juego_Flappy_Bird;
import com.developers.lactimuu_prueba.Clases_triqui_traka.triqui_traka;
import com.example.lactimuu_prueba.R;

public class Menu_Juegos extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_juegos);

        toolbar = findViewById(R.id.Toolbar_Menu_Juegos);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void a (View view){
        Intent siguiente = new Intent(this, Juego_Flappy_Bird.class);
        startActivity(siguiente);
    }
    public void b (View view){
        Intent siguiente = new Intent(this, GameActivity.class);
        startActivity(siguiente);
    }
    public void c (View view){
        Intent siguiente = new Intent(this, triqui_traka.class);
        startActivity(siguiente);
    }
    public void LinkJuego (View view){
        Intent siguiente3 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCaybAeO8m6Pn-I19p6qlmzg"));
        startActivity(siguiente3);
    }
}