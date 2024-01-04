package com.developers.lactimuu_prueba.Clases_triqui_traka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.developers.lactimuu_prueba.Clases_triqui_traka.Fragments.StartFragment;
import com.example.lactimuu_prueba.R;

public class triqui_traka extends AppCompatActivity {

    private FrameLayout activity_triki;
    public static boolean multiPlayer = true;
    public static int scoreX = 0, scoreO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_triqui_traka);
        activity_triki = findViewById(R.id.activity_triki);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_triki, new StartFragment());
        transaction.commit();
    }
}