package com.developers.lactimuu_prueba.Clases_Ahorcado;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lactimuu_prueba.R;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private String[] Palabras;
    private Random random;
    private String currPalabra;
    private TextView[] charViews;
    private LinearLayout palabrasLayout;
    private LetrasAdapter adapter;
    private GridView gridView;
    private int numcorrecto, numchars, sizeparts=6, currpartes;
    private ImageView[] partes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Palabras=getResources().getStringArray(R.array.palabras);
        palabrasLayout=findViewById(R.id.words);
        gridView=findViewById(R.id.letters);
        random = new Random();

        partes = new ImageView[sizeparts];
        partes[0] = findViewById(R.id.head);
        partes[1] = findViewById(R.id.body);
        partes[2] = findViewById(R.id.armLeft);
        partes[3] = findViewById(R.id.armRight);
        partes[4] = findViewById(R.id.legLeft);
        partes[5] = findViewById(R.id.legRight);

        playgame();
    }

    private void playgame(){
        String nuevapalabra = Palabras [random.nextInt(Palabras.length)];

        while(nuevapalabra.equals(currPalabra)) nuevapalabra  = Palabras[random.nextInt(Palabras.length)];

        currPalabra = nuevapalabra;

        charViews = new TextView[currPalabra.length()];

        palabrasLayout.removeAllViews();

        for (int i=0; i<currPalabra.length(); i++){
            charViews[i] = new TextView(this);
            charViews[i].setText(""+currPalabra.charAt(i));
            charViews[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            charViews[i].setGravity(Gravity.CENTER);
            charViews[i].setTextColor(Color.WHITE);
            charViews[i].setBackgroundResource(R.drawable.letter_bg);
            palabrasLayout.addView(charViews[i]);
        }
        adapter = new LetrasAdapter(this);
        gridView.setAdapter(adapter);
        numcorrecto=0;
        currpartes=0;
        numchars=currPalabra.length();

        for(int i = 0; i<sizeparts; i++){
            partes[i].setVisibility(View.INVISIBLE);
        }
    }

    public void letraspresionar(View view){
        String letras =((TextView)view).getText().toString();
        char letraschar=letras.charAt(0);

        view.setEnabled(false);

        boolean correcto=false;

        for(int i=0; i<currPalabra.length(); i++){
            if(currPalabra.charAt(i)==letraschar){
                correcto=true;
                numcorrecto++;
                charViews[i].setTextColor(Color.BLACK);
            }
        }
        if(correcto){
            if(numcorrecto == numchars){
                deshabilitarbotones();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("!!!GANASTE!!!");
                builder.setMessage("Felicidades!\n\nLa respuesta era \n\n"+currPalabra);
                builder.setPositiveButton("Jugar de Nuevo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GameActivity.this.playgame();
                    }
                });

                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GameActivity.this.finish();
                    }
                });
                builder.show();
            }
        }else if(currpartes<sizeparts){
            partes[currpartes].setVisibility(View.VISIBLE);
            currpartes++;
        }else{
            deshabilitarbotones();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Perdiste");
            builder.setMessage("Has Perdido!\n\nLa respuesta era \n\n"+currPalabra);
            builder.setPositiveButton("Jugar de Nuevo", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    GameActivity.this.playgame();
                }
            });

            builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    GameActivity.this.finish();
                }
            });
            builder.show();
        }
    }
    public void deshabilitarbotones(){
        for(int i = 0; i<gridView.getChildCount(); i++){
            gridView.getChildAt(i).setEnabled(false);
        }
    }
}