package com.developers.lactimuu_prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lactimuu_prueba.R;

public class ManualUsuarioEmpleado extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_usuario_empleado);

        textView = findViewById(R.id.txtmanualE);
        imageView = findViewById(R.id.imgAtras_MUE);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManualUsuarioEmpleado.this, ManualUsuarioRolEmpleado.class);
                startActivity(intent);
            }
        });
    }
}