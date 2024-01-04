package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Modo_Usuario_Empleado extends AppCompatActivity {

    FirebaseAuth auth;
    Toolbar toolbar;
    FirebaseFirestore firestore;
    ImageView p1, p2, p3, p4, p5, p6, pll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modo_usuario_empleado);

        toolbar = findViewById(R.id.toolbar_modo_empleado);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        p1 = findViewById(R.id.pm1);
        p2 = findViewById(R.id.pm2);
        p3 = findViewById(R.id.pm3);
        p4 = findViewById(R.id.pm4);
        p5 = findViewById(R.id.pm5);
        p6 = findViewById(R.id.pm6);
        pll = findViewById(R.id.pmpl);

        p1.setVisibility(View.INVISIBLE);
        p2.setVisibility(View.INVISIBLE);
        p3.setVisibility(View.INVISIBLE);
        p4.setVisibility(View.INVISIBLE);
        p5.setVisibility(View.INVISIBLE);
        p6.setVisibility(View.INVISIBLE);
        pll.setVisibility(View.INVISIBLE);

        firestore.collection("Pedidos_Mesa_1").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    if(documentSnapshot.exists()){
                        p1.setVisibility(View.VISIBLE);
                    }
                    else{
                        p1.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        firestore.collection("Pedidos_Mesa_2").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    if(documentSnapshot.exists()){
                        p2.setVisibility(View.VISIBLE);
                    }
                    else{
                        p2.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        firestore.collection("Pedidos_Mesa_3").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    if(documentSnapshot.exists()){
                        p3.setVisibility(View.VISIBLE);
                    }
                    else{
                        p3.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        firestore.collection("Pedidos_Mesa_4").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    if(documentSnapshot.exists()){
                        p4.setVisibility(View.VISIBLE);
                    }
                    else{
                        p4.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        firestore.collection("Pedidos_Mesa_5").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    if(documentSnapshot.exists()){
                        p5.setVisibility(View.VISIBLE);
                    }
                    else{
                        p5.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        firestore.collection("Pedidos_Mesa_6").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    if(documentSnapshot.exists()){
                        p5.setVisibility(View.VISIBLE);
                    }
                    else{
                        p5.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        firestore.collection("Pedidos_Para_Llevar").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                    if(documentSnapshot.exists()){
                        pll.setVisibility(View.VISIBLE);
                    }
                    else{
                        pll.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    public void mesa1 (View view){
        Intent mesa1 = new Intent(this, Mesa1.class);
        startActivity(mesa1);
        finish();
    }

    public void mesa2 (View view){
        Intent mesa2 = new Intent(this, Mesa2.class);
        startActivity(mesa2);
        finish();
    }

    public void mesa3 (View view){
        Intent mesa3 = new Intent(this, Mesa3.class);
        startActivity(mesa3);
        finish();
    }

    public void mesa4 (View view){
        Intent mesa4 = new Intent(this, Mesa4.class);
        startActivity(mesa4);
        finish();
    }

    public void mesa5 (View view){
        Intent mesa5 = new Intent(this, Mesa5.class);
        startActivity(mesa5);
    }

    public void mesa6 (View view){
        Intent mesa6 = new Intent(this, Mesa6.class);
        startActivity(mesa6);
        finish();
    }

    public void pl (View view){
        Intent pl = new Intent(this, Para_Llevar.class);
        startActivity(pl);
        finish();
    }

    public void mu (View view){
        Intent mu = new Intent(this, ManualUsuarioEmpleado.class);
        startActivity(mu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empleado, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cerrar_cliente_empleado) {
            salir_empleado();
        }
        return super.onOptionsItemSelected(item);
    }

    void salir_empleado (){
        auth.signOut();
        Intent intent = new Intent(this, InicioActivity.class);
        startActivity(intent);
        finish();
    }
}