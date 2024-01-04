package com.developers.lactimuu_prueba.Clases_Ahorcado;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.lactimuu_prueba.R;

public class LetrasAdapter extends BaseAdapter {
    private String[] letras;
    private LayoutInflater letrasInf;

    public LetrasAdapter(Context context){
        letras = new String[26];
        for (int i=0; i < letras.length; i++){
            letras[i] = ""+(char)(i+'A');
        }
        letrasInf = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return letras.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button btnLetras;
        if(view==null){
            btnLetras=(Button) letrasInf.inflate(R.layout.letras,viewGroup, false);
        }else{
            btnLetras=(Button)view;
        }
        btnLetras.setText(letras[i]);
        return btnLetras;
    }
}
