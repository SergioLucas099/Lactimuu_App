package com.developers.lactimuu_prueba.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lactimuu_prueba.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Sugerencias extends Fragment {

    EditText Nom, Tel, Msg;
    TextView textView;
    Spinner spinner;
    Button button;
    FirebaseFirestore firestore;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.sugerencias_fragment, container, false);

        firestore = FirebaseFirestore.getInstance();

        spinner = root.findViewById(R.id.spinnersgr);
        Nom = root.findViewById(R.id.editTextNombre);
        Tel = root.findViewById(R.id.editTexttelefono);
        Msg = root.findViewById(R.id.contenido);
        textView = root.findViewById(R.id.datoAsunto);
        button = root.findViewById(R.id.crearsgr);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1: textView.setText("Sugerencia"); break;
                    case 2: textView.setText("Queja"); break;
                    case 3: textView.setText("Reclamo"); break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearDato();
            }
        });

        return root;
    }

    private void CrearDato() {

        String nombre = Nom.getText().toString();
        if (Nom.getText().toString().equals("")){
            Nom.setText("Anonimo");
            nombre = Nom.getText().toString();
        }
        String telefono = Tel.getText().toString();
        if (Tel.getText().toString().equals("")){
            Tel.setText("Sin Telefono");
            telefono = Tel.getText().toString();
        }
        String mensaje = Msg.getText().toString();
        String asunto = textView.getText().toString();
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate =  Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        Map<String, Object> map = new HashMap<>();

        map.put("Nombre", nombre);
        map.put("Telefono", telefono);
        map.put("Mensaje", mensaje);
        map.put("Asunto", asunto);
        map.put("Hora", saveCurrentDate);
        map.put("Fecha", saveCurrentTime);

        if (asunto.equals("") || mensaje.equals("")){
            Toast.makeText(getActivity(), "Ingrese los datos necesarios para continuar", Toast.LENGTH_LONG).show();
        }else{

            firestore.collection("BuzonRecomendaciones").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(getActivity(), "Gracias por sus sugerencias", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}