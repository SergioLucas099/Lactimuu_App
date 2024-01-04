package com.developers.lactimuu_prueba.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.developers.lactimuu_prueba.Manual_Usuario_Rol_Cliente;
import com.example.lactimuu_prueba.R;

public class FragmentManualUsuario extends Fragment {
    TextView ManualUsuario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.manualusuario,container,false);

        ManualUsuario = view.findViewById(R.id.txtmanual);

        ManualUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Manual_Usuario_Rol_Cliente.class);
                startActivity(intent);
            }
        });

        return view;
    }
}