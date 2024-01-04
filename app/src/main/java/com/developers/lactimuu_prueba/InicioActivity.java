package com.developers.lactimuu_prueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.developers.lactimuu_prueba.Fragments.CarritoCompras;
import com.developers.lactimuu_prueba.Fragments.FragmentAcercaNosotros;
import com.developers.lactimuu_prueba.Fragments.FragmentManualUsuario;
import com.developers.lactimuu_prueba.Fragments.MainFragment;
import com.developers.lactimuu_prueba.Fragments.Sugerencias;
import com.developers.lactimuu_prueba.Fragments.Whatsapp;
import com.example.lactimuu_prueba.R;
import com.google.android.material.navigation.NavigationView;

public class InicioActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener { ;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new MainFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawer(GravityCompat.START);
        if(menuItem.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new MainFragment());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.acerca_nosotros){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentAcercaNosotros());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.ManualUsuario){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FragmentManualUsuario());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.sugerencias){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new Sugerencias());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.carrito){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new CarritoCompras());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.sesion){
            Intent siguiente = new Intent(this, InicioSesion.class);
            startActivity(siguiente);
        }
        if(menuItem.getItemId() == R.id.fa){
            Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/lactimuu.chiquinquira.5"));
            startActivity(i);
        }
        if(menuItem.getItemId() == R.id.wa){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new Whatsapp());
            fragmentTransaction.commit();
        }
        if(menuItem.getItemId() == R.id.in){
            Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.instagram.com/lactimuu_chiquinquira/"));
            startActivity(i);
        }
        return false;
    }
}