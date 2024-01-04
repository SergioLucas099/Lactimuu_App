package com.developers.lactimuu_prueba.Clases_triqui_traka.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.developers.lactimuu_prueba.Clases_triqui_traka.triqui_traka;
import com.example.lactimuu_prueba.R;

public class StartFragment extends Fragment {

    private Button btn_start;
    private RadioButton r_play_with_computer, r_2_player;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        btn_start = view.findViewById(R.id.boton_start);
        r_play_with_computer = view.findViewById(R.id.r_with_computer);
        r_2_player = view.findViewById(R.id.r_2_player);
        r_play_with_computer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                triqui_traka.multiPlayer = false;
            }
        });
        r_2_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                triqui_traka.multiPlayer = true;
            }
        });
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                triqui_traka.scoreO = 0;
                triqui_traka.scoreX = 0;
                transaction.addToBackStack(GameFragment.TAG);
                transaction.replace(R.id.activity_triki, new GameFragment());
                transaction.commit();
            }
        });
        return view;
    }
}